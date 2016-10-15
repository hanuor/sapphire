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

public class SuggestionTempDBHandler extends SQLiteOpenHelper {

    private static final String DBNAME = "SuggestionDBHelper.db";
    private static final String TABLENAME = "PARENTTABLEDB";
    private static final String COLUMN_ID = "KEYTAG";
    private static final String COLUMN_VALUE = "VALUE";
    private static final int DBVERSION = 3;

/*
    private static final String ATTR_INT = "INTEGERSTORE";
    private static final String ATTR_STRING = "STRINGSTORE";
    private static final String ATTR_BOOLEAN = "BOOLEANSTORE";
    private static final String ATTR_INT = "INTEGERSTORE";
    private static final String ATTR_INT = "INTEGERSTORE";*/
    public SuggestionTempDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SuggestionTempDBHandler(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLECREATE = "CREATE TABLE " + TABLENAME + "("+
                                COLUMN_ID + " STRING," + COLUMN_VALUE
                                + " TEXT);";
        sqLiteDatabase.execSQL(TABLECREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int insertData(String key, byte[] value){
        //return 1 when operation successful
        //else return 0 when some error has occurred
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, key);
        contentValues.put(COLUMN_VALUE, value);
        db.insert(TABLENAME, null, contentValues);
        db.close();
        return 1;
    }
    public byte[] retrieveIntentData(String keyTag){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + COLUMN_ID + "," + COLUMN_VALUE + " FROM " + TABLENAME + " WHERE " + COLUMN_ID + "=" + "'" + keyTag + "';";
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                return cSor.getBlob(cSor.getColumnIndexOrThrow(SuggestionTempDBHandler.COLUMN_VALUE));
            }while(cSor.moveToNext());
        }else{
            return null;
        }
    }

}
