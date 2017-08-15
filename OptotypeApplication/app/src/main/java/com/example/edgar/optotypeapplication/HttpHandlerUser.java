package com.example.edgar.optotypeapplication;

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
 * Created by Edgar on 13/08/2017.
 */

public class HttpHandlerUser {


    public static String jsonValue = "";
    private String request;
    ServerPath serverPath = new ServerPath();

    public HttpHandlerUser(String request) {
        this.request = request;
    }

    public String sendRequestGet (){

        URL url = null;
        String line = "";
        int responseCode;
        StringBuilder result = null;
        String path = serverPath.getHttp() + serverPath.getIpAdddress() + serverPath.getPathAddress();

        Log.d("get: ",path + this.request);

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

    public String connectToResource (final LoginActivity ctx){

        Thread tr = new Thread(){
            @Override
            public void run() {
                HttpHandlerUser.jsonValue = sendRequestGet();
            }
        };
        tr.start();
        if (verifyRespondeServer(HttpHandlerUser.jsonValue)) {
            Toast.makeText(ctx.getApplicationContext(),"Conexion con el servidor", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(ctx.getApplicationContext(),"No Conexion con el servidor", Toast.LENGTH_SHORT).show();

        return HttpHandlerUser.jsonValue;
    }


}
