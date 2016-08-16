package com.hanuor.sapphire.hub;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
        new FilePrinterAsyncTaskSapphire().execute(docID);



    }
    private class FilePrinterAsyncTaskSapphire extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            String fileName = urls[0];
            String content = urls[0];

            FileOutputStream outputStream = null;
            try {
                outputStream = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
                outputStream.write(content.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BufferedReader input = null;
            File file = null;
            try {
                file = new File(ctx.getFilesDir(), fileName); // Pass getFilesDir() and "MyFile" to read file

                input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line;
                StringBuffer buffer = new StringBuffer();
                while ((line = input.readLine()) != null) {
                    buffer.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

}



}
