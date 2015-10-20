package com.poc_android.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;
import com.poc_android.api.RestClient;
import com.poc_android.api.WeatherAPI;
import com.poc_android.constants.Constants;
import com.poc_android.model.WeatherData;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by vanden on 10/14/15.
 */
public class LoginService extends IntentService {

    private static String appId = "bd82977b86bf27fb59a04b61b657fb6f";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public LoginService() {
        super("LoginService");
    }

    private void BroadcastResult(String resultJSON){
        /*
         * Creates a new Intent containing a Uri object
         * BROADCAST_ACTION is a custom Intent action
         */
        String status = "OK";
        Intent localIntent =
                new Intent(Constants.BROADCAST_ACTION)
                        .putExtra("data",resultJSON)
                        // Puts the status into the Intent
                        .putExtra(Constants.EXTENDED_DATA_STATUS, status);
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
        //...
        // Do work here, based on the contents of dataString
        //...

        WeatherAPI service = RestClient.getClient();
        String URL = "London"+appId;
        Call<WeatherData> call = service.getWeatherFromApiSync("London", appId);

        call.enqueue(new Callback<WeatherData>() {

            @Override
            public void onResponse(Response<WeatherData> response, Retrofit retrofit) {
                Log.d("MainActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    WeatherData result = response.body();
                    String resultJSON = new Gson().toJson(result);
                    Log.d("MainActivity", "response = " + resultJSON);
                    BroadcastResult(resultJSON);
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors

                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Error: " + t.getMessage().toString());
            }
        });


    }
}
