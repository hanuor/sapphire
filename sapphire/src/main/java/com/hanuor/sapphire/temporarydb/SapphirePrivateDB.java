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

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SapphirePrivateDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "SapphireInternalPrivitized.db";
    private static final int DBVERSION = 3;

    private static final String TABLE_PR = "privatemoduletwo";
    private static final String COL_LISTTAGS =  "privateListTags";


    public SapphirePrivateDB(Context context) {
        super(context, DB_NAME, null, DBVERSION);
    }


    public SapphirePrivateDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     String TABLE_PRIVATETAGS = "CREATE TABLE " + TABLE_PR + "(" +
                COL_LISTTAGS + " STRING" + ");";
        sqLiteDatabase.execSQL(TABLE_PRIVATETAGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void storeTags(String jDocument){
        deleteAll();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_LISTTAGS, jDocument);
        db.insert(TABLE_PR, null, contentValues);
        db.close();
    }
    private void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PR, 1 + "=" + 1, null);
        db.close();
    }
}
