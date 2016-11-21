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
import android.util.Log;

import com.hanuor.sapphire.hub.SuggestionView;
import com.hanuor.sapphire.temporarydb.SapphireDbManager;
import com.hanuor.sapphire.temporarydb.SapphireImgDbHelper;
import com.hanuor.sapphire.temporarydb.SapphirePrivateDB;
import com.hanuor.sapphire.temporarydb.TrainingTracker;
import com.hanuor.utils.GetDayUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/*
A one week training is needed here. This class is used to implement trained data when the whole week has been completed.*/
/*
we need to link sv to this engine in order to make functionality and demo ready.*/

public class StartEngineModulePrimary {
    private GetDayUtil getDayUtil;
    private TrainingTracker trainingTracker;
    private SuggestionView suggestionView;
    private SapphirePrivateDB sapphirePrivateDB;
    private SapphireDbManager sapphireDbManager;
    private SapphireImgDbHelper sapphireImgDbHelper;
/*
    SapphireprivateDB is for mapping events occuring on specific days.
*/
    private Context context;
    public StartEngineModulePrimary(Context ctx){
        this.context = ctx;
        sapphirePrivateDB = new SapphirePrivateDB(context);
        getDayUtil = new GetDayUtil();
        trainingTracker = new TrainingTracker(context);
        sapphirePrivateDB = new SapphirePrivateDB(context);
        sapphireDbManager = new SapphireDbManager(context);
        sapphireImgDbHelper = new SapphireImgDbHelper(context);
       // suggestionView = new SuggestionView(context);

    }
    public void startSuggestions(boolean decision){
        if(decision){
            //display
            trainingTracker.clearTable();
        trainingTracker.updateValue(getDayUtil.getDay());
         String  valmos = trainingTracker.queryTracker(getDayUtil.getDay(), true);
            Log.d("TrainingTrackerEngine",""+valmos);
           String getJsonDoc = sapphireDbManager.query();
            Log.d("Engine",""+getJsonDoc);
            try {
                JSONObject jsonArray = new JSONObject(getJsonDoc);
                Log.d("Enignee",""+jsonArray.length());
                Iterator<String> flavourI = jsonArray.keys();

                while (flavourI.hasNext()){
                    Log.d("Enigne Dum",""+flavourI.next());
                }

            } catch (JSONException e) {
                Log.d("EngHASSS",e.toString());
                e.printStackTrace();
            }


        }else{
            //keep on modelling
        }
    }
}
