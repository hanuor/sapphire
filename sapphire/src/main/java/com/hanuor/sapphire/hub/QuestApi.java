package com.hanuor.sapphire.hub;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shantanu Johri on 29-07-2016.
 */
public class QuestApi {
    public static void setforImageView(Context context, ImageView imgview, String tag){

    }
    public static void setforTextView(Context context, TextView txtview, String tag){

    }
    //public static void setforButton(Context context, Button btton, String tag){

    //}
    public static void setforButton(Context context, String key, Intent intent, String tag, Parcelable parcelable){
        //write algorithm here
        intent.putExtra(tag, parcelable);
        context.startActivity(intent);
    }
    public static void setforButton(Context context, String key){
        //neural netwok here
    }

}
