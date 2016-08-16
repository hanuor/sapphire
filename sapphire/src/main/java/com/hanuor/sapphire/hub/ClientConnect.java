package com.hanuor.sapphire.hub;

import android.content.Context;
import android.util.Log;

import com.hanuor.client.Client;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 15-08-2016.
 */
public class ClientConnect {
    public ClientConnect(){

    }
    public void register(Context ctx, ArrayList<String> tags){
        Internals internals = new Internals(ctx);
        //the docId will be returned here thanks to the jar file
        //Add relevant conditions here
        //checking jar library
        internals.saveDocIdInternally(Client.makeJsonString(tags));
        Log.d("Sapphire",""+Client.makeJsonString(tags));
    }
}
