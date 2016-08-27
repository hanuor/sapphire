package com.hanuor.sapphire.hub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.hanuor.client.Client;
import com.hanuor.container.Initializer;
import com.hanuor.container.LibraryDatabase;
import com.hanuor.sapphire.temporarydb.SapphireDbManager;
import com.hanuor.sapphire.temporarydb.SapphireImgDbHelper;
import com.hanuor.sapphire.utils.BitmapUtility;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.storage.Storage;
import com.shephertz.app42.paas.sdk.android.storage.StorageService;
import com.shephertz.app42.paas.sdk.android.upload.UploadService;

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
    private static SapphireDbManager sapphireDbManager;
    private static SapphireImgDbHelper sapphireImgDbHelper;
    public static Initializer mInit = new Initializer();
    private static StorageService storageService;

    private static Client _client = new Client();
    private  BitmapUtility bitmapUtility = new BitmapUtility();
    private static UploadService uploadService;

    public Internals(Context ctx){
        this.ctx = ctx;
        sapphireDbManager = new SapphireDbManager(ctx);
        sapphireImgDbHelper = new SapphireImgDbHelper(ctx);
        App42API.initialize(ctx, mInit.Appkey(),mInit.AppSecret());
        Backendless.initApp( ctx, mInit.ImgHelperId(), mInit.ImgHelperSecret(),"v1");
        storageService = App42API.buildStorageService();
        uploadService = App42API.buildUploadService();
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
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Log.d("Sapp",""+e);
                    e.printStackTrace();
                }


            }
            public void onException(Exception ex)
            {
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
    public void storImgs(Context ctx, ArrayList<ImageView> imgviews){

        String destination = getAppName(ctx) +"*"+ readIdfromDevice();

        for(int i = 0; i<imgviews.size(); i++){
            //add Tag check here, not adding for now as this is just for testing
            String tag = null;
            tag = (String) imgviews.get(i).getTag() + "*" + readIdfromDevice()+ ".jpg";
            Bitmap bmp = null;
            try {
                bmp = ((BitmapDrawable)imgviews.get(i).getDrawable()).getBitmap();
                Backendless.Files.Android.upload(bitmapUtility.createResizedBitmap(bmp), Bitmap.CompressFormat.PNG,
                        100, tag, destination , new AsyncCallback<BackendlessFile>() {
                            @Override
                            public void handleResponse(BackendlessFile backendlessFile) {
                            }

                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
    private static String getAppName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        if(context.getString(stringId)!= null){
            return context.getString(stringId);
        }else{
            return "null_app_name";
        }

    }



}
