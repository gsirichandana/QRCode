package com.example.siri.qr_code;

import android.provider.BaseColumns;

/**
 * Created by siri on 05-Dec-14.
 */
public class Table_Sign_Up {

    public Table_Sign_Up()
    {

    }

    public static abstract class TableSignUpInfo implements BaseColumns
    {
        public static String NAME = "Name";                        //key = EMAIL
        public static String ENNo = "EnrollNo";
        public static String Branch = "Branch";
        public static String Year = "Year";
        public static String CPI = "CPI";
        public static String SSC = "SSC";
        public static String HSC = "HSC";
        public static String EMAIL = "Email";
        public static String PWD = "Password";
        public static String ConPWD = "Con_Password";                //key = PWD
        public static final String DATABASE_NAME ="SignUp_db";
        public static final String TABLE_NAME ="SignUp_info";


    }
}
