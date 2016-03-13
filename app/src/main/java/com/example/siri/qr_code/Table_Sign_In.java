package com.example.siri.qr_code;

import android.provider.BaseColumns;

/**
 * Created by siri on 04-Dec-14.
 */
public class Table_Sign_In {

    public Table_Sign_In()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {
        public static String EMAIL = "Email";                        //key = EMAIL
        public static String PWD = "Password";                       //key = PWD
        public static final String DATABASE_NAME ="SignIn_db";
        public static final String TABLE_NAME ="SignIn_info";


    }
}
