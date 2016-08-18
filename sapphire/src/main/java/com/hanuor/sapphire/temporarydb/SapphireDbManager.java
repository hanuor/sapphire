package com.hanuor.sapphire.temporarydb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

/**
 * Created by Shantanu Johri on 15-08-2016.
 */
public class SapphireDbManager extends SQLiteOpenHelper {


    public static final String DB_NAME = "SapphireInternal.db";
    private static final String TABLE_NAME = "clientManager";
    private static final String TABLE_JSONDOC= "jsonDocManager";
    private static final String ID_DOCS = "idoc";
    private static final int DB_VERSION = 1;

    public SapphireDbManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public SapphireDbManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_JDOCS = "CREATE TABLE "+ TABLE_JSONDOC + "("+
                ID_DOCS + " STRING" + ")";
        sqLiteDatabase.execSQL(TABLE_JDOCS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertJDoc(String Doc){
        clearJDocTable();
        
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_DOCS, Doc);
        db.insert(TABLE_JSONDOC, null, contentValues);
        db.close();
    }
    public void clearJDocTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JSONDOC, 1 + "=" + 1, null);
        db.close();
    }
    public String query(){
        String returnString = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + ID_DOCS + " FROM " + TABLE_JSONDOC;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                returnString =  cSor.getString(cSor.getColumnIndex(SapphireDbManager.ID_DOCS));

            }while(cSor.moveToNext());

        }
        return returnString;

    }
    public void openDBconnection(){


        Calendar today = Calendar.getInstance();
        int hour = today.get(Calendar.HOUR);
        //Calendar expireDate = Calendar.getInstance();
        //expireDate.set(2011, Calendar.AUGUST, 12);
        //today.compareTo(expireDate) == 0 || today.compareTo(expireDate) == 1
        //if ()

        //{
// expired - please purchase app

        //}
        //else
       // {
// do some stuff
        //}
    }
}
