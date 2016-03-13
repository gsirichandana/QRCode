package com.example.siri.qr_code;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.siri.qr_code.Table_Sign_Up.TableSignUpInfo.TABLE_NAME;

/**
 * Created by siri on 04-Dec-14.
 */
public class DatabaseOps extends SQLiteOpenHelper
{
    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE "+ TABLE_NAME +"("+ Table_Sign_Up.TableSignUpInfo.NAME+" TEXT,"+ Table_Sign_Up.TableSignUpInfo.ENNo+" TEXT,"+ Table_Sign_Up.TableSignUpInfo.Branch+" TEXT,"+ Table_Sign_Up.TableSignUpInfo.Year+" TEXT,"+ Table_Sign_Up.TableSignUpInfo.CPI+" NUMBER,"+ Table_Sign_Up.TableSignUpInfo.SSC+" DECIMAL,"+ Table_Sign_Up.TableSignUpInfo.HSC+" DECIMAL,"+ Table_Sign_Up.TableSignUpInfo.EMAIL+" TEXT,"+ Table_Sign_Up.TableSignUpInfo.PWD+" TEXT);";

    public DatabaseOps(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, Table_Sign_Up.TableSignUpInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations","Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb)
    {
    sdb.execSQL(CREATE_QUERY);
        Log.d("Table Operations","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0,int arg1, int arg2)
    {

    }

    public void putInfo(DatabaseOps dop,String name,String en,String branch,String year,String cpi,String ssc,String hsc,String email,String password)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Table_Sign_Up.TableSignUpInfo.NAME,name);
        cv.put(Table_Sign_Up.TableSignUpInfo.ENNo,en);
        cv.put(Table_Sign_Up.TableSignUpInfo.Branch,branch);
        cv.put(Table_Sign_Up.TableSignUpInfo.Year,year);
        cv.put(Table_Sign_Up.TableSignUpInfo.CPI,cpi);
        cv.put(Table_Sign_Up.TableSignUpInfo.SSC,ssc);
        cv.put(Table_Sign_Up.TableSignUpInfo.HSC,hsc);
        cv.put(Table_Sign_Up.TableSignUpInfo.EMAIL,email);
        cv.put(Table_Sign_Up.TableSignUpInfo.PWD,password);
        long k = SQ.insert(TABLE_NAME,null,cv);
        Log.d("Database Operations","One Row inserted");
    }

    public Cursor validate(DatabaseOps dop)
    {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {Table_Sign_Up.TableSignUpInfo.NAME, Table_Sign_Up.TableSignUpInfo.ENNo, Table_Sign_Up.TableSignUpInfo.Branch, Table_Sign_Up.TableSignUpInfo.Year, Table_Sign_Up.TableSignUpInfo.CPI, Table_Sign_Up.TableSignUpInfo.SSC, Table_Sign_Up.TableSignUpInfo.HSC, Table_Sign_Up.TableSignUpInfo.EMAIL, Table_Sign_Up.TableSignUpInfo.PWD};
        Cursor CR = SQ.query(Table_Sign_Up.TableSignUpInfo.TABLE_NAME,columns,null,null,null,null,null,null);
        return CR;
    }
}
