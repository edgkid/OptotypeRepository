package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by Edgar on 03/09/2017.
 */

public class RequestPatient {

    private String request;
    private Context context;

    public RequestPatient(){

    }

    public RequestPatient(Context context) {
        this.context = context;
    }

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

    public void fillListPatientsToDay (ArrayList patients){

        Patient patient;
        String query =  " SELECT Name, LastName, MiddleName, MaidenName, YearsOld, Photo, FkUser " +
                        " FROM patient";
        PatientDbHelper PatientDb = new PatientDbHelper(this.context);
        SQLiteDatabase db = PatientDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            patient = new Patient();

            patient.setName(cursor.getString(0));
            patient.setLastName(cursor.getString(1));
            patient.setMiddleName(cursor.getString(2));
            patient.setMaidenName(cursor.getString(3));
            patient.setYearsOld(cursor.getString(4));
            patient.setPhoto(cursor.getString(5));
            patient.setFkUser(cursor.getString(6));

            patients.add(patient);

        }

    }

}
