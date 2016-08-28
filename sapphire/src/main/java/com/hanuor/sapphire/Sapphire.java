package com.hanuor.sapphire;
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

import com.hanuor.client.Client;
import com.hanuor.sapphire.hub.SapphireApi;
import com.hanuor.sapphire.temporarydb.Differentiator;
import com.hanuor.sapphire.utils.InformationHandler;
import com.hanuor.sapphire.utils.RuntimeHandler;
import com.hanuor.sapphire.utils.Utility;

import de.greenrobot.event.EventBus;

public class Sapphire {

    private static Differentiator differentiator = new Differentiator();
    private static String questappkey;
    private static RuntimeHandler runtimeHandler;
    private static EventBus bus = EventBus.getDefault();


    public Sapphire(){

    }
    volatile static SapphireApi mconnect =  null;
    public static SapphireApi with(Context context){

        mconnect = new SapphireApi(context);
        mconnect.setInstance(mconnect);

        return mconnect;
    }

    public static void initialize(Context ctx){
        Utility.throwExceptionIfNullOrBlank(ctx, "context");
        ctx = ctx;
        differentiator.setManage(false);

    }
    public static boolean initialize(Context context, String appKeyID, String keySecret, String validator){
        Utility.throwExceptionIfNullOrBlank(context, "context");
        Utility.throwExceptionIfNullOrBlank(appKeyID, "appKey");
        runtimeHandler = new RuntimeHandler();
        runtimeHandler.setKey_ID(appKeyID);
        runtimeHandler.setKey_secret(keySecret);
        runtimeHandler.setaBoolean(validator);
        bus.postSticky(new InformationHandler(runtimeHandler.getKey_ID(), runtimeHandler.getKey_secret(), runtimeHandler.getaBoolean()));

        double mm = Client.test();
        questappkey = appKeyID;
        //check if key matches to the key stored in Database
        //if else statement
        differentiator.setManage(true);
        if(questappkey.equalsIgnoreCase("hanuor")){
            return true;
     }else{
            return false;
        }
    }
}
