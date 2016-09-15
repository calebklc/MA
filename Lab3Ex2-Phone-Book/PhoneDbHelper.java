package com.klcal.lab3ex2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhoneDbHelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE =
            "CREATE TABLE " + PhoneDbContact.PhoneDbEntry.TABLE_NAME + "(" +
            PhoneDbContact.PhoneDbEntry._ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PhoneDbContact.PhoneDbEntry.COLUMN_NAME_NAME + " TEXT, " +
            PhoneDbContact.PhoneDbEntry.COLUMN_NAME_MOBILE + " INTEGER, " +
            PhoneDbContact.PhoneDbEntry.COLUMN_NAME_OFFICE + " TEXT, " +
            PhoneDbContact.PhoneDbEntry.COLUMN_NAME_EMAIL + " TEXT" + ")";

    private static final String DROP_TABLE =
            "DROP TABLE IF EXIST " + PhoneDbContact.PhoneDbEntry.TABLE_NAME;

    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME    = "PhoneDb.db";

    public PhoneDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
