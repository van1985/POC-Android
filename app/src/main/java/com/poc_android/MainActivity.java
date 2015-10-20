package com.poc_android;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import com.google.gson.Gson;
import com.poc_android.api.RestClient;
import com.poc_android.api.WeatherAPI;
import com.poc_android.constants.Constants;
import com.poc_android.model.WeatherData;
import com.poc_android.receivers.LoginReceiver;
import com.poc_android.services.LoginService;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;


public class MainActivity extends Activity {


    // An instance of the status broadcast receiver
    private LoginReceiver mLoginReceiver;
    protected final String TAG = getClass().getSimpleName();
    private static String appId = "bd82977b86bf27fb59a04b61b657fb6f";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // The filter's action is BROADCAST_ACTION
        IntentFilter statusIntentFilter = new IntentFilter(
                Constants.BROADCAST_ACTION);


        // Instantiates a new LoginReceiver
        mLoginReceiver = new LoginReceiver();

        // Registers the DownloadStateReceiver and its intent filters
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mLoginReceiver,
                statusIntentFilter);

        WeatherAPI service = RestClient.getClient();
        String URL = "London"+appId;
        Call<WeatherData> call = service.getWeatherFromApiSync("London",appId);
        call.enqueue(new Callback<WeatherData>() {

            @Override
            public void onResponse(Response<WeatherData> response, Retrofit retrofit) {
                Log.d("MainActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    WeatherData result = response.body();
                    Log.d("MainActivity", "response = " + new Gson().toJson(result));
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors

                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Error: "+t.getMessage().toString());
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    Intent mServiceIntent;
    public void login(View view) {
        System.out.println("Login button");
        /*
         * Creates a new Intent to start the LoginService
         * IntentService. Passes a URI in the
         * Intent's "data" field.
         */
        String dataUrl="data";
        mServiceIntent = new Intent(this, LoginService.class);
        mServiceIntent.setData(Uri.parse(dataUrl));

        this.startService(mServiceIntent);
    }



}
