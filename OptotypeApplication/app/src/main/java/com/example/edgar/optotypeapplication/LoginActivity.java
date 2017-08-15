package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText    editTextUserName;
    EditText    editTextPaswword;
    Button      buttonLogin;
    ImageView   imageViewIcon;
    Context contextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        contextActivity = this;

        editTextUserName = (EditText) findViewById(R.id.editTextUserNameLogin);
        editTextPaswword = (EditText) findViewById(R.id.editTextViewPassWord);
        imageViewIcon   = (ImageView) findViewById(R.id.imageViewIconEye);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener((View.OnClickListener) contextActivity);
    }

    @Override
    public void onClick(View v) {

        String resourceUser = "users/"+editTextUserName.getText().toString() + "," + editTextPaswword.getText().toString();
        String jsonString;
        JSONObject jsonObject;
        JSONArray json_array = null;

        //Trabajando en conexion y Login no borrar
        HttpHandlerUser requestUser = new HttpHandlerUser(resourceUser);
        jsonString = requestUser.connectToResource(this);

        //Log.d("resultado: ",jsonString);

    }

    public void showJson (){

    }
}
