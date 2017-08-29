package com.example.edgar.optotypeapplication;

/**
 * Created by Edgar on 28/08/2017.
 */

public class UserRoll {

    private String rollName;
    private String rollDescription;
    private int idRoll;

    public UserRoll(String rollName, String rollDescription, int idRoll) {
        this.rollName = rollName;
        this.rollDescription = rollDescription;
        this.idRoll = idRoll;
    }

    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    public String getRollDescription() {
        return rollDescription;
    }

    public void setRollDescription(String rollDescription) {
        this.rollDescription = rollDescription;
    }

    public int getIdRoll() {
        return idRoll;
    }

    public void setIdRoll(int idRoll) {
        this.idRoll = idRoll;
    }
}
