package com.example.edgar.optotypeapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Edgar on 01/09/2017.
 */

public class ImagePatientAdapter extends ArrayAdapter<ImagePatient> {

    Context context;
    int resource;
    ImagePatient[] data;

    public ImagePatientAdapter(Context context, int resource, ImagePatient[] objects){

        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.data = objects;

    }

    public View getView (int position, View convertView, ViewGroup parent){

        View row = convertView;
        ImagePatientHolder holder = null;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new ImagePatientHolder();

            holder.image = (ImageView) row.findViewById(R.id.imagePatientItemRow);
            holder.textData = (TextView) row.findViewById(R.id.textDataPatientItemRow);
            holder.textYears = (TextView) row.findViewById(R.id.textYearsOldItemRow);

            row.setTag(holder);
        }else{
            holder = (ImagePatientHolder) row.getTag();
        }

        ImagePatient patients = data[position];
        holder.textData.setText(patients.getData());
        holder.textYears.setText(patients.getYearsOld());
        holder.image.setImageResource(patients.getPhoto());

        return row;
    }




    static class ImagePatientHolder {

        ImageView image;
        TextView textData;
        TextView textYears;
    }
}
