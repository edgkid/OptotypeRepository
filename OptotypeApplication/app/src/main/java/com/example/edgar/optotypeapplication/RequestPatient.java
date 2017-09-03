package com.example.edgar.optotypeapplication;

import android.content.Context;
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
        httpRequest.connectToResource((DashBoardActivity) this.context, patients);

        /*Log.d("explorar ", "lista de pacientes");
        Iterator iteratorPatients = patients.iterator();
        while (iteratorPatients.hasNext()){
            Patient patient = (Patient) iteratorPatients.next();
            System.out.println(patient.getName() + " " + patient.getLastName());
            Log.d("paciente: ", patient.getName() + " " + patient.getLastName());
        }*/

        if (patients.size() >0)
            Log.d("valor: ","veo la lista");
        else
            Log.d("valor","No veo la lista");

    }
}
