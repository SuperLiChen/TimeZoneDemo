package com.timeZoon.android;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.TimeZone;

import info.hoang8f.android.segmented.SegmentedGroup;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    RadioButton easternButton;
    RadioButton buttonTimezoneCentral;
    RadioButton buttonTimezoneMountain;
    RadioButton buttonTimezonePacific;
    RadioButton buttonTimezoneAlaska;
    RadioButton buttonTimezoneChina;
    RadioButton buttonTimezoneHawaii;
    RadioButton buttonTimezoneArizona;
    SegmentedGroup timezoneControl;
    TextView mTvTimeZone;

    /**
     * Distinguish time zones by short identifiers , default timeZone is Eastern timeZone.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();

        initData();


    }

    private void initEvent() {
        timezoneControl.setTintColor(ContextCompat.getColor(MainActivity.this, R.color.light_grey), ContextCompat.getColor(MainActivity.this, R.color.white));
        timezoneControl.setOnCheckedChangeListener(timeZoneListener);
    }

    private void initData() {
        //根据当前当前标准时间 创建一个 timeZone
        TimeZone timezone = TimeZone.getDefault();

        mTvTimeZone.setText(timezone.getDisplayName(false, TimeZone.SHORT));

        Log.e(TAG,timezone.getID());

        Log.e(TAG,TimeZone.getTimeZone("Asia/Shanghai").getDisplayName(false,TimeZone.SHORT));
        Log.e(TAG,TimeZone.getTimeZone("America/Anchorage").getDisplayName(false,TimeZone.SHORT));
        Log.e(TAG,TimeZone.getTimeZone("America/Phoenix").getDisplayName(false,TimeZone.LONG)+" : "+TimeZone.getTimeZone("US/Arizona").getID());
        Log.e(TAG,TimeZone.getTimeZone("America/Chicago").getDisplayName(false,TimeZone.SHORT));
        Log.e(TAG,TimeZone.getTimeZone("America/New_York").getDisplayName(false,TimeZone.SHORT));
        Log.e(TAG,TimeZone.getTimeZone("Pacific/Honolulu").getDisplayName(false,TimeZone.SHORT));
        Log.e(TAG,TimeZone.getTimeZone("America/Denver").getDisplayName(false,TimeZone.LONG)+" : "+TimeZone.getTimeZone("US/Mountain").getID());
        Log.e(TAG,TimeZone.getTimeZone("America/Los_Angeles").getDisplayName(false,TimeZone.SHORT));

        checkTimeZone(timezone.getDisplayName(false, TimeZone.SHORT),timezone.getID());
    }

    public void checkTimeZone(String shortTimeZone,String mountainId) {
        Log.i(TAG,mountainId+"---");
        String timeZone = getString(R.string.timezone_eastern_title);
        if (shortTimeZone.equals(TimeZone.getTimeZone("Asia/Shanghai").getDisplayName(false,TimeZone.SHORT))) {
            timeZone = getString(R.string.timezone_china_title);
        } else if (shortTimeZone.equals(TimeZone.getTimeZone("America/Anchorage").getDisplayName(false,TimeZone.SHORT)) ) {
            timeZone = getString(R.string.timezone_alaska_title);
        } else if (shortTimeZone.equals(TimeZone.getTimeZone("America/Phoenix").getDisplayName(false,TimeZone.SHORT))&&TimeZone.getTimeZone("America/Phoenix").getID().equals(mountainId)) {
            timeZone = getString(R.string.timezone_arizona_title);
        } else if (shortTimeZone.equals(TimeZone.getTimeZone("America/Chicago").getDisplayName(false,TimeZone.SHORT))) {
            timeZone = getString(R.string.timezone_central_title);
        } else if (shortTimeZone.equals(TimeZone.getTimeZone("America/New_York").getDisplayName(false,TimeZone.SHORT))) {
            timeZone = getString(R.string.timezone_eastern_title);
        } else if (shortTimeZone.equals(TimeZone.getTimeZone("Pacific/Honolulu").getDisplayName(false,TimeZone.SHORT))) {
            timeZone = getString(R.string.timezone_hawaii_title);
        } else if (shortTimeZone.equals(TimeZone.getTimeZone("America/Denver").getDisplayName(false,TimeZone.SHORT))) {
            timeZone = getString(R.string.timezone_mountain_title);
        } else if (shortTimeZone.equals(TimeZone.getTimeZone("America/Los_Angeles").getDisplayName(false,TimeZone.SHORT))) {
            timeZone = getString(R.string.timezone_pacific_title);
        }

        onUpdateTimeZone(timeZone);

    }


    private void initView() {
        easternButton = (RadioButton) findViewById(R.id.button_timezone_eastern);
        buttonTimezoneCentral = (RadioButton) findViewById(R.id.button_timezone_central);
        buttonTimezoneMountain = (RadioButton) findViewById(R.id.button_timezone_mountain);
        buttonTimezonePacific = (RadioButton) findViewById(R.id.button_timezone_pacific);
        buttonTimezoneAlaska = (RadioButton) findViewById(R.id.button_timezone_alaska);
        buttonTimezoneArizona = (RadioButton) findViewById(R.id.button_timezone_arizona);
        buttonTimezoneChina = (RadioButton) findViewById(R.id.button_timezone_china);
        buttonTimezoneHawaii = (RadioButton) findViewById(R.id.button_timezone_hawaii);

        timezoneControl = (SegmentedGroup) findViewById(R.id.timezone_control);
        mTvTimeZone = (TextView) findViewById(R.id.tvTimeZone);

    }


    RadioGroup.OnCheckedChangeListener timeZoneListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            String timeZone;

            if (checkedId == R.id.button_timezone_eastern) {
                timeZone = getString(R.string.timezone_eastern_title);
            } else if (checkedId == R.id.button_timezone_central) {
                timeZone = getString(R.string.timezone_central_title);
            } else if (checkedId == R.id.button_timezone_mountain) {
                timeZone = getString(R.string.timezone_mountain_title);
            } else if (checkedId == R.id.button_timezone_pacific) {
                timeZone = getString(R.string.timezone_pacific_title);
            } else if (checkedId == R.id.button_timezone_alaska) {
                timeZone = getString(R.string.timezone_alaska_title);
            } else if (checkedId == R.id.button_timezone_hawaii) {
                timeZone = getString(R.string.timezone_hawaii_title);
            } else if (checkedId == R.id.button_timezone_arizona) {
                timeZone = getString(R.string.timezone_arizona_title);
            }  else if (checkedId == R.id.button_timezone_china) {
                timeZone = getString(R.string.timezone_china_title);
            }else {
                throw new IllegalStateException("unknown radioButton id for timezone");
            }

        }
    };

    public void onUpdateTimeZone(String timeZone) {
        timezoneControl.setOnCheckedChangeListener(null);
        if (timeZone.equals(getString(R.string.timezone_eastern_title))) {
            easternButton.setChecked(true);
        } else if (timeZone.equals(getString(R.string.timezone_central_title))) {
            buttonTimezoneCentral.setChecked(true);
        } else if (timeZone.equals(getString(R.string.timezone_mountain_title))) {
            buttonTimezoneMountain.setChecked(true);
        } else if (timeZone.equals(getString(R.string.timezone_pacific_title))) {
            buttonTimezonePacific.setChecked(true);
        } else if (timeZone.equals(getString(R.string.timezone_alaska_title))) {
            buttonTimezoneAlaska.setChecked(true);
        } else if (timeZone.equals(getString(R.string.timezone_hawaii_title))) {
            buttonTimezoneHawaii.setChecked(true);
        } else if (timeZone.equals(getString(R.string.timezone_arizona_title))) {
            buttonTimezoneArizona.setChecked(true);
        } else if (timeZone.equals(getString(R.string.timezone_china_title))) {
            buttonTimezoneChina.setChecked(true);
        } else {
            throw new IllegalStateException("unknown timezone came back from web service");
        }

        timezoneControl.setOnCheckedChangeListener(timeZoneListener);
    }

}
