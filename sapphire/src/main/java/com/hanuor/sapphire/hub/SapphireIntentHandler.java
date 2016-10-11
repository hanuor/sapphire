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

import java.util.Iterator;
import java.util.Set;

public class SapphireIntentHandler {
    private Context context;
    public SapphireIntentHandler(Context context){
        this.context = context;
    }
    public void setIntent(Intent intentObject){
        Bundle bundle = intentObject.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();
            Log.e("SapphireIntentRes","Dumping Intent start");
            while (it.hasNext()) {
                String key = it.next();
                Log.e("SapphireIntentRes","[" + key + "=" + bundle.get(key)+"]");
            }
            Log.e("SapphireIntentRes","Dumping Intent end");
        }
    }
}
