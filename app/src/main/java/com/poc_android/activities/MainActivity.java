package com.poc_android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.poc_android.R;
import com.poc_android.receivers.LoginReceiver;
import io.fabric.sdk.android.Fabric;


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
        setContentView(R.layout.activity_login);
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                //.kits(new CrashlyticsNdk()) TODO: SABER QUE ES CrashlyticsNdk()
                .debuggable(true)
                .build();
        Fabric.with(fabric);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

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


}
