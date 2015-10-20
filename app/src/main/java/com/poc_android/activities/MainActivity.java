package com.poc_android.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.poc_android.R;
import com.poc_android.helpers.Constants;
import com.poc_android.receivers.LoginReceiver;
import com.poc_android.services.LoginService;
import com.poc_android.data.StorageService;

import io.realm.Realm;


public class MainActivity extends Activity {


    // An instance of the status broadcast receiver
    private LoginReceiver mLoginReceiver;
    protected final String TAG = getClass().getSimpleName();



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

        //Create an instance of Realm and assign to StorageService
        Realm r=Realm.getInstance(this);
        StorageService.setContext(r);

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
        Test Realm.io library
         */
        StorageService.saveUser("Leandro",30,123456);
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
