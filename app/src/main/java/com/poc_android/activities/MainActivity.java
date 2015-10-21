package com.poc_android.activities;

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
import android.widget.EditText;

import com.poc_android.R;
import com.poc_android.helpers.Constants;
import com.poc_android.models.UserData;
import com.poc_android.receivers.LoginReceiver;
import com.poc_android.services.LoginService;
import com.poc_android.data.StorageService;

import io.realm.Realm;


public class MainActivity extends Activity {


    // An instance of the status broadcast receiver
    private LoginReceiver mLoginReceiver;
    protected final String TAG = getClass().getSimpleName();

    //UI Components
    EditText mUserName;
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();
        setContentView(R.layout.activity_main);

        //Define UI Components
        mUserName = (EditText)findViewById(R.id.input_username);
        mPassword   = (EditText)findViewById(R.id.input_password);


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
        StorageService.saveUser(mUserName.getText().toString(),0,Integer.parseInt(mPassword.getText().toString()));
        /*
         * Creates a new Intent to start the LoginService
         * IntentService. Passes a URI in the
         * Intent's "data" field.
         */
        UserData userData = new UserData();
        userData.setUsername(mUserName.getText().toString());
        userData.setPassword(Integer.parseInt(mPassword.getText().toString()));
        mServiceIntent = new Intent(this, LoginService.class);
        mServiceIntent.putExtra("userData",userData);
        this.startService(mServiceIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("YourActivity", "onNewIntent is called!");
        UserData d=(UserData)intent.getExtras().getParcelable("userData");
        //Check if the login response is OK
        super.onNewIntent(intent);
    } // End of onNewIntent(Intent intent)



}
