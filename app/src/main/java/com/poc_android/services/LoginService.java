package com.poc_android.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.poc_android.api.MockyAPI;
import com.poc_android.api.RestClient;
import com.poc_android.helpers.Constants;
import com.poc_android.models.UserData;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by vanden on 10/14/15.
 */
public class LoginService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public LoginService() {
        super("LoginService");
    }

    private void BroadcastResult(UserData resultJSON){
        /*
         * Creates a new Intent containing a Uri object
         * BROADCAST_ACTION is a custom Intent action
         */
        String status = "OK";
        Intent localIntent =
                new Intent(Constants.BROADCAST_ACTION)
                        .putExtra("data", resultJSON)
                        // Puts the status into the Intent
                        .putExtra(Constants.EXTENDED_DATA_STATUS, status);
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        UserData userData = (UserData)workIntent.getParcelableExtra("userData");
        MockyAPI service = RestClient.getMockyAPIClient();
        Call<UserData> call = service.LoginApiSync(/*
        userData.getUsername(),
        userDate.getPassword()*/
        );
        try {
            Response<UserData> response=call.execute();
            BroadcastResult(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
