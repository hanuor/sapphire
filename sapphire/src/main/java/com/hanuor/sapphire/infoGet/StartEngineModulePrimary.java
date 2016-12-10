package com.hanuor.sapphire.infoGet;
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

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.hanuor.client.MaxValueEvaluator;
import com.hanuor.sapphire.hub.SuggestionView;
import com.hanuor.sapphire.temporarydb.SapphireDbManager;
import com.hanuor.sapphire.temporarydb.SapphireImgDbHelper;
import com.hanuor.sapphire.temporarydb.SapphirePrivateDB;
import com.hanuor.sapphire.temporarydb.TrainingTracker;
import com.hanuor.sapphire.utils.ImagesUtil;
import com.hanuor.utils.GetDayUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import static android.content.Context.SENSOR_SERVICE;

/*
A one week training is needed here. This class is used to implement trained data when the whole week has been completed.*/
/*
we need to link sv to this engine in order to make functionality and demo ready.*/

public class StartEngineModulePrimary implements SensorEventListener {
    private GetDayUtil getDayUtil;
    private TrainingTracker trainingTracker;
    private SuggestionView suggestionView;
    private SapphirePrivateDB sapphirePrivateDB;
    private SapphireDbManager sapphireDbManager;
    private SapphireImgDbHelper sapphireImgDbHelper;
    private ImagesUtil imagesUtil;
    private SuggestionView suggestionViewOb;

    private Sensor mSensor;
    private SensorManager mSensorManager;
    private float _sensorMaximumRange;
/*
    SapphireprivateDB is for mapping events occuring on specific days.
*/
    private Context context;
    public StartEngineModulePrimary(Context ctx, SuggestionView suggestionView1){
        this.context = ctx;
        sapphirePrivateDB = new SapphirePrivateDB(context);
        getDayUtil = new GetDayUtil();
        trainingTracker = new TrainingTracker(context);
        sapphirePrivateDB = new SapphirePrivateDB(context);
        sapphireDbManager = new SapphireDbManager(context);
        sapphireImgDbHelper = new SapphireImgDbHelper(context);
        imagesUtil = new ImagesUtil();
        this.suggestionViewOb = suggestionView1;
        Log.d("minutes","Re invent");
        mSensorManager = (SensorManager) ctx.getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        _sensorMaximumRange = mSensor.getMaximumRange();


    }
    public String startSuggestions(boolean decision){
        if(decision){
            trainingTracker.clearTable();
        trainingTracker.updateValue(getDayUtil.getDay());
         String  valmos = trainingTracker.queryTracker(getDayUtil.getDay(), true);

            try {
                HashMap<String, Double> stringDoubleHashMap = new HashMap<String, Double>();
                String getJsonDoc = sapphireDbManager.query();
                JSONObject jsonArray = new JSONObject(getJsonDoc);
                Iterator<String> flavourI = jsonArray.keys();
                while (flavourI.hasNext()){
                    String key = flavourI.next();
                    String value = (String) jsonArray.get(key);
                    stringDoubleHashMap.put(key, Double.valueOf(value));
                }
                //Notice that this returns the maximum node value. We also want a descending order name list.

                String vaAbbreviation = (String) MaxValueEvaluator.processHash(stringDoubleHashMap);
                byte[] slipstream = sapphireImgDbHelper.imgquery(vaAbbreviation);
               // suggestionView = new SuggestionView(context, imagesUtil.byteToBitmap(slipstream));
                suggestionViewOb.setUPSuggestion(context,imagesUtil.byteToBitmap(slipstream),1);
                return vaAbbreviation;

            } catch (Exception e) {
                e.printStackTrace();
                return null;

            }

        }else{
            //keep on modelling
            return null;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (_sensorMaximumRange == sensorEvent.values[0]) {
                //near
                Log.d("25 minutes",""+sensorEvent.accuracy + "  " + sensorEvent.values[0] +  " I am far");

            } else {
                //far
                Log.d("25 minutes",""+sensorEvent.accuracy + "  " + sensorEvent.values[0] +  " I am near");

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d("25 minutes","Accuracy changed");
    }
}
