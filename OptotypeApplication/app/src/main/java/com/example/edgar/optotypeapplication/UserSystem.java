package com.example.edgar.optotypeapplication;

/**
 * Created by Edgar on 13/08/2017.
 */

public class UserSystem {

    private String userName;
    private String password;
    private int idUser;
    private int fkRoll;

    private UserRoll userRoll;

    public UserSystem() {
        this.userName ="";
        this.password = "";
        this.idUser = 0;
        this.fkRoll = 0;
        this.userRoll = null;
    }

    public UserSystem(String userName, String password, int idUser, int fkRoll, UserRoll userRoll) {
        this.userName = userName;
        this.password = password;
        this.idUser = idUser;
        this.fkRoll = fkRoll;
        this.userRoll = userRoll;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getFkRoll() {
        return fkRoll;
    }

    public void setFkRoll(int fkRoll) {
        this.fkRoll = fkRoll;
    }

    public UserRoll getUserRoll() {
        return userRoll;
    }

    public void setUserRoll(UserRoll userRoll) {
        this.userRoll = userRoll;
    }
}
