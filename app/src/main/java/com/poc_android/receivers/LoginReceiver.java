package com.poc_android.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.poc_android.data.StorageService;
import com.poc_android.data.User;
import com.poc_android.models.WeatherData;

/**
 * Created by vanden on 10/15/15.
 */
public class LoginReceiver extends BroadcastReceiver {


    public LoginReceiver() {

        // prevents instantiation by other packages.
    }

    // Called when the BroadcastReceiver gets an Intent it's registered to receive
    public void onReceive(Context context, Intent intent) {
        WeatherData d=(WeatherData)intent.getParcelableExtra("data");
        System.out.println("Service return ");
        User user = StorageService.getUser("Leandro");
        System.out.println("Username: "+user.getName()+" age: "+user.getAge()+ "sessionId: "+user.getSessionId());
    }
}
