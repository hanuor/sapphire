package com.hanuor.sapphire.hub;
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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanuor.sapphire.temporarydb.SuggestionTempDBHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SapphireIntentHandler {
    private Context context;
    SuggestionTempDBHandler suggestionTempDBHandler;

    public SapphireIntentHandler(Context context){
        this.context = context;
        suggestionTempDBHandler = new SuggestionTempDBHandler(context);

    }
    public void setIntent(Intent intentObject) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String getClassName = null;
        getClassName = intentObject.getComponent().getClassName();
        String getContextName = null;
        getContextName = context.getClass().getSimpleName() + ".this";
        HashMap<String, String> makeInsideJsonArray = new HashMap<String, String>();

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("className",getClassName);
        hashMap.put("context",getContextName);
        Bundle bundle = intentObject.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();

            while (it.hasNext()) {
                String key = it.next();
                makeInsideJsonArray.put(key,bundle.get(key).toString());
                hashMap.put(key, bundle.get(key).toString());
            }
        }
        String passArray = mapper.writeValueAsString(makeInsideJsonArray);
        hashMap.put("intentExtras",passArray);

        String intentString  = mapper.writeValueAsString(intentObject);
        Log.d("IntentString", "" + mapper.writeValueAsString(hashMap));
        StringBuilder a1S = new StringBuilder(mapper.writeValueAsString(hashMap));
        a1S.deleteCharAt(mapper.writeValueAsString(hashMap).length()-1);
        a1S.append(",");
        String s1t = a1S.toString();

        StringBuilder sb = new StringBuilder(intentString);
        sb.deleteCharAt(0);
        String retrString = sb.toString();
        StringBuilder newS = new StringBuilder();
        newS.append(s1t);
        newS.append(retrString);
        Log.d("Intentation",""+ newS.toString());


        //begin here
        try {
            JSONObject jsonObject = new JSONObject(newS.toString());

            String toArray = jsonObject.get("intentExtras").toString();
            Log.d("Sapp[][]",""+toArray);

            JSONObject issueObj = new JSONObject(toArray);
            for(int i = 0; i< issueObj.length() ; i++) {
                Log.e("Sapp[]][]", "Key = " + issueObj.names().getString(i) + " value = " + issueObj.get(issueObj.names().getString(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void saveIntent(String keyTag, Intent intent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

            String jsonString  = mapper.writeValueAsString(intent);
            Log.d("SappHeya",""+jsonString);
        //Intent bj = mapper.readValue(jsonString, Intent.class);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("a","aa");
        hashMap.put("b","lalala");
        ArrayList<String> m = new ArrayList<String>();
        m.add("frost");
        m.add("girl");
        m.add("Bumblebee");
        hashMap.put("c",m.toString());
        Log.d("SappTestTag", ""+ mapper.writeValueAsString(hashMap));

        //  String saveIntentthroughString = ToStringBuilder.reflectionToString(intent);
       // suggestionTempDBHandler.insertData(keyTag, saveIntentthroughString);
    }

    public void retrieveIntentData(String keyTag){

        Log.d("SapphireSuggestion","" + suggestionTempDBHandler.retrieveIntentData(keyTag));

    }
}
