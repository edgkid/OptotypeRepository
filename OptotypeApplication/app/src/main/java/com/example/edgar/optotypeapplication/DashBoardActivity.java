package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.content.Intent;
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

        //Para obtener los datos mientras termino la conexion
        ///esto deberia ser remplazado por el manejo desesiones y el objeto de usuario
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String receiver = extras.getString("UserName");

        listViewMenu = (ListView) findViewById(R.id.listViewDashBoardMenu);
        ArrayAdapter<String> adapterMenuDoctor = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, menuDoctor);
        ArrayAdapter<String> adapterMenuPatient = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, menuPatient);

        if (receiver.equals("Doctor"))
            listViewMenu.setAdapter(adapterMenuDoctor);
        if(receiver.equals("Paciente"))
            listViewMenu.setAdapter(adapterMenuPatient);

    }


}
