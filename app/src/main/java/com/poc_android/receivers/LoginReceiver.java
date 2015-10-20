package com.poc_android.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by vanden on 10/15/15.
 */
public class LoginReceiver extends BroadcastReceiver {


    public LoginReceiver() {

        // prevents instantiation by other packages.
    }

    // Called when the BroadcastReceiver gets an Intent it's registered to receive
    public void onReceive(Context context, Intent intent) {
        System.out.println("Service return");
    }
}
