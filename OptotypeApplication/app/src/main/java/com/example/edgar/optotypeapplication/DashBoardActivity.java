package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listViewMenu;
    Context contextActivity;

    ImagePatient listPatient[];
    ImagePatientAdapter imagePatientAdapter;

    Button logOut;
    ImageView imageUser;
    TextView userName;

    String [] menuDoctor = new String []{"Asociar Test", "Opción B", "Opción C" };

    public void loadDataForListPatient(){

        String resourcePatient = "patients";
        ArrayList<Patient> patients= new ArrayList <Patient> ();

        listPatient  = new ImagePatient[]{
                new ImagePatient("Edgar Landaeta","3", R.drawable.usuario_icon),
                new ImagePatient("Juan Landaeta","4", R.drawable.usuario_icon),
                new ImagePatient("Gabriel Landaeta","4", R.drawable.usuario_icon),
                new ImagePatient("Adira Quintero","4", R.drawable.usuario_icon),
                new ImagePatient("Victoria Malave","3", R.drawable.usuario_icon),
                new ImagePatient("Dimas Landaeta","3", R.drawable.usuario_icon),
                new ImagePatient("Juan Vega","5", R.drawable.usuario_icon),
                new ImagePatient("Spartacus sirio","5", R.drawable.usuario_icon),
                new ImagePatient("Enomao Batiato","4", R.drawable.usuario_icon),
                new ImagePatient("Marcos Carasos","3", R.drawable.usuario_icon),
        };

    }


    public void loadMenu(){
        SharedPreferences preferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);

        if (preferences.getString("roll", "defaultroll").equals("Doctor")){
            ArrayAdapter<String> adapterMenuDoctor = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, menuDoctor);
            listViewMenu.setAdapter(adapterMenuDoctor);
        }else if(preferences.getString("roll", "defaultroll").equals("Paciente Infantil")){
            loadDataForListPatient();
            imagePatientAdapter = new ImagePatientAdapter(contextActivity, R.layout.listview_image_patient_item_row, listPatient);
            listViewMenu.setAdapter(this.imagePatientAdapter);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        contextActivity = this;

        logOut = (Button) findViewById(R.id.buttonLogout);
        imageUser = (ImageView) findViewById(R.id.imageViewLoginUser);
        userName = (TextView) findViewById(R.id.textViewLoginUser);
        listViewMenu = (ListView) findViewById(R.id.listViewDashBoardMenu);

        logOut.setOnClickListener(this);

        loadMenu();

    }


    @Override
    public void onClick(View v) {

        SharedPreferences loginPreferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = loginPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.commit();

        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);

    }
}
