package com.hanuor.sapphire.dynalitic;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DynaliticService extends Service implements SensorEventListener {

    private Sensor mSensor;
    private SensorManager mSensorManager;
    private float _sensorMaximumRange;
    private long durationValue;
    // constant
    public static final long NOTIFY_INTERVAL = 1000; // 10 seconds

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;

    private long startValue, endValue;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        _sensorMaximumRange = mSensor.getMaximumRange();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            if (_sensorMaximumRange == sensorEvent.values[0]) {
                //near
                long getD = getDuration();
                if (getD == 0) {
                    startValue = System.currentTimeMillis();
                } else {
                    endValue = System.currentTimeMillis();
                    durationValue = startValue - endValue;
                    Log.d("5 minutes", "" + durationValue + "  " + sensorEvent.values[0] + " I am far");
                }
            } else {
                //far
                long getD = getDuration();
                if (getD == 0) {
                    startValue = System.currentTimeMillis();
                } else {
                    endValue = System.currentTimeMillis();
                    durationValue = startValue - endValue;

                    Log.d("5 minutes", "" + durationValue + "  " + sensorEvent.values[0] + " I am far");

                }
                Log.d("5 minutes", "" + sensorEvent.accuracy + "  " + sensorEvent.values[0] + " I am near");

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private long getDuration() {
        if (startValue != 0) {
            return durationValue;
        } else {
            return 0;
        }
    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // display toast
                 Log.d("myatlantis","Service "+getDateTime());
                    if(getDateTime().equals("[3:00:00]")){
                        //Start uploading Docs
                        

                    }

                }

            });
        }

        private String getDateTime() {
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss]");
            return sdf.format(new Date());
        }
    }
}
