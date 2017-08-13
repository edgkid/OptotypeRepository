package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

      /* RequestUser requestUser = new RequestUser();

        if (requestUser.findUserOnSystem()){
            Intent dashBoardActivity = new Intent(LoginActivity.this, DashBoardActivity.class);
            startActivity(dashBoardActivity);
        }else{
            Toast.makeText(contextActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }*/

        HttpHandlerUser requestUser = new HttpHandlerUser("users");
        requestUser.connectToResource(this);

    }
}
