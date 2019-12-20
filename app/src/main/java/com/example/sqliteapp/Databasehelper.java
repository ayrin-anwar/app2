package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Databasehelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "Student_detils";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String AGE = "age";
    private static final String GENDER = "gender";

    private Context context;
    private static final int VERSION_NUMBER = 2;
    private static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " VARCHAR(255)," + AGE + " INTEGER," + GENDER + " VARCHAR(15) )";
    private static final String SELECT_ALL="SELECT * FROM "+TABLE_NAME;
    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {

        try {
            Toast.makeText(context, "on create is called:", Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, "Exception:" + e, Toast.LENGTH_LONG).show();
        }
    }


    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            Toast.makeText(context, "on upgrade is called:", Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Toast.makeText(context, "Exception:" + e, Toast.LENGTH_LONG).show();
        }

    }

    public long insertData(String name, String age, String gender) {
        SQLiteDatabase  sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);
        long rowId= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }
     public Cursor displayAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor1=sqLiteDatabase.rawQuery(SELECT_ALL,null);

        return cursor1;
    }
}

