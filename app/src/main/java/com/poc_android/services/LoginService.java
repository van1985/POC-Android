package com.poc_android.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.poc_android.constants.Constants;

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

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
        //...
        // Do work here, based on the contents of dataString
        //...
        /*
         * Creates a new Intent containing a Uri object
         * BROADCAST_ACTION is a custom Intent action
         */
        String status = "OK";
        Intent localIntent =
                new Intent(Constants.BROADCAST_ACTION)
                        // Puts the status into the Intent
                        .putExtra(Constants.EXTENDED_DATA_STATUS, status);
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}
