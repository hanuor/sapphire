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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//Db to store starttimestamps of app for machine learning.

public class StartTimeStoreDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "TimeStampStore.db";
    private static final int DB_VERSION = 13;
    private static final String TABLE_TIMESTAMP = "TimeStampTable";
    private static final String COLUMN_TIMESTAMP = "TimeStampColumn";

    public StartTimeStoreDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TIMESTAMP + "(" + COLUMN_TIMESTAMP + " STRING" + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public StartTimeStoreDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void addTimeStampToDB(long _timeStamp){
        String longCon  = Long.toString(_timeStamp);
        String querytoAppend = retrieveStamp();
        String nss = querytoAppend + "\r\n" + longCon;
        clearTimestampTable();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TIMESTAMP, nss);
        long ff = db.insert(TABLE_TIMESTAMP, null, contentValues);
        db.close();
    }

    private String retrieveStamp(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + TABLE_TIMESTAMP;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                return cSor.getString(cSor.getColumnIndexOrThrow(COLUMN_TIMESTAMP));
            }while(cSor.moveToNext());
        }else{
            return "";
        }
    }
    public void clearTimestampTable(){
        SQLiteDatabase db0 = this.getWritableDatabase();
        db0.delete(TABLE_TIMESTAMP,1+"="+1 , null);
        db0.close();
    }
}
