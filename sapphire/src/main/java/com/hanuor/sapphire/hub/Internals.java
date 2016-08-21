package com.hanuor.sapphire.hub;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.hanuor.client.Client;
import com.hanuor.container.Initializer;
import com.hanuor.container.LibraryDatabase;
import com.hanuor.sapphire.temporarydb.SapphireDbManager;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.storage.Storage;
import com.shephertz.app42.paas.sdk.android.storage.StorageService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 16-08-2016.
 */
public class Internals {
    private Context ctx = null;
    SapphireDbManager sapphireDbManager;
    public static Initializer mInit = new Initializer();
    private static StorageService storageService;

    public Internals(Context ctx){
        this.ctx = ctx;
        sapphireDbManager = new SapphireDbManager(ctx);
        App42API.initialize(ctx, mInit.Appkey(),mInit.AppSecret());
        storageService = App42API.buildStorageService();
    }
    public  void saveDocIdInternally(String docID){
        //Don't forget to add wrtie permission in android
        new FilePrinterAsyncTaskSapphire().execute(docID);
    }
    public String readIdfromDevice(){
        BufferedReader input = null;
        File file = null;
        try {
            file = new File(ctx.getFilesDir(), LibraryDatabase.INTERNALFNAME);

            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
    private class FilePrinterAsyncTaskSapphire extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            String content = urls[0];

            FileOutputStream outputStream = null;
            try {
                outputStream = ctx.openFileOutput(LibraryDatabase.INTERNALFNAME, Context.MODE_PRIVATE);
                outputStream.write(content.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }
    public void updateJsonFollowUp(final ArrayList<String> tags, String docID){
        storageService.findDocumentById(LibraryDatabase.DBNAME, LibraryDatabase.collectionName, docID, new App42CallBack() {
            public void onSuccess(Object response)
            {
                String jsonD = null;

                Storage storage  = (Storage )response;
                ArrayList<Storage.JSONDocument> jsonDocList = storage.getJsonDocList();

                for(int i=0;i<jsonDocList.size();i++)
                {
                    jsonD = jsonDocList.get(i).getJsonDoc();
                }
                try {
                    if(jsonD!=null){
                        JSONObject jObj = new JSONObject(jsonD);

                        String getDoc = Client.updateJsonDoc(Client.jsonDocAlgo(tags, jObj));
                        String queriedDoc = sapphireDbManager.query();
                        if(getDoc.equals(queriedDoc)){

                        }else{
                            sapphireDbManager.insertJDoc(getDoc);
                        }

                        Log.d("Sapphire",""+sapphireDbManager.query());
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Log.d("Sapp",""+e);
                    e.printStackTrace();
                }


            }
            public void onException(Exception ex)
            {
                Log.d("Sapp",""+ex.getMessage());
                System.out.println("Exception Message"+ex.getMessage());
            }
        });

    }
    public void hitTags(String _tag){
        //fetch from database
        String retrievedDoc = sapphireDbManager.query();
        try {
            String getDoc = Client.updateJsonDoc(Client.SapphireHitTag(retrievedDoc, _tag));
            sapphireDbManager.insertJDoc(getDoc);
            Log.d("Sapphire",""+sapphireDbManager.query());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
