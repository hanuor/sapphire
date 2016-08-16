package com.hanuor.sapphire.temporarydb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shantanu Johri on 15-08-2016.
 */
public class SapphireDbManager extends SQLiteOpenHelper {


    public static final String DB_NAME = "SapphireInternal.db";
    private static final String TABLE_NAME = "clientManager";
    private static final int DB_VERSION = 1;

    public SapphireDbManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public SapphireDbManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*String TABLE_ID = "CREATE TABLE " + TABLE_NAME + "("+
                ID_F + " STRING"
                + ")";
*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
