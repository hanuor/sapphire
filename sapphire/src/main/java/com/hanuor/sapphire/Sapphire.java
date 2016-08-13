package com.hanuor.sapphire;

import android.content.Context;

import com.hanuor.sapphire.temporarydb.Differentiator;
import com.hanuor.sapphire.utils.Utility;

/**
 * Created by Shantanu Johri on 28-07-2016.
 */
public class Sapphire {
    Context ctx;
    static Differentiator differentiator = new Differentiator();
    private static String questappkey;

    public Sapphire(){

    }
    public static void initialize(Context ctx){
        Utility.throwExceptionIfNullOrBlank(ctx, "context");
        ctx = ctx;
        differentiator.setManage(false);

    }
    public static boolean initialize(Context ctx, String appKey){
        Utility.throwExceptionIfNullOrBlank(ctx, "context");
        Utility.throwExceptionIfNullOrBlank(appKey, "appKey");
        ctx = ctx;
        questappkey = appKey;
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
