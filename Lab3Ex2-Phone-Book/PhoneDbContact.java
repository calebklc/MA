package com.klcal.lab3ex2;

import android.provider.BaseColumns;

public class PhoneDbContact {

    public static abstract class PhoneDbEntry implements BaseColumns {
        public static final String TABLE_NAME         = "TBL_PHONE_BOOK";
        public static final String COLUMN_NAME_NAME   = "NAME";
        public static final String COLUMN_NAME_MOBILE = "MOBILE";
        public static final String COLUMN_NAME_OFFICE = "OFFICE";
        public static final String COLUMN_NAME_EMAIL  = "EMAIL";
    }
    
}
