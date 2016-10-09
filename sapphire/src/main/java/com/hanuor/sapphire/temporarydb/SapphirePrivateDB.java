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

    private static final String TABLE_PR = "privatemoduletwo";
    private static final String COL_LISTTAGS =  "privateListTags";

    private static final String COLUMN_PR_DAYS = "privateModulodataDays";
    private static final String TABLE_PRDAYS_MON = "privatedatadaysTABLE_MON";
    private static final String TABLE_PRDAYS_TUE = "privatedatadaysTABLE_TUE";
    private static final String TABLE_PRDAYS_WED = "privatedatadaysTABLE_WED";
    private static final String TABLE_PRDAYS_THU = "privatedatadaysTABLE_THU";
    private static final String TABLE_PRDAYS_FRI = "privatedatadaysTABLE_FRI";
    private static final String TABLE_PRDAYS_SAT = "privatedatadaysTABLE_SAT";
    private static final String TABLE_PRDAYS_SUN = "privatedatadaysTABLE_SUN";

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
        String TABLEPRMON = "CREATE TABLE " + TABLE_PRDAYS_MON + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRTUE = "CREATE TABLE " + TABLE_PRDAYS_MON + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRWED = "CREATE TABLE " + TABLE_PRDAYS_MON + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRTHU = "CREATE TABLE " + TABLE_PRDAYS_MON + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRFRI = "CREATE TABLE " + TABLE_PRDAYS_MON + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRSAT = "CREATE TABLE " + TABLE_PRDAYS_MON + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRSUN = "CREATE TABLE " + TABLE_PRDAYS_SUN + "("+
                COLUMN_PR_DAYS + " STRING);";

        sqLiteDatabase.execSQL(TABLE_PRIVATETAGS);
        sqLiteDatabase.execSQL(TABLEPRMON);
        sqLiteDatabase.execSQL(TABLEPRTUE);
        sqLiteDatabase.execSQL(TABLEPRWED);
        sqLiteDatabase.execSQL(TABLEPRTHU);
        sqLiteDatabase.execSQL(TABLEPRFRI);
        sqLiteDatabase.execSQL(TABLEPRSAT);
        sqLiteDatabase.execSQL(TABLEPRSUN);
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
        Log.d("SapphireBlof",""+fetchprivategetJsonStringfromDB());
    }
    private void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PR, 1 + "=" + 1, null);
        db.close();
    }
    public String fetchprivategetJsonStringfromDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + TABLE_PR;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                return cSor.getString(cSor.getColumnIndexOrThrow(SapphirePrivateDB.COL_LISTTAGS));
            }while(cSor.moveToNext());
        }else{
            return null;
        }
    }
    public void nodeupdatePRDAYsModulo1(int day, String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch(day){
            case 0:
                    deleteNodePRdatafor(0);
                    contentValues.put(COLUMN_PR_DAYS, data);
                    db.insert(TABLE_PRDAYS_SUN, null, contentValues);
                    db.close();
                break;
            case 1:
                deleteNodePRdatafor(1);
                contentValues.put(TABLE_PRDAYS_MON, data);
                db.insert(TABLE_PRDAYS_MON, null, contentValues);
                db.close();
                break;
            case 2:
                deleteNodePRdatafor(2);
                contentValues.put(TABLE_PRDAYS_TUE, data);
                db.insert(TABLE_PRDAYS_TUE, null, contentValues);
                db.close();
                break;
            case 3:
                deleteNodePRdatafor(3);
                contentValues.put(TABLE_PRDAYS_WED, data);
                db.insert(TABLE_PRDAYS_WED, null, contentValues);
                db.close();
                break;
            case 4:
                deleteNodePRdatafor(4);
                contentValues.put(TABLE_PRDAYS_THU, data);
                db.insert(TABLE_PRDAYS_THU, null, contentValues);
                db.close();
                break;
            case 5:
                deleteNodePRdatafor(5);
                contentValues.put(TABLE_PRDAYS_FRI, data);
                db.insert(TABLE_PRDAYS_FRI, null, contentValues);
                db.close();
                break;
            case 6:
                deleteNodePRdatafor(6);
                contentValues.put(TABLE_PRDAYS_SAT, data);
                db.insert(TABLE_PRDAYS_SAT, null, contentValues);
                db.close();
                break;
            default:
                break;
        }
    }
    public void deleteNodePRdatafor(int day){
        SQLiteDatabase db = this.getWritableDatabase();
        switch(day){
            case 0:
                db.delete(TABLE_PRDAYS_SUN,1+"="+1 , null);
                db.close();
                break;
            case 1:
                db.delete(TABLE_PRDAYS_MON,1+"="+1 , null);
                db.close();
                break;
            case 2:
                db.delete(TABLE_PRDAYS_TUE,1+"="+1 , null);
                db.close();
                break;
            case 3:
                db.delete(TABLE_PRDAYS_WED,1+"="+1 , null);
                db.close();
                break;
            case 4:
                db.delete(TABLE_PRDAYS_THU,1+"="+1 , null);
                db.close();
                break;
            case 5:
                db.delete(TABLE_PRDAYS_FRI,1+"="+1 , null);
                db.close();
                break;
            case 6:
                db.delete(TABLE_PRDAYS_SAT,1+"="+1 , null);
                db.close();
                break;
            default:
                break;
        }
    }

}
