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

/**
 * Created by Edgar on 13/08/2017.
 */

public class HttpHandlerUser {

    private String request;
    private Context context;
    ServerPath serverPath = new ServerPath();

    public HttpHandlerUser(String request, Context context) {
        this.request = request;
        this.context = context;
    }

    public String sendRequestGet (){

        URL url = null;
        String line = "";
        int responseCode;
        StringBuilder result = null;
        String path = serverPath.getHttp() + serverPath.getIpAdddress() + serverPath.getPathAddress();

        try{

            url = new URL (path + this.request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();// en caso de que halla respuesta el valor es 200

            result = new StringBuilder();

            // equivalente a preguntar si la respuesta es igual a 200
            if (responseCode == HttpURLConnection.HTTP_OK){

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

    public void connectToResource (final LoginActivity ctx){

        Thread tr = new Thread(){
            @Override
            public void run() {

                final String result= sendRequestGet();
                ctx.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (verifyRespondeServer(result)){
                            Toast.makeText(ctx.getApplicationContext(),"Conexion con el servidor", Toast.LENGTH_SHORT).show();
                            procesingJson(result);
                        } else
                            Toast.makeText(ctx.getApplicationContext(),"Conexion No Datos", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        tr.start();
        tr.interrupt();
    }

    public void procesingJson (String result){

        Log.d("json: ", result);
        JSONArray array = null;

        SharedPreferences loginPreferences = this.context.getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = loginPreferences.edit();


        try {
            array = new JSONArray(result);

            for(int i=0; i<array.length(); i++){
                JSONObject jsonObj  = array.getJSONObject(i);
                preferencesEditor.putString("user", jsonObj.getString("username") );
                preferencesEditor.putString("password",jsonObj.getString("userpassword"));
                preferencesEditor.putString("roll", jsonObj.getString("rollname"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        preferencesEditor.commit();

    }
}
