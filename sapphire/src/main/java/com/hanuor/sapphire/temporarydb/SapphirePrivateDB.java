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
import android.util.Log;

public class SapphirePrivateDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "SapphireInternalPrivitized.db";
    private static final int DBVERSION = 3;
    private static final String TABLE_PTREE = "sapprivlearn";
    private static final String COLUMN_IMG = "sappprivlearncolIMG";

    public SapphirePrivateDB(Context context) {
        super(context, DB_NAME, null, DBVERSION);
    }


    public SapphirePrivateDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLEOFPROTOCOLS = "CREATE TABLE " + TABLE_PTREE + "(" +
                COLUMN_IMG + " TEXT);";
        sqLiteDatabase.execSQL(TABLEOFPROTOCOLS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void storeIMG(byte[] imgArray){
        byte retrieveBArray[] = retrievePImage();
        if(retrieveBArray==null){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cvalues = new ContentValues();
            cvalues.put(COLUMN_IMG, imgArray);
            db.insert(TABLE_PTREE, null, cvalues);
            db.close();
        }else{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_PTREE, 1 + "=" + 1, null);
            db.close();
            SQLiteDatabase dbnew = this.getWritableDatabase();
            ContentValues cvalues = new ContentValues();
            cvalues.put(COLUMN_IMG, imgArray);
            dbnew.insert(TABLE_PTREE, null, cvalues);
            dbnew.close();
        }

    }

    public byte[] retrievePImage(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + COLUMN_IMG + " FROM " + TABLE_PTREE;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                Log.d("Sappyy","Yes");
                return cSor.getBlob(cSor.getColumnIndexOrThrow(COLUMN_IMG));

            }while(cSor.moveToNext());
        }else{
            return null;
        }

    }
}
