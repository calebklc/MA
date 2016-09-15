package com.klcal.lab3;

import android.provider.BaseColumns;

public class BookDBContract {

    public static abstract class BookDbEntry implements BaseColumns {

        public static final String TABLE_NAME = "TBL_BOOK";
        public static final String COLUMN_NAME_ISBN = "ISBN";
        public static final String COLUMN_NAME_TITLE = "TITLE";
        public static final String COLUMN_NAME_AUTHOR = "AUTHOR";

    }
}
