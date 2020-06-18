package com.omar.myapps.one_onerdbapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DataBase_NAME = "database.db";

    public DatabaseHelper(Context context) {
        super(context, DataBase_NAME, null, 1);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table persons ( id INTEGER PRIMARY KEY,name TEXT NOT NULL );");
        db.execSQL(" create table passport_details ( id INTEGER PRIMARY KEY,\n" +
                "                                 Pass_Number TEXT NOT NULL,\n" +
                "                                 expiryDate TEXT NOT NULL,\n" +
                "                                 person_id INTEGER UNIQUE, FOREIGN KEY( person_id) REFERENCES persons( id) ON DELETE CASCADE\n" +
                "                                                         );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists persons");
        db.execSQL("drop table if exists passport_details");
        onCreate(db);
    }
}
