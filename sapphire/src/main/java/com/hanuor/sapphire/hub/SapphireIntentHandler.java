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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanuor.sapphire.temporarydb.SuggestionTempDBHandler;
import com.hanuor.sapphire.utils.TypeChecker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

public class SapphireIntentHandler {
    private Context context;
    SuggestionTempDBHandler suggestionTempDBHandler;
    private TypeChecker tc;

    public SapphireIntentHandler(Context context){
        this.context = context;
        suggestionTempDBHandler = new SuggestionTempDBHandler(context);
        tc = new TypeChecker();

    }
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void setIntent( Intent intentObject) throws JsonProcessingException {
       /* String patth = context.getDir("data", 0) + "/" + "intent21.dat";
        NativeSerializer nativeSerializer = new NativeSerializer("hanuor", intentObject);
        ObjectBinder.serialize(nativeSerializer,patth);
        NativeSerializer nativeSerializer1 = ObjectBinder.deserialize(patth, NativeSerializer.class);
        Intent obj = (Intent) intentObject.clone();*/
       /* try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            byte[] bb =  b.toByteArray();

            Log.d("SapphireTagHEdd","dd "+bb.toString());
        } catch (IOException e) {
            Log.d("SapphireTagHEee",""+e.getMessage());
            e.printStackTrace();
        }
        final ObjectBinderProvider objectBinderProvider = new ObjectBinderProvider(context);

        if(intentObject!=null) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                 //  objectBinderProvider.store(intentObject);

                }
            });
            }
*/
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
            ObjectOutputStream objectOut = new ObjectOutputStream(gzipOut);
            objectOut.writeObject(intentObject);
            objectOut.close();
            byte[] bytes = baos.toByteArray();
            Log.d("Serializable",""+bytes);
        }catch(IOException i) {
            i.printStackTrace();
        }
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
            Log.d("SappsnoopDog",""+keys.size());
            while (it.hasNext()) {
                String key = it.next();
                Log.d("Sapptagdog","TYPE   " + bundle.get(key).toString());
                Log.d("NERVE",""+bundle.get(key).getClass().getAnnotations());


                String type = bundle.get(key).getClass().getSimpleName();
                Log.d("SappDogTAG",key + " OF TYPE " + type);
                switch (type) {
                    case "String":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "String[]":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Integer":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());

                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Double":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case  "double[]":

                        double[] newDouble = (double[]) bundle.get(key);
                        String fromDouble = Arrays.toString(newDouble);
                        makeInsideJsonArray.put(key,type+"-"+fromDouble);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "int[]":
                        int[] newArray = (int[]) bundle.get(key);
                        String fromArray = Arrays.toString(newArray);
                        makeInsideJsonArray.put(key,type+"-"+fromArray);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Boolean":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "boolean[]":

                        boolean[] newBool = (boolean[]) bundle.get(key);
                        String fromBool = Arrays.toString(newBool);
                        makeInsideJsonArray.put(key,type+"-"+fromBool);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Char":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "char[]":

                        char[] newChar = (char[]) bundle.get(key);
                        String fromChar = Arrays.toString(newChar);
                        makeInsideJsonArray.put(key,type+"-"+fromChar);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "CharSequence":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "charsequence[]":

                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Byte":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "byte[]":

                        byte[] newByte = (byte[]) bundle.get(key);
                        String fromByte = Arrays.toString(newByte);
                        makeInsideJsonArray.put(key,type+"-"+fromByte);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Float":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "float[]":

                        float[] newFloat = (float[]) bundle.get(key);
                        String fromFloat = Arrays.toString(newFloat);
                        makeInsideJsonArray.put(key,type+"-"+fromFloat);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Short":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "short[]":

                        short[] newShort = (short[]) bundle.get(key);
                        String fromShort = Arrays.toString(newShort);
                        makeInsideJsonArray.put(key,type+"-"+fromShort);
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Long":
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "long[]":

                        long[] newLong = (long[]) bundle.get(key);
                        String fromLong = Arrays.toString(newLong);
                        makeInsideJsonArray.put(key,type+"-"+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;

                    case "ArrayList":
                        ArrayList <Object> obj = (ArrayList<Object>) bundle.get(key);
                        Object[] objArr = obj.toArray();
                        if(objArr[0] instanceof Integer){
                            ArrayList<Integer> newIntegerArray = bundle.getIntegerArrayList(key);
                            makeInsideJsonArray.put(key,type+"Integer"+"-"+newIntegerArray.toString());

                        }else if(objArr[0] instanceof String){
                            ArrayList<String> newStringArray = bundle.getStringArrayList(key);

                            makeInsideJsonArray.put(key,type+"String"+"-"+newStringArray.toString());

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


       /* //begin here
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
        }*/

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
