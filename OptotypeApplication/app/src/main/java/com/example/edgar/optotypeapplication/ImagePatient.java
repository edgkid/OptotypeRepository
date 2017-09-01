package com.example.edgar.optotypeapplication;

/**
 * Created by Edgar on 30/08/2017.
 */

public class ImagePatient {

    private String data;
    private String yearsOld;
    private int photo;

    public ImagePatient(String data, String yearsOld, int photo) {
        this.data = data;
        this.yearsOld = yearsOld;
        this.photo = photo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(String yearsOld) {
        this.yearsOld = yearsOld;
    }
}
