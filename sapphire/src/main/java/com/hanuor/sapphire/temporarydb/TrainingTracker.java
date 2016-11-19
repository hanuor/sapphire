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

public class TrainingTracker extends SQLiteOpenHelper {
    private static final String DB_NAME_VALUE = "TrainindData.db";
    private static final String TRAINING_TABLE = "TrainingTable";
    private static final String MON_COL = "MondayColumn";
    private static final String TUE_COL = "TuesdayColumn";
    private static final String WED_COL = "WednesdayColumn";
    private static final String THU_COL = "ThursdayColumn";
    private static final String FRI_COL = "FridayColumn";
    private static final String SAT_COL = "SaturdayColumn";
    private static final String SUN_COL = "SundayColumn";
    private static  String COLUMN;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TRAINING_TABLE + "("+
                MON_COL + " INTEGER," + TUE_COL + " INTEGER," +
                WED_COL + " INTEGER," + THU_COL + " INTEGER," +
                FRI_COL + " INTEGER," + SAT_COL + " INTEGER," +
                SUN_COL + " INTEGER" + ");");
    }
    private static int Db_VERSION_VALUE = 13;
    public TrainingTracker(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public TrainingTracker(Context context){
        super(context, DB_NAME_VALUE, null, Db_VERSION_VALUE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void updateValue(int day){
        String GETCOLUMNNAME = null;
        switch (day){
            case 0:
                GETCOLUMNNAME = SUN_COL;
                break;
            case 1:
                GETCOLUMNNAME = MON_COL;
                break;
            case 2:
                GETCOLUMNNAME = TUE_COL;
                break;
            case 3:
                GETCOLUMNNAME = WED_COL;
                break;
            case 4:
                GETCOLUMNNAME = THU_COL;
                break;
            case 5:
                GETCOLUMNNAME = FRI_COL;
                break;
            case 6:
                GETCOLUMNNAME = SAT_COL;
                break;
            default:
                GETCOLUMNNAME = SUN_COL;
                break;
        }

        //query here then store

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GETCOLUMNNAME, 1);
        db.insert(TRAINING_TABLE, null, contentValues);
        db.close();
    }

    public int queryTracker(int day, boolean forAll){

        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = null;
        COLUMN = null;
        switch (day){
            case 0:
                query_params = "SELECT " + SUN_COL + " FROM " + TRAINING_TABLE;
                COLUMN = SUN_COL;
                break;
            case 1:
                query_params = "SELECT " + MON_COL + " FROM " + TRAINING_TABLE;
                COLUMN = MON_COL;
                break;
            case 2:
                query_params = "SELECT " + TUE_COL + " FROM " + TRAINING_TABLE;
                COLUMN = TUE_COL;
                break;
            case 3:
                query_params = "SELECT " + WED_COL + " FROM " + TRAINING_TABLE;
                COLUMN = WED_COL;
                break;
            case 4:
                query_params = "SELECT " + THU_COL + " FROM " + TRAINING_TABLE;
                COLUMN = THU_COL;
                break;
            case 5:
                query_params = "SELECT " + FRI_COL + " FROM " + TRAINING_TABLE;
                COLUMN = FRI_COL;
                break;
            case 6:
                COLUMN = SAT_COL;
                query_params = "SELECT " + SAT_COL + " FROM " + TRAINING_TABLE;
                break;
            default:
                COLUMN = SUN_COL;
                query_params = "SELECT " + SUN_COL + " FROM " + TRAINING_TABLE;
                break;
        }
        if(forAll){
            Cursor cSor = db.rawQuery(query_params, null);
            if(cSor.moveToFirst()){
                do{
                    return cSor.getInt(cSor.getColumnIndexOrThrow(TrainingTracker.COLUMN));
                }while(cSor.moveToNext());
            }else{
                return 0;
            }

        }else{
            Cursor cSor = db.rawQuery(query_params, null);
            if(cSor.moveToFirst()){
                do{
                    return cSor.getInt(cSor.getColumnIndexOrThrow(TrainingTracker.COLUMN));
                }while(cSor.moveToNext());
            }else{
                return 0;
            }

        }
    }
}
