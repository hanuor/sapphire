package com.hanuor.sapphire.temporarydb;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;

public class ObjectBinderHelper {
    String dbpath;
    ObjectContainer db;
    Context context;

    public ObjectBinderHelper(Context ctx) {
        context = ctx;
    }
    public ObjectContainer db() {

        try {
            if (db == null || db.ext().isClosed()) {
                db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), db4oDBFullPath(context));
            }

            return db;

        } catch (Exception ie) {
            Log.e(ObjectBinderHelper.class.getName(), ie.toString());
            return null;
        }
    }

    private String db4oDBFullPath(Context ctx) {
        return ctx.getDir("data", 0) + "/" + "myDbHan.db4o";

    }
    public void CloseDb() {

        db.close();

    }

     public void emptyDb(){

        ObjectSet result= db.queryByExample(new Object());

        while(result.hasNext()){

            db.delete(result.next());

        }


    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void StoreIntent(final Context ctx, final Intent con) {
        //db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), db4oDBFullPath(ctx));
        db.store(con);

        db.commit();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code

            }
        });


        Log.d("sohail", "object stored");

    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void getAllIntents() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code
                ArrayList<Intent> list = new ArrayList<Intent>();

                Intent proto = new Intent();
               // db.query(proto);
               // ObjectSet<Intent> result = db.query(proto);


               /* while (result.hasNext()) {

                    list.add(result.next());

                }*/

                Log.d("SohailBhai",list.toString());
            }
        });

    }

    public boolean updateObject(Intent ObjTo, Intent ObjFrom) {



        Intent found = null;

        ObjectSet<Intent> result = db.queryByExample(ObjTo);
/*
        if (result.hasNext()) { // if found

            found = result.next();

            found.setAge(ObjFrom.getAge()); // shallow copy just replay to, to From.

            found.setMember(ObjFrom.isMember());

            found.setName(ObjFrom.getName());

            found.setNumber(ObjFrom.getNumber());

            db.store(found);

            db.commit();

            return true;

        }*/

        return false;

    }

    public boolean deleteObject(Intent p) {

        Intent found = null;
        ObjectSet<Intent> result = db.queryByExample(p);

        if (result.hasNext()) {

            found = result.next();
            db.delete(found);

            return true;

        } else {

            return false;

        }

    }

}
