package com.hanuor.sapphire.hub;

import android.content.Context;

import java.io.FileOutputStream;

/**
 * Created by Shantanu Johri on 16-08-2016.
 */
public class Internals {
    private Context ctx = null;
    public Internals(Context ctx){
        this.ctx = ctx;
    }
    public  void saveDocIdInternally(String docID){

        //Don't forget to add wrtie permission in android

        String fileName = docID;
        String content = docID;

        FileOutputStream outputStream = null;
        try {
            outputStream = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
