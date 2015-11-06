package com.poc_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.poc_android.R;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1Fragment extends Fragment {

    Button mButtonScan;
    TextView mTextView;
    private int MY_SCAN_REQUEST_CODE = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButtonScan = (Button) view.findViewById(R.id.scan_button);
        mTextView = (TextView) view.findViewById(R.id.scan_result);

        mButtonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onScanPress(view);
            }
        });

    }

    private void onScanPress(View v) {
        Intent scanIntent = new Intent(getActivity(), CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
        // hides the manual entry button
        // if set, developers should provide their own manual entry mechanism in the app
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true);
        // matches the theme of your application
        scanIntent.putExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, false); // default: false

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String resultStr;

        if(data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

            resultStr = "Card Number: " + scanResult.getRedactedCardNumber();
        } else {
            resultStr = getResources().getString(R.string.scan_result_cancel_text);
        }

        mTextView.setText(resultStr);
    }
}
