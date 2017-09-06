package com.example.edgar.optotypeapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Edgar on 05/09/2017.
 */

public class PatientDbHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "opticalDataBase";

    public PatientDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql ="";
        ContentValues values = new ContentValues();

        // crear Tablas de base de datos
        sql = sql + "CREATE TABLE " + PatientDbContract.PatientEntry.TABLE_NAME + " ( ";
        sql = sql + PatientDbContract.PatientEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT,";
        sql = sql + PatientDbContract.PatientEntry.ID + ", ";
        sql = sql + PatientDbContract.PatientEntry.NAME + ", ";
        sql = sql + PatientDbContract.PatientEntry.LASTNAME + ", ";
        sql = sql + PatientDbContract.PatientEntry.MAIDENNAME + ", ";
        sql = sql + PatientDbContract.PatientEntry.MAIDENNAME + ", ";
        sql = sql + PatientDbContract.PatientEntry.YEARSOLD + ", ";
        sql = sql + PatientDbContract.PatientEntry.PHOTO + ", ";
        sql = sql + PatientDbContract.PatientEntry.FKUSER + ", ";
        sql = sql + "UNIQUE (" + PatientDbContract.PatientEntry.ID + " ) ";
        sql = sql +" ) ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql ="";
        ContentValues values = new ContentValues();

        // crear Tablas de base de datos
        sql = sql + "CREATE TABLE " + PatientDbContract.PatientEntry.TABLE_NAME + " ( ";
        sql = sql + PatientDbContract.PatientEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql = sql + PatientDbContract.PatientEntry.ID + " TEXT, ";
        sql = sql + PatientDbContract.PatientEntry.NAME + " TEXT, ";
        sql = sql + PatientDbContract.PatientEntry.LASTNAME + " TEXT, ";
        sql = sql + PatientDbContract.PatientEntry.MAIDENNAME + " TEXT, ";
        sql = sql + PatientDbContract.PatientEntry.MAIDENNAME + " TEXT, ";
        sql = sql + PatientDbContract.PatientEntry.YEARSOLD + " TEXT, ";
        sql = sql + PatientDbContract.PatientEntry.PHOTO + " TEXT, ";
        sql = sql + PatientDbContract.PatientEntry.FKUSER + " TEXT, ";
        sql = sql + " UNIQUE ( " + PatientDbContract.PatientEntry.ID + " ) ";
        sql = sql +" ) ";

        db.execSQL(sql);

    }
}
