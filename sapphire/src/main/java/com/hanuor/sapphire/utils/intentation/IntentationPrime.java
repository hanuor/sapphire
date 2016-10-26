package com.hanuor.sapphire.utils.intentation;
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
import com.hanuor.container.LibraryDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IntentationPrime {

    public String intentToJSON(Context con,Intent intent) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String getClassName = null;
        getClassName = intent.getComponent().getClassName();
        String getContextName = null;
        getContextName = con.getClass().getName() + ".this";
        HashMap<String, String> makeInsideJsonArray = new HashMap<String, String>();

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("className",getClassName);
        hashMap.put("context",getContextName);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();
            Log.d("SappsnoopDog",""+keys.size());
            while (it.hasNext()) {
                String key = it.next();

                Log.d("Sapptagdog","TYPE   " + bundle.get(key).toString());
                Log.d("NERVE",""+bundle.get(key).getClass().getAnnotations());


                String type = bundle.get(key).getClass().getSimpleName();
                Log.d("SappDogTAG",key + " OF TYPE " + type);
                switch (type) {
                    case "String":
                        makeInsideJsonArray.put(key,type+ LibraryDatabase.JSONSEPERATOR +bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "String[]":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Integer":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());

                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Double":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case  "double[]":

                        double[] newDouble = (double[]) bundle.get(key);
                        String fromDouble = Arrays.toString(newDouble);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromDouble);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "int[]":
                        int[] newArray = (int[]) bundle.get(key);
                        String fromArray = Arrays.toString(newArray);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromArray);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Boolean":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "boolean[]":

                        boolean[] newBool = (boolean[]) bundle.get(key);
                        String fromBool = Arrays.toString(newBool);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromBool);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Char":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "char[]":

                        char[] newChar = (char[]) bundle.get(key);
                        String fromChar = Arrays.toString(newChar);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromChar);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "CharSequence":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "charsequence[]":

                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Byte":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "byte[]":

                        byte[] newByte = (byte[]) bundle.get(key);
                        String fromByte = Arrays.toString(newByte);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromByte);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Float":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "float[]":

                        float[] newFloat = (float[]) bundle.get(key);
                        String fromFloat = Arrays.toString(newFloat);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromFloat);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Short":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "short[]":

                        short[] newShort = (short[]) bundle.get(key);
                        String fromShort = Arrays.toString(newShort);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromShort);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Long":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "long[]":

                        long[] newLong = (long[]) bundle.get(key);
                        String fromLong = Arrays.toString(newLong);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;

                    case "ArrayList":
                        ArrayList<Object> obj = (ArrayList<Object>) bundle.get(key);
                        Object[] objArr = obj.toArray();
                        if(objArr[0] instanceof Integer){
                            ArrayList<Integer> newIntegerArray = bundle.getIntegerArrayList(key);
                            makeInsideJsonArray.put(key,type+"Integer"+LibraryDatabase.JSONSEPERATOR+newIntegerArray.toString());

                        }else if(objArr[0] instanceof String){
                            ArrayList<String> newStringArray = bundle.getStringArrayList(key);

                            makeInsideJsonArray.put(key,type+"String"+LibraryDatabase.JSONSEPERATOR+newStringArray.toString());

                        }
                        break;

                    default:
                        // whatever
                }

                hashMap.put(key, bundle.get(key).toString());
            }
        }
        String passArray = mapper.writeValueAsString(makeInsideJsonArray);
        hashMap.put("intentExtras",passArray);
        Log.d("GOGTAD",""+passArray);

        String intentString  = mapper.writeValueAsString(intent);
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
        Log.d("Insnsns",newS.toString());
        return newS.toString();
    }

    public Intent jsonToINTENT(String JSONString) throws JSONException {

        JSONObject jsonObject = new JSONObject(JSONString.toString());
        String toArray = jsonObject.get("intentExtras").toString();
        String contextName = "com.hanuor.sapphiredemo";
        String className = jsonObject.get("className").toString();
        Log.d("Insass",className.toString());

        Intent setIntent = new Intent();
        setIntent.setClassName(contextName, className);
        HashMap<String, String> extrasHash = new HashMap<String, String>();
        JSONObject issueObj = new JSONObject(toArray);
        for (int i = 0; i < issueObj.length(); i++) {
            extrasHash.put(issueObj.names().getString(i), issueObj.get(issueObj.names().getString(i)).toString());
        }
        Iterator it = extrasHash.entrySet().iterator();
        while (it.hasNext()) {
            //add conditions  and checks here

            Map.Entry pair = (Map.Entry) it.next();
            String currentKey = (String) pair.getKey();
            Log.d("HAHA",""+currentKey);
            String[] getValuethroughSplit = pair.getValue().toString().split(LibraryDatabase.JSONSEPERATOR);
            String dataType = getValuethroughSplit[0];
            String  value = (String) getValuethroughSplit[2];
            Log.d("Insamareen",getValuethroughSplit.length + " " +dataType+ " " +value.toString());
            switch (dataType) {
                case "String":
                    setIntent.putExtra(currentKey,(String) value);
                    break;
                case "String[]":
                    setIntent.putExtra(currentKey,value);
                    break;
                case "Integer":
                    setIntent.putExtra(currentKey,Integer.parseInt(value));
                    break;
                case "Double":
                    setIntent.putExtra(currentKey,Double.parseDouble(value));

                    break;
                case  "double[]":
                    setIntent.putExtra(currentKey,Double.parseDouble(value));
                    break;
                case "int[]":
                    setIntent.putExtra(currentKey,value);

                    break;
                case "Boolean":
                    setIntent.putExtra(currentKey,Boolean.valueOf(value));

                    break;
                case "boolean[]":
                    setIntent.putExtra(currentKey, value);

                    break;
                case "Char":
                    setIntent.putExtra(currentKey,value);

                    break;
                case "char[]":
                    setIntent.putExtra(currentKey,value.toCharArray());

                    break;
                case "CharSequence":
                    setIntent.putExtra(currentKey,(CharSequence) value);

                    break;
                case "Charsequence[]":
                    setIntent.putExtra(currentKey,value.toString());

                    break;
                case "Byte":
                    setIntent.putExtra(currentKey,Byte.valueOf(value));

                    break;
                case "byte[]":
                    setIntent.putExtra(currentKey,value);

                    break;
                case "Float":
                    setIntent.putExtra(currentKey,Float.valueOf(value));

                    break;
                case "float[]":
                    setIntent.putExtra(currentKey,value);

                    break;
                case "Short":
                    setIntent.putExtra(currentKey,Short.valueOf(value));

                    break;
                case "short[]":
                    setIntent.putExtra(currentKey,value);

                    break;
                case "Long":
                    setIntent.putExtra(currentKey,Long.valueOf(value));

                    break;
                case "long[]":
                    setIntent.putExtra(currentKey,value);

                    break;

                case "ArrayList":
                   /* ArrayList <String> obj = (ArrayList<String>) value;
                    Object[] objArr = obj.toArray();
                    if(objArr[0] instanceof Integer){
                        setIntent.putIntegerArrayListExtra(currentKey,(ArrayList<Integer>) value);
                    }else if(objArr[0] instanceof String){
                        setIntent.putIntegerArrayListExtra(currentKey,(ArrayList<Integer>) value);
                    }*/
                    break;

                default:
                    // whatever
            }
        }
        return setIntent;
    }

}
