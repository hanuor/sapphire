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
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class DynaliticService extends Service implements SensorEventListener{

    private Sensor mSensor;
    private SensorManager mSensorManager;
    private float _sensorMaximumRange;
    private long durationValue;
    private long startValue, endValue;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Skrillec","Working in an inside servuce");
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        _sensorMaximumRange = mSensor.getMaximumRange();
        Log.d("Skrillec",""+_sensorMaximumRange);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
            Log.d("Skrillec","HEYEYEYEYEYEYEYEYE");
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            if (_sensorMaximumRange == sensorEvent.values[0]) {
                //near
                long getD = getDuration();
                if(getD == 0){
                    startValue = System.currentTimeMillis();
                }else{
                    endValue = System.currentTimeMillis();
                    durationValue = startValue - endValue;
                    Log.d("5 minutes",""+durationValue + "  " + sensorEvent.values[0] +  " I am far");
                }
            }
            else {
                //far
                long getD = getDuration();
                if(getD == 0){
                    startValue = System.currentTimeMillis();
                    }else{
                    endValue = System.currentTimeMillis();
                    durationValue = startValue - endValue;

                    Log.d("5 minutes",""+durationValue + "  " + sensorEvent.values[0] +  " I am far");

                }
                Log.d("5 minutes",""+sensorEvent.accuracy + "  " + sensorEvent.values[0] +  " I am near");

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    private long getDuration(){
        if(startValue!=0){
            return durationValue;
        }else{
            return 0;
        }
    }
}
