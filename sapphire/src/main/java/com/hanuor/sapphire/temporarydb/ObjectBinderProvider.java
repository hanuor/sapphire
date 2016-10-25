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
import android.util.Log;

import java.util.List;

public class ObjectBinderProvider extends ObjectBinderHelper {

    private static ObjectBinderProvider provider = null;

    //configure Db4oHelper by Passing Context.
    public ObjectBinderProvider(Context ctx) {
        super(ctx);
    }

    public static ObjectBinderProvider getInstance(Context ctx) {
        if (provider == null)
            provider = new ObjectBinderProvider(ctx);
        return provider;
    }

    //This method is used to store the object into the database.
    public void store(Intent exercise) {
        db().store(exercise);
        Log.d("SapphireDb40","Stored");
    }

    //This method is used to delete the object into the database.
    public void delete(Intent exercise) {
        db().delete(exercise);
    }

    //This method is used to retrive all object from database.
    public List<Intent> findAll() {
        return db().query(Intent.class);
    }

    //This method is used to retrive matched object from database.
    public List<Intent> getRecord(Intent s) {
        return db().queryByExample(s);
    }
// public ObjectSet<Student> getAllData() {
//  Student proto = new Student(null, null, 0);
//  ObjectSet result = db().queryByExample(proto);
//  return result;
// }

}
