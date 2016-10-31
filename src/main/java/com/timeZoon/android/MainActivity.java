package com.timeZoon.android;

import android.app.AlarmManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
    ProgressBar proBar ;

    TimeZone mTimezone;

    private final String TIME_ZONE_CHINA = "Asia/Shanghai";
    private final String TIME_ZONE_ALASKA = "America/Anchorage";
    private final String TIME_ZONE_ARIZONA = "America/Phoenix";
    private final String TIME_ZONE_CENTRAL = "America/Chicago";
    private final String TIME_ZONE_EASTERN = "America/New_York";
    private final String TIME_ZONE_HAWAII = "Pacific/Honolulu";
    private final String TIME_ZONE_MOUNTAIN = "America/Denver";
    private final String TIME_ZONE_PACIFIC = "America/Los_Angeles";

    private String mBeforeTimeidStr;


    /**
     * Distinguish time zones by short identifiers , default timeZone is Eastern timeZone.
     *
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
        mTimezone = TimeZone.getDefault();

        mTvTimeZone.setText(mTimezone.getDisplayName(false, TimeZone.LONG));

        Log.e(TAG, mTimezone.getID());

        Log.e(TAG, TimeZone.getTimeZone(TIME_ZONE_CHINA).getDisplayName(false, TimeZone.SHORT) + " : " + TimeZone.getTimeZone(TIME_ZONE_CHINA).getID());
        Log.e(TAG, TimeZone.getTimeZone(TIME_ZONE_ALASKA).getDisplayName(false, TimeZone.SHORT) + " : " + TimeZone.getTimeZone(TIME_ZONE_ALASKA).getID());
        Log.e(TAG, TimeZone.getTimeZone(TIME_ZONE_ARIZONA).getDisplayName(false, TimeZone.LONG) + " : " + TimeZone.getTimeZone(TIME_ZONE_ARIZONA).getID());
        Log.e(TAG, TimeZone.getTimeZone(TIME_ZONE_CENTRAL).getDisplayName(false, TimeZone.SHORT) + " : " + TimeZone.getTimeZone(TIME_ZONE_CENTRAL).getID());
        Log.e(TAG, TimeZone.getTimeZone(TIME_ZONE_EASTERN).getDisplayName(false, TimeZone.SHORT) + " : " + TimeZone.getTimeZone(TIME_ZONE_EASTERN).getID());
        Log.e(TAG, TimeZone.getTimeZone(TIME_ZONE_HAWAII).getDisplayName(false, TimeZone.SHORT) + " : " + TimeZone.getTimeZone(TIME_ZONE_HAWAII).getID());
        Log.e(TAG, TimeZone.getTimeZone(TIME_ZONE_MOUNTAIN).getDisplayName(false, TimeZone.LONG) + " : " + TimeZone.getTimeZone("US/Mountain").getID());
        Log.e(TAG, TimeZone.getTimeZone(TIME_ZONE_PACIFIC).getDisplayName(false, TimeZone.SHORT) + " : " + TimeZone.getTimeZone(TIME_ZONE_PACIFIC).getID());

        checkTimeZone(mTimezone.getID());
    }

    public void checkTimeZone(String timeZoneid) {

        mBeforeTimeidStr = timeZoneid;
        String timeZone = getString(R.string.timezone_eastern_title);
        if (timeZoneid.equals(TimeZone.getTimeZone("Asia/Shanghai").getID())) {
            timeZone = getString(R.string.timezone_china_title);
        } else if (timeZoneid.equals(TimeZone.getTimeZone("America/Anchorage").getID())) {
            timeZone = getString(R.string.timezone_alaska_title);
        } else if (timeZoneid.equals(TimeZone.getTimeZone("America/Phoenix").getID())) {
            timeZone = getString(R.string.timezone_arizona_title);
        } else if (timeZoneid.equals(TimeZone.getTimeZone("America/Chicago").getID())) {
            timeZone = getString(R.string.timezone_central_title);
        } else if (timeZoneid.equals(TimeZone.getTimeZone("America/New_York").getID())) {
            timeZone = getString(R.string.timezone_eastern_title);
        } else if (timeZoneid.equals(TimeZone.getTimeZone("Pacific/Honolulu").getID())) {
            timeZone = getString(R.string.timezone_hawaii_title);
        } else if (timeZoneid.equals(TimeZone.getTimeZone("America/Denver").getID())) {
            timeZone = getString(R.string.timezone_mountain_title);
        } else if (timeZoneid.equals(TimeZone.getTimeZone("America/Los_Angeles").getID())) {
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
        proBar = (ProgressBar) findViewById(R.id.progressBar);

    }


    RadioGroup.OnCheckedChangeListener timeZoneListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            String timeZone;
            String changeTimeZoneStr;

            if (checkedId == R.id.button_timezone_eastern) {
                timeZone = getString(R.string.timezone_eastern_title);
                changeTimeZoneStr = TIME_ZONE_EASTERN;
            } else if (checkedId == R.id.button_timezone_central) {
                timeZone = getString(R.string.timezone_central_title);
                changeTimeZoneStr = TIME_ZONE_CENTRAL;
            } else if (checkedId == R.id.button_timezone_mountain) {
                timeZone = getString(R.string.timezone_mountain_title);
                changeTimeZoneStr = TIME_ZONE_MOUNTAIN;
            } else if (checkedId == R.id.button_timezone_pacific) {
                timeZone = getString(R.string.timezone_pacific_title);
                changeTimeZoneStr = TIME_ZONE_PACIFIC;
            } else if (checkedId == R.id.button_timezone_alaska) {
                timeZone = getString(R.string.timezone_alaska_title);
                changeTimeZoneStr = TIME_ZONE_ALASKA;
            } else if (checkedId == R.id.button_timezone_hawaii) {
                timeZone = getString(R.string.timezone_hawaii_title);
                changeTimeZoneStr = TIME_ZONE_HAWAII;
            } else if (checkedId == R.id.button_timezone_arizona) {
                timeZone = getString(R.string.timezone_arizona_title);
                changeTimeZoneStr = TIME_ZONE_ARIZONA;
            } else if (checkedId == R.id.button_timezone_china) {
                timeZone = getString(R.string.timezone_china_title);
                changeTimeZoneStr = TIME_ZONE_CHINA;
            } else {
                throw new IllegalStateException("unknown radioButton id for timezone");
            }

            changeSystemTimeZone(changeTimeZoneStr);

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

    private void changeSystemTimeZone(String changeTimeZoneStr) {
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setTimeZone(changeTimeZoneStr);

        checkSkipNewTimeZone(changeTimeZoneStr);
    }

    //TODO can use wait dialog ,
    private void checkSkipNewTimeZone(String timeZone) {
        timezoneControl.setOnCheckedChangeListener(null);
        proBar.setVisibility(View.VISIBLE);
        new CheckTimeZone().execute(timeZone);
    }


    public class CheckTimeZone extends AsyncTask<String, Void, Boolean> {
        private String timeZoneid;
        private String timeZone;

        @Override
        protected Boolean doInBackground(String... strings) {
            timeZone = strings[0];
            timeZoneid = TimeZone.getTimeZone(timeZone).getID();
            int count = 20;
            boolean isSuccess = false;
            while (count > 0 && !isSuccess) {

                String timezoneid = TimeZone.getDefault().getID();
                if (timezoneid != null && timezoneid.equals(timeZoneid)) {
                    isSuccess = true;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                count--;
            }

            return isSuccess;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                mBeforeTimeidStr = timeZoneid;
                checkTimeZone(timeZoneid);
                Log.i(TAG, "SUCCESS");
            } else {
                checkTimeZone(mBeforeTimeidStr);
                Log.i(TAG, "FAIl ");
            }
            proBar.setVisibility(View.GONE);
            TimeZone timezone = TimeZone.getDefault();
            mTvTimeZone.setText(timezone.getDisplayName(false, TimeZone.LONG));
            timezoneControl.setOnCheckedChangeListener(timeZoneListener);
        }
    }


}
