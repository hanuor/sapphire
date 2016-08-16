package com.hanuor.sapphire.hub;

import android.content.Context;

import com.hanuor.sapphire.utils.Client;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 15-08-2016.
 */
public class ClientConnect {
    public ClientConnect(){

    }
    public void register(Context ctx, ArrayList<String> tags){
        //the docId will be returned here thanks to the jar file
        //Add relevant conditions here
        //checking jar library

        Client mC = new Client(ctx);
         mC.makeJsonString(tags);
    }
}
