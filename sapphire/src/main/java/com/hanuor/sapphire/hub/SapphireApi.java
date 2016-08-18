package com.hanuor.sapphire.hub;

import android.content.Context;
import android.view.View;

import com.hanuor.sapphire.utils.ExceptionHandler;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 15-08-2016.
 */
public class SapphireApi {
    private Context context = null;
    private SapphireApi connect = null;
    private ArrayList<String> tags = null;
    private ArrayList<String> updateTags = null;
    private View mview = null;
    ClientConnect mclient;
    public SapphireApi(Context context){
        if(context == null){

            ExceptionHandler.writeError("Context cannot be null");
        }else{
            this.context = context;
            mclient = new ClientConnect(context);
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
    public SapphireApi updateTags(ArrayList<String> tags){
        connect.updateTags = tags;
        mclient.update(context, tags);
        return connect;
    }
    public SapphireApi attachView(View view){
        //attach a view to show suggestions
        connect.mview = view;
        return connect;
    }

}

