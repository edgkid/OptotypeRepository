package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DashBoardActivity extends AppCompatActivity {

    ListView listViewMenu;
    Context contextActivity;

    String [] menuPatient = new String []{"Optotypes"};
    String [] menuDoctor = new String []{"Asociar Test", "Opción B", "Opción C" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        contextActivity = this;

        listViewMenu = (ListView) findViewById(R.id.listViewDashBoardMenu);
        ArrayAdapter<String> adapterMenuDoctor = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, menuDoctor);
        ArrayAdapter<String> adapterMenuPatient = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, menuPatient);

        SharedPreferences preferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);

        if (preferences.getString("roll", "defaultroll").equals("Doctor")){
            listViewMenu.setAdapter(adapterMenuDoctor);
        }else if(preferences.getString("roll", "defaultroll").equals("Paciente Infantil")){
            listViewMenu.setAdapter(adapterMenuPatient);
        }

    }


}
