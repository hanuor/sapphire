package com.hanuor.sapphire.hub;

import android.content.Context;

import com.hanuor.sapphire.utils.ExceptionHandler;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 15-08-2016.
 */
public class SapphireApi {
    private Context context = null;
    private SapphireApi connect = null;
    private ArrayList<String> tags = null;
    ClientConnect mclient = new ClientConnect();
    public SapphireApi(Context context){
        if(context == null){

            ExceptionHandler.writeError("Context cannot be null");
        }else{
            this.context = context;
        }
    }
    public void setInstance(SapphireApi connect){
        this.connect = connect;
    }
    public SapphireApi registerTags(ArrayList<String> tags){
        connect.tags = tags;
        mclient.register(context, tags);
        return connect;
    }


}

