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

import static android.R.attr.tag;

public class UploadDateStoreDb extends SQLiteOpenHelper {
    private static final String DB_NAME = "UploadDateStoreDb.db";
    private static final String TABLE_DB_DATE = "DateStoreTable";
    private static final String COLUMN_LATEST_DATE = "LatestDateStore";
    private static final int DB_VERSION = 1;
    public UploadDateStoreDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public UploadDateStoreDb(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DB_DATE + "(" + COLUMN_LATEST_DATE + " STRING" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void insertNewDate(String _date){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LATEST_DATE, tag);
        database.insert(TABLE_DB_DATE, null, cv);
    }
    public void clearTable(){
        SQLiteDatabase dbnew = this.getWritableDatabase();
        dbnew.delete(TABLE_DB_DATE, 1 + "=" + 1, null);
        dbnew.close();
    }
    
}
