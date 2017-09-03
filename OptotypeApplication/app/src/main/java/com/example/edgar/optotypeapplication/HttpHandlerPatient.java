package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        String path = serverPath.getHttp() + serverPath.getIpAdddress() + serverPath.getPathAddress();

        try{

            url = new URL (path + this.request);
            Log.d("path: ", path + this.request );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();// en caso de que halla respuesta el valor es 200

            result = new StringBuilder();

            // equivalente a preguntar si la respuesta es igual a 200
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_BAD_REQUEST){

                InputStream input = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                while ((line = reader.readLine()) !=null ){
                    result.append(line);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        Log.d("result: ", result.toString());

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

    public void connectToResource (final DashBoardActivity ctx){

        Thread tr = new Thread(){
            @Override
            public void run() {

                final String result= sendRequestGet();
                ctx.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (verifyRespondeServer(result)){
                            Toast.makeText(ctx.getApplicationContext(),"Conexion con patients", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(ctx.getApplicationContext(),"Conexion No patients", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        tr.start();
        tr.interrupt();
    }


}
