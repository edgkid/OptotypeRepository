package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Edgar on 02/09/2017.
 */

public class HttpHandlerPatient {

    private String request;
    private Context context;
    ServerPath serverPath = new ServerPath();

    public HttpHandlerPatient(String request, Context context) {
        this.request = request;
        this.context = context;
    }

    public String sendRequestGet (){

        URL url = null;
        String line = "";
        int responseCode;
        StringBuilder result = null;
        String path = serverPath.getHttp() + serverPath.getIpAdddress() + serverPath.getPathAddress()+ this.request;

        try{
            Log.d("path: ", path);

            url = new URL (path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();// en caso de que halla respuesta el valor es 200

            Log.d("code: ", Integer.toString(responseCode));
            // equivalente a preguntar si la respuesta es igual a 200
            if (responseCode == HttpURLConnection.HTTP_OK){

                result = new StringBuilder();
                InputStream input = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                while ((line = reader.readLine()) !=null ){
                    result.append(line);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return result.toString();
    }


    public boolean verifyRespondeServer (String result){

        boolean value = false;

        try{

            JSONArray json = new JSONArray(result);
            if (json.length() > 0)
                value = true;

        }catch (Exception e){}

        return value;
    }

    public void connectToResource (final DashBoardActivity ctx, final ArrayList patients){

        Thread tr = new Thread(){
            @Override
            public void run() {

                final String result= sendRequestGet();
                ctx.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (verifyRespondeServer(result)){
                            Toast.makeText(ctx.getApplicationContext(),"Conexion con patients", Toast.LENGTH_SHORT).show();
                            procesingJson(result, patients);
                        } else
                            Toast.makeText(ctx.getApplicationContext(),"Conexion No patients", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        tr.start();
        tr.interrupt();
    }

    public void procesingJson (String result, ArrayList patients){

        JSONArray array = null;
        Patient patient = null;

        Log.d("JSON: ", result.toString());

        try {
            array = new JSONArray(result);

            for(int i=0; i<array.length(); i++){
                JSONObject jsonObj  = array.getJSONObject(i);
                //System.out.println(jsonObj.getString("firstname"));
                patient = new Patient();
                patient.setIdPatient(Integer.parseInt(jsonObj.getString("idpatient")));
                patient.setName(jsonObj.getString("firstname"));
                patient.setMiddleName(jsonObj.getString("middlename"));
                patient.setLastName(jsonObj.getString("lastname"));
                patient.setMaidenName(jsonObj.getString("maidenname"));
                patient.setYearsOld(Integer.parseInt(jsonObj.getString("yearsold")));
                patient.setFkUser(jsonObj.getString("fkuser"));
                patient.setPhoto(Integer.toString(R.drawable.usuario_icon));

                patients.add(patient);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("json: ", "No hay valor para procesar");
        }

    }


}
