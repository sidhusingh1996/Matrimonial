package com.example.finalproject;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database

     */
    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table Userdetails(Name Text primary key, email1 Text, phoneNo Text)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String Name, String email1, String phoneNo){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues(); // keep things in pair
        // (Coloum, value)
        contentValue.put("Name",Name);
        contentValue.put("email1", email1);
        contentValue.put("phoneNo", phoneNo);
        long result = DB.insert("Userdetails",null,contentValue);

        return result != -1;

    }

    public Boolean updateuserdata(String name, String contact, String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues(); // keep things in pair
        contentValue.put("contact", contact);
        contentValue.put("dob", dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where Name = ?", new String[]{name});

        if(cursor.getCount()>0){
            long result = DB.update("Userdetails",contentValue, "name=?", new String[]{name});


            return result != -1;
        }
        return false;
    }

    public Boolean deleteuserdata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});

        if(cursor.getCount()>0){
            long result = DB.delete("Userdetails", "name=?", new String[]{name});


            return result != -1;
        }
        return false;
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null) ;

        return cursor;

    }
}