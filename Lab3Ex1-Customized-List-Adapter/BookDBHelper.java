package com.klcal.lab3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDBHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BookDBContract.BookDbEntry.TABLE_NAME + "(" +
            BookDBContract.BookDbEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BookDBContract.BookDbEntry.COLUMN_NAME_ISBN + " TEXT, " +
            BookDBContract.BookDbEntry.COLUMN_NAME_TITLE + " TEXT, " +
            BookDBContract.BookDbEntry.COLUMN_NAME_AUTHOR + " TEXT" + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BookDBContract.BookDbEntry.TABLE_NAME;

    public static final int    DATABASE_VERSION = 1;
    public static final String DATABASE_NAME    = "BookDb.db";

    public BookDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
