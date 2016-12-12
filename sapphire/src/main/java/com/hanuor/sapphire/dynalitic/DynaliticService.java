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

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hanuor.sapphire.infoGet.TimeStampGS;
import com.hanuor.sapphire.temporarydb.StartTimeStoreDB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DynaliticService extends Service implements SensorEventListener {

    private Sensor mSensor;
    private SensorManager mSensorManager;
    private float _sensorMaximumRange;
    private long durationValue;
    private StartTimeStoreDB startTimeStoreDB;
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("doobie","smoke weed");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("troySegan","Service up and running");

        startTimeStoreDB = new StartTimeStoreDB(this);
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
        TimeStampGS timeStampGS = new TimeStampGS();
        @Override
        public void run() {
            // run on another thread

            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                    List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo = am.getRunningAppProcesses();
                    List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);

                    String currentRunningActivityName = taskInfo.get(0).topActivity.getPackageName();
                    if(currentRunningActivityName.equals("com.hanuor.sapphiredemo")){
                       if(timeStampGS.getSetTime()!=0){

                        }else{
                           Log.d("nucleyaTimeStapm",""+timeStampGS.getSetTime());
                            timeStampGS.setTime(fetchLongTime());
                           Log.d("nucleyaTimeStapm",""+timeStampGS.getSetTime());
                       }
                        final long starttime = fetchLongTime();
                       if(starttime == timeStampGS.getSetTime()) {
                            //startTimeStoreDB.clearTimestampTable();
                           Log.d("nucleyaTimeStapm","tart " + starttime + "  " +timeStampGS.getSetTime());
                           startTimeStoreDB.addTimeStampToDB(timeStampGS.getSetTime());
                        }
                        //Log.d("nucleya","Current time - "+fetchLongTime() +"\r\n"+ " and start time" + timeStampGS.getSetTime());

                    }


                /*    for (int i = 0; i < runningAppProcessInfo.size(); i++) {
                        Log.d("nucleya"," HAH " + runningAppProcessInfo.get(i).processName);
                        if(runningAppProcessInfo.get(i).processName.equals("com.twango.me") ){
                            // Do you stuff
                            Log.d("nucleya","Launched");
                        }
                    }
                    Log.d("myatlantis","Service "+getDateTime());
                    if(getDateTime().equals("[3:00:00]")){
                        //Start uploading Docs
*/

                   // }

                }

            });
        }
        private String getTime(){
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss]");
            return sdf.format(new Date());
        }

        private String getDateandTime() {
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
            return sdf.format(new Date());
        }
        private long fetchLongTime(){
            return System.currentTimeMillis();
        }
    }
    private String getDateandTime() {
        // get date time in custom format
        SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
        return sdf.format(new Date());
    }
}
