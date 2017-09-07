package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


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

    public void findPatientsToDay(){


        PatientDbHelper PatientDb = new PatientDbHelper(this.context);
        SQLiteDatabase db = PatientDb.getReadableDatabase();

        Log.d(".....","Consultar");
        Cursor cursor = db.rawQuery("SELECT name FROM patient", null);

        if (existLocalRecord(cursor)){
            Log.d("Datos: ","Locales");
            do{
                String nombre = cursor.getString(0);
                Log.d("name ", nombre);
            }while(cursor.moveToNext());
        }else{
            Log.d("Datos: ","Servidor");
            HttpHandlerPatient httpHandlerPatient = new HttpHandlerPatient(request, context);
            httpHandlerPatient.connectToResource((DashBoardActivity) context);
        }

    }

    public boolean existLocalRecord (Cursor cursor){

        boolean value = false;

        if (cursor.moveToFirst()){
            value = true;
        }else{
            value = false;
        }

        return value;
    }

}
