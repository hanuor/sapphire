package com.hanuor.sapphire;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hanuor.client.Client;
import com.hanuor.sapphire.hub.SapphireApi;
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
    public static boolean initialize(Context ctx, String appKey){
        Utility.throwExceptionIfNullOrBlank(ctx, "context");
        Utility.throwExceptionIfNullOrBlank(appKey, "appKey");
        double mm = Client.test();
        Log.d("Sapphire",""+ mm);
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
