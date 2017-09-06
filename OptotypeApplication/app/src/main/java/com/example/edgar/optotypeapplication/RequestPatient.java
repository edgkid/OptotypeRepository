package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Edgar on 03/09/2017.
 */

public class RequestPatient {

    private String request;
    private Context context;

    public RequestPatient(String request, Context context) {
        this.request = request;
        this.context = context;
    }

    public void findPatientsToDay(ArrayList patients){

        HttpHandlerPatient httpRequest = new HttpHandlerPatient(this.request, this.context);
        httpRequest.connectToResource((DashBoardActivity) this.context);

        /*PatientDbHelper PatientDb = new PatientDbHelper(this.context);
        SQLiteDatabase db = PatientDb.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM patient", null);
        if (c.moveToFirst()){
            do{
                String nombre = c.getString(0);
                Log.d("name ", nombre);
            }while(c.moveToNext());
        }*/

    }
}
