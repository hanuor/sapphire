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
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanuor.sapphire.temporarydb.SuggestionTempDBHandler;
import com.hanuor.sapphire.utils.TypeChecker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SapphireIntentHandler {
    private Context context;
    SuggestionTempDBHandler suggestionTempDBHandler;
    private TypeChecker tc;

    public SapphireIntentHandler(Context context){
        this.context = context;
        suggestionTempDBHandler = new SuggestionTempDBHandler(context);
        tc = new TypeChecker();

    }
    public void setIntent(Intent intentObject) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String getClassName = null;
        getClassName = intentObject.getComponent().getClassName();
        String getContextName = null;
        getContextName = context.getClass().getName() + ".this";
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
            String contextName = jsonObject.get("context").toString();
            String className = jsonObject.get("className").toString();
            HashMap<String, String> extrasHash = new HashMap<String, String>();
            Intent setIntent = new Intent(contextName, Uri.parse(className));

            Log.d("Sapp[][]",""+toArray);

            JSONObject issueObj = new JSONObject(toArray);
            for(int i = 0; i< issueObj.length() ; i++) {
                extrasHash.put(issueObj.names().getString(i), issueObj.get(issueObj.names().getString(i)).toString());
            }
            Iterator it = extrasHash.entrySet().iterator();
            while (it.hasNext()) {
                //add conditions  and checks here

                Map.Entry pair = (Map.Entry)it.next();
                String currentKey = (String) pair.getKey();
                int xi;
                boolean startsWithspchar = false;
                boolean isarrayInt = false;
                boolean isInt = false;
                boolean isFloat = false;
                boolean isDouble = false;
                boolean isByte = false;
                boolean isChar = false;
                boolean isShort = false;
                boolean isLong = false;
                double dbxi = 0.0;
                float flxi = 0f;
                short shxi = 0;
                if(pair.getValue().toString().startsWith("[")){
                    startsWithspchar = true;
                }
                try {
                     xi = Integer.parseInt(pair.getValue().toString());
                    isInt = true;
                } catch (NumberFormatException e) {
                    //not int
                    xi = -901335572;
                    try {
                        dbxi = Double.parseDouble(pair.getValue().toString());
                        isDouble = true;
                    } catch (NumberFormatException e1) {
                        //not double
                        dbxi = -0.900092;
                        try {
                            flxi = Float.parseFloat(pair.getValue().toString());
                        } catch (NumberFormatException e2) {
                            //not flaot
                            flxi = -9.32f;
                            isFloat = true;
                            try {
                                shxi = Short.parseShort(pair.getValue().toString());
                                isShort = true;
                            } catch (NumberFormatException e3) {
                               shxi = -1099;
                                e3.printStackTrace();
                            }
                            Log.d("SappppEEf",""+e.toString());
                            e2.printStackTrace();
                        }

                        Log.d("SappppEEd",""+e.toString());
                        e1.printStackTrace();
                    }

                    Log.d("SappppEE",""+e.toString());
                    e.printStackTrace();
                }

                if(isInt){
                    Log.d("SapppInt","Int" + xi);
                    setIntent.putExtra(currentKey, xi);
                }else if (isDouble){

                    Log.d("SapppDouble","Double"+dbxi);
                    setIntent.putExtra(currentKey, dbxi);
                }else if (isFloat){
                    Log.d("SapppFloat","Float"+flxi);
                    setIntent.putExtra(currentKey, flxi);
                }
                if(xi<0 && !startsWithspchar){
                    //must be a string  currentKey,pair.getValue().toString()
                    //setIntent.putExtra()
                    Log.d("Sapppddd",""+setIntent.toString());
                }
                if(xi<0 && startsWithspchar){
                    //must be an array
                    String retString  = pair.getValue().toString().substring(1, pair.getValue().toString().length()-1);
                    String retStringarr[] = retString.split(",");
                    ArrayList<Integer> arrayLister = new ArrayList<>();

                    try {
                        int num = Integer.parseInt(retStringarr[0]);
                        isarrayInt = true;

                        for(int i = 0;i < retStringarr.length;i++)
                        {
                            arrayLister.add(Integer.parseInt(retStringarr[i]));
                        }
                    } catch (NumberFormatException e) {
                        //must be a string
                        e.printStackTrace();
                    }
                    if(!isarrayInt){
                        ArrayList<String> arrayList = new ArrayList<>();
                        Collections.addAll(arrayList, retStringarr);
                        setIntent.putStringArrayListExtra("sa",arrayList);
                        Log.d("Sapppddis[h",arrayList.toString());
                    }else{
                        Log.d("Sapppddis[h",arrayLister.toString());
                        setIntent.putIntegerArrayListExtra("saa",arrayLister);
                    }


                    Log.d("Sapppddish",setIntent.toString());

                }
                Log.d("SappppResult",""+currentKey + " is an integer Value" + xi);
                Log.d("SappppResult",currentKey + " " + pair.getValue() + " NOT");

                System.out.println(pair.getKey() + " = " + pair.getValue());
                it.remove();
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
