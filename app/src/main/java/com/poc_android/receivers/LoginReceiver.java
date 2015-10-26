package com.poc_android.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.poc_android.activities.LoginActivity;
import com.poc_android.activities.MainActivity;
import com.poc_android.data.StorageService;
import com.poc_android.data.User;
import com.poc_android.models.UserData;

/**
 * Created by vanden on 10/15/15.
 */
public class LoginReceiver extends BroadcastReceiver {


    public LoginReceiver() {

        // prevents instantiation by other packages.
    }

    // Called when the BroadcastReceiver gets an Intent it's registered to receive
    public void onReceive(Context context, Intent intent) {

        //Create an intent for MainActivity activity
        Intent intentLoginReceiver = new Intent(context, LoginActivity.class);
        intentLoginReceiver.putExtra("userData",(UserData)intent.getParcelableExtra("data"));
        intentLoginReceiver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentLoginReceiver.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        System.out.println("Service return ");
        //Storage Service
        User user = StorageService.getUser("Leandro");
        if (user!=null) System.out.println("Username: "+user.getName()+" age: "+user.getAge()+ "sessionId: "+user.getSessionId());
        context.startActivity(intentLoginReceiver);
    }
}
