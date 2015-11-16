package com.poc_android.activities;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poc_android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondSplashFragment extends android.support.v4.app.Fragment {


    public SecondSplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_splash, container, false);
    }


}
