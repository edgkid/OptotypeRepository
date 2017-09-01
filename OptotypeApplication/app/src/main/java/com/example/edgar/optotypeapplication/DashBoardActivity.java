package com.example.edgar.optotypeapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listViewMenu;
    Context contextActivity;

    //ArrayAdapter<String> adapterMenuPatient;
    ImagePatient listPatient[];
    ImagePatientAdapter imagePatientAdapter;

    Button logOut;
    ImageView imageUser;
    TextView userName;

    //String [] menuPatient = new String []{"Optotypes"};
    String [] menuDoctor = new String []{"Asociar Test", "Opción B", "Opción C" };

    public void loadDataForListPatient(){
       ///this.adapterMenuPatient = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, menuPatient);

        listPatient  = new ImagePatient[]{
                new ImagePatient("3","Edgar Landaeta", R.drawable.usuario_icon),
                new ImagePatient("4","Juan Landaeta", R.drawable.usuario_icon),
                new ImagePatient("4","Gabriel Landaeta", R.drawable.usuario_icon),
                new ImagePatient("4","Adira Quintero", R.drawable.usuario_icon),
                new ImagePatient("5","Victoria Malave", R.drawable.usuario_icon),
                new ImagePatient("3","Dimas Landaeta", R.drawable.usuario_icon),
                new ImagePatient("3","Juan Vega", R.drawable.usuario_icon),
                new ImagePatient("4","Spartacus sirio", R.drawable.usuario_icon),
                new ImagePatient("5","Enomao Batiato", R.drawable.usuario_icon),
                new ImagePatient("4","Marcos Carasos", R.drawable.usuario_icon),
        };

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        contextActivity = this;

        logOut = (Button) findViewById(R.id.buttonLogout);
        imageUser = (ImageView) findViewById(R.id.imageViewLoginUser);
        userName = (TextView) findViewById(R.id.textViewLoginUser);

        logOut.setOnClickListener(this);

        listViewMenu = (ListView) findViewById(R.id.listViewDashBoardMenu);
        ArrayAdapter<String> adapterMenuDoctor = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, menuDoctor);
        //ArrayAdapter<String> adapterMenuPatient = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, menuPatient);

        SharedPreferences preferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);

        if (preferences.getString("roll", "defaultroll").equals("Doctor")){
            listViewMenu.setAdapter(adapterMenuDoctor);
        }else if(preferences.getString("roll", "defaultroll").equals("Paciente Infantil")){
            loadDataForListPatient();
            imagePatientAdapter = new ImagePatientAdapter(contextActivity, R.layout.listview_image_patient_item_row, listPatient);
            listViewMenu.setAdapter(this.imagePatientAdapter);
        }

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
