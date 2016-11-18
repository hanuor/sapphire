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
}
