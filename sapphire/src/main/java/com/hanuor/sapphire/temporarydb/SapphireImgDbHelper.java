package com.hanuor.sapphire.temporarydb;/*
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
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SapphireImgDbHelper  extends SQLiteOpenHelper{
    private static final String DB_NAME = "SapphireInternalIMG.db";
    private static final String TABLE_IMAGE = "ImageHandler";
    private static final String ID_IMG = "ImageStorage";
    private static final String ID_IMGKEY = "ImageKeyTag";
    private static final int DB_VERSION = 2;

    public SapphireImgDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public SapphireImgDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_IMG = "CREATE TABLE " + TABLE_IMAGE + "(" +
                ID_IMGKEY+ " STRING," +
                ID_IMG + " BLOB NOT NULL " + ")";
        sqLiteDatabase.execSQL(TABLE_IMG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertImage(String tag, byte []  image) throws SQLException {
        if(getCount()>0) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_IMAGE, 1 + "=" + 1, null);
            db.close();
            // database.close();
        }else{
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(ID_IMGKEY, tag);
            cv.put(ID_IMG, image);
            database.insert(TABLE_IMAGE, null, cv);
            Log.d("dbsapp", "" + database.toString());
        }
    }
    private int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + TABLE_IMAGE;
        Cursor cSor = db.rawQuery(query_params, null);
        return cSor.getCount();
    }
    public String imgquery(){
        String returnString = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + TABLE_IMAGE;
        Cursor cSor = db.rawQuery(query_params, null);
        Log.d("Sappmmmm",""+cSor.getCount());
        if(cSor.moveToFirst()){
            do{
                Log.d("SappTat - ",""+cSor.getColumnIndex(SapphireImgDbHelper.ID_IMGKEY)+"    "+cSor.getColumnIndex(SapphireImgDbHelper.ID_IMG));
                byte[] m = cSor.getBlob(0);
                Log.d("Sapptti",""+m.toString());
               // returnString =  cSor.getString(cSor.getColumnIndex(SapphireImgDbHelper.ID_IMGKEY));

            }while(cSor.moveToNext());

        }
        return returnString;

    }

}
