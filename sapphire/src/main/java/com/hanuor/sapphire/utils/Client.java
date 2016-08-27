package com.hanuor.sapphire.utils;

import android.content.Context;

import com.hanuor.client.NodeMonitor;
import com.hanuor.container.Initializer;
import com.hanuor.container.LibraryDatabase;
import com.hanuor.sapphire.hub.Internals;
import com.hanuor.utils.ConverterUtils;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.storage.Storage;
import com.shephertz.app42.paas.sdk.android.storage.StorageService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Shantanu Johri on 16-08-2016.
 */
public class Client {
    private Context ctx;
    public static Initializer mInit = new Initializer();
    public static NodeMonitor mNodeMonitor = new NodeMonitor();
    private static StorageService storageService = App42API.buildStorageService();

    public Client(Context ctx){
      App42API.initialize(ctx, mInit.Appkey(),mInit.AppSecret());
       this.ctx = ctx;

    }
    public static double test(){
        return mNodeMonitor.nodeIncrementor(0.1);
    }
    /*private void addDocument(String documentID){
        EmailUtilities emailUtil = new EmailUtilities();
        String pwd = "hanuor";
        UserService userService = App42API.buildUserService();
        userService.createUser( documentID, pwd, emailUtil.generateEmail(), new App42CallBack() {
        public void onSuccess(Object response)
        {
        }
        public void onException(Exception ex)
        {
            //monitor the exceptions here
            System.out.println("Exception Message"+ex.getMessage());
        }
        });

    }
    */
    public  void writeJson(final String jsonDocument){
		/*
		Write json document to the database. This method is directly accessed from the android library.

		*/
        ServiceHandler.storageService.insertJSONDocument(LibraryDatabase.DBNAME, LibraryDatabase.collectionName, jsonDocument, new App42CallBack() {
            @Override
            public void onSuccess(Object o) {
                Internals internals = new Internals(ctx);
                Storage storage  = (Storage )o;
                ArrayList<Storage.JSONDocument> jsonDocList = storage.getJsonDocList();
                String docId = jsonDocList.get(0).getDocId();
                internals.saveDocIdInternally(docId);

            }


            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
        });

        //addDocument(docID);

        //return docID;

    }
    private void getJson(String docId) {
        // TODO Auto-generated method stub

		/*Get the json doccument from the database using a docId as a formal parameter.
		*/

        storageService.findDocumentById(LibraryDatabase.DBNAME, LibraryDatabase.collectionName, docId, new App42CallBack() {
            public void onSuccess(Object response)
            {
                Storage  storage  = (Storage )response;
                System.out.println("dbName is " + storage.getDbName());
                System.out.println("collection Name is " + storage.getCollectionName());
                ArrayList<Storage.JSONDocument> jsonDocList = storage.getJsonDocList();
                for(int i=0;i<jsonDocList.size();i++)
                {
                    System.out.println("objectId is " + jsonDocList.get(i).getDocId());
                    System.out.println("CreatedAt is " + jsonDocList.get(i).getCreatedAt());
                    System.out.println("UpdatedAtis " + jsonDocList.get(i).getUpdatedAt());
                    System.out.println("Jsondoc is " + jsonDocList.get(i).getJsonDoc());
                }
            }
            public void onException(Exception ex)
            {
                System.out.println("Exception Message"+ex.getMessage());
            }
        });
    }

    public  void makeJsonString(ArrayList<String> tags){
        ConverterUtils cutils = new ConverterUtils(tags);
        String jsonDoc =  cutils.getJsonString();
        //call the write Json script
        writeJson(jsonDoc);
    }
    public static void updateJson(final ArrayList<String> tags, String docID){

        storageService.findDocumentById(LibraryDatabase.DBNAME, LibraryDatabase.collectionName, docID, new App42CallBack() {
            public void onSuccess(Object response)
            {
                String jsonD = null;
                HashMap<String,String> hMap=new HashMap<String,String>();
                ArrayList<String> arrayList = new ArrayList<String>();
                Storage  storage  = (Storage )response;
                System.out.println("dbName is " + storage.getDbName());
                System.out.println("collection Name is " + storage.getCollectionName());
                ArrayList<Storage.JSONDocument> jsonDocList = storage.getJsonDocList();
                for(int i=0;i<jsonDocList.size();i++)
                {
                    jsonD = jsonDocList.get(i).getJsonDoc();
                }
                try {
                    if(jsonD!=null){
                        JSONObject jObj = new JSONObject(jsonD);
                        for(int i = 0; i<tags.size();i++){
                            String entityValue = null;
                            entityValue = jObj.getString(tags.get(i));
                            if(entityValue.length()!=0){
                                hMap.put(tags.get(i), entityValue);
                            }else{
                                arrayList.add(tags.get(i));
                            }
                            //add to hashmap here
                            for(int j = 0; j< arrayList.size();j++){
                                hMap.put(arrayList.get(j), "0.1");
                            }
                        }
                        updateJsonDoc(hMap);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
            public void onException(Exception ex)
            {
                System.out.println("Exception Message"+ex.getMessage());
            }
        });

    }
    protected static void updateJsonDoc(HashMap<String, String> hMap) {
        //Imported from Eclipse
        // TODO Auto-generated method stub
        ConverterUtils cUtils = new ConverterUtils();
        try {
            String output = cUtils.getUpdatedJsonString(hMap);

            //return the string here if you want to

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }

}
