package com.hanuor.sapphire.hub;

import android.content.Context;
import android.util.Log;

import com.hanuor.sapphire.temporarydb.SapphireDbManager;
import com.hanuor.sapphire.utils.Client;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 15-08-2016.
 */
public class ClientConnect {
    SapphireDbManager sapphireDbManager;
    Internals internals;
    ClientConnect mclient;
    public ClientConnect(Context ctx){
        sapphireDbManager = new SapphireDbManager(ctx);
        internals = new Internals(ctx);
    }
    public void register(Context ctx, ArrayList<String> tags){
        //the docId will be returned here thanks to the jar file
        //Add relevant conditions here
        //checking jar library
        Client mC = new Client(ctx);
        mC.makeJsonString(tags);
    }
    public void update(Context ctx, ArrayList<String> tags){
        mclient = new ClientConnect(ctx);
        String docID = internals.readIdfromDevice();
        if(docID==null){
            mclient.register(ctx, tags);
            docID = internals.readIdfromDevice();

            Log.d("Sapppdd1",""+docID);

        }else{
            internals.updateJsonFollowUp(tags, docID);
            docID = internals.readIdfromDevice();
            Log.d("Sapppdd2",""+docID);

        }
        Log.d("Sapppdd",""+docID);
        //save Jdoc in the database


    }
    public void tagUpdate(String _tag){
    internals.hitTags(_tag);
    }
}
