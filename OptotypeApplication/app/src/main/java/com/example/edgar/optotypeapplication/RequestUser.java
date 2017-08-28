package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Edgar on 13/08/2017.
 */

public class RequestUser {

    private String request;
    private Context context;

    public RequestUser(String request, Context context) {
        this.request = request;
        this.context = context;
    }

    public boolean findUserOnSystem (){

        boolean value = false;
        String password = "";
        String user = "";
        String roll = "";

        HttpHandlerUser httpRequestUser = new HttpHandlerUser(request, context);
        httpRequestUser.connectToResource((LoginActivity) context);

        SharedPreferences loginPreferences = context.getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);

        Log.d("Datos: ",  loginPreferences.getString("user", "defaultUser"));
        Log.d("Datos: ", loginPreferences.getString("password", "defaultUser"));

        user = loginPreferences.getString("user", "defaultUser");
        password = loginPreferences.getString("password", "defaultUser");

        if (!user.equals("defaultUser") && !password.equals("defaultUser")){
            value = true;
        }

        return value;
    }
}
