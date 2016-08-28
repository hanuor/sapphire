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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hanuor.sapphire.utils.ExceptionHandler;
import com.hanuor.sapphire.utils.InformationHandler;
import com.hanuor.sapphire.utils.Utility;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
/*
There needs to be an imageview or some kind if we want to display it in the suggestion view box.
To do this we must have to pass the list of view objects and their intents/actions.
This is the main task that we need to do.
*/
public class SapphireApi {
    private Context context = null;
    private SapphireApi connect = null;
    private ArrayList<String> tags = null;
    private ArrayList<String> updateTags = null;
    private View mview = null;
    private ArrayList<String> listofTags = null;
    private String mgainTag = null;
    private ArrayList<ImageView> imageViews = null;
    private EventBus bus = EventBus.getDefault();
    private ClientConnect mclient;
    private InformationHandler stickyEvent;
    public SapphireApi(Context context){
        stickyEvent = EventBus.getDefault().getStickyEvent(InformationHandler.class);
        if(context == null){
            ExceptionHandler.writeError("Context cannot be null");
        }else{
             if(stickyEvent != null) {
                Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
                this.context = context;
                mclient = new ClientConnect(context);
            }else{
                Utility.throwRuntimeException();
            }
        }
    }
  public void setInstance(SapphireApi connect){
        this.connect = connect;
    }
    public SapphireApi registerTags(ArrayList<String> tags){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            connect.tags = tags;
            mclient.register(context, tags);
            return connect;
           }else{
            Utility.throwRuntimeException();
            return null;
        }

    }
    public SapphireApi addTags(ArrayList<String> tags){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            connect.updateTags = tags;
            mclient.update(context, tags);
            return connect;
        }else{
            Utility.throwRuntimeException();
            return null;
        }
    }
    public SapphireApi attachView(View view){
        //attach a view to show suggestions
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            connect.mview = view;
            return connect;
        }else{
            Utility.throwRuntimeException();
            return null;
        }

        }
    public SapphireApi gain(String tag){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            connect.mgainTag = tag;
            mclient.tagUpdate(tag);
            return connect;
        }else{
            Utility.throwRuntimeException();
            return null;
        }
    }
    public SapphireApi registerImageViews(Context context, ArrayList<ImageView>Views){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            connect.imageViews = Views;
            mclient.imageStore(context, Views);
            return connect;
        }else{
            Utility.throwRuntimeException();
            return null;
        }
     }
    public SapphireApi registerButtons(ArrayList<Button> buttons){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            return connect;
        }else{
            Utility.throwRuntimeException();
            return null;
        }
    }
}

