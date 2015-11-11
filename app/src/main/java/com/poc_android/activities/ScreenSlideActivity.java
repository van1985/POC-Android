package com.poc_android.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.poc_android.R;

public class ScreenSlideActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private ImageView mImageIndicator1, mImageIndicator2, mImageIndicator3;
    private Button mBtnSkip, mBtnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        getActionBar().hide();

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mBtnDone = (Button) findViewById(R.id.button_done);
        mBtnSkip = (Button) findViewById(R.id.button_skip);
        mImageIndicator1 = (ImageView) findViewById(R.id.indicator_one);
        mImageIndicator2 = (ImageView) findViewById(R.id.indicator_two);
        mImageIndicator3 = (ImageView) findViewById(R.id.indicator_three);

        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mImageIndicator1.setEnabled(true);
                        mImageIndicator2.setEnabled(false);
                        mImageIndicator3.setEnabled(false);
                        mBtnDone.setVisibility(View.INVISIBLE);
                        mBtnSkip.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mImageIndicator1.setEnabled(false);
                        mImageIndicator2.setEnabled(true);
                        mImageIndicator3.setEnabled(false);
                        mBtnDone.setVisibility(View.GONE);
                        mBtnSkip.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mImageIndicator1.setEnabled(false);
                        mImageIndicator2.setEnabled(false);
                        mImageIndicator3.setEnabled(true);
                        mBtnDone.setVisibility(View.VISIBLE);
                        mBtnSkip.setVisibility(View.INVISIBLE);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return new FirstSplashFragment();
                case 1:
                    return new SecondSplashFragment();
                case 2:
                    return new ThirdSplashFragment();
                default:
                    return new FirstSplashFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
