package com.example.edgar.optotypeapplication;

/**
 * Created by Edgar on 30/08/2017.
 */

public class ImagePatient {

    private String data;
    private String edad;
    private int photo;

    public ImagePatient(String data, String edad, int photo) {
        this.data = data;
        this.edad = edad;
        this.photo = photo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
