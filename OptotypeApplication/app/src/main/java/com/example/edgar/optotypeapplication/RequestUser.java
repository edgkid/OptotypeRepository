package com.example.edgar.optotypeapplication;

import android.widget.TextView;

/**
 * Created by Edgar on 13/08/2017.
 */

public class RequestUser {

    UserSystem user = new UserSystem();

    TextView textTest;
    DashBoardActivity dashBoard;

    public boolean findUserOnSystem (){

        boolean value = false;
        String  request = "users";
        HttpHandlerUser httpRequestUser = new HttpHandlerUser(request);

        //httpRequestUser.connectToResource(textTest,dashBoard);

        return value;
    }
}
