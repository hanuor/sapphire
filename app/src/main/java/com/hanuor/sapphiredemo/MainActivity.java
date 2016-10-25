package com.hanuor.sapphiredemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanuor.sapphire.Sapphire;
import com.hanuor.sapphire.hub.SapphireIntentHandler;
import com.hanuor.sapphire.hub.SuggestionView;

import java.util.ArrayList;
import java.util.Calendar;

import de.greenrobot.event.EventBus;


public class MainActivity extends AppCompatActivity {
    Button but;
    DemoObject demoObject;
    ImageView img, img2;
    SuggestionView suggestionView;
    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but  = (Button) findViewById(R.id.button);
        suggestionView = (SuggestionView) findViewById(R.id.suggest);
       // suggestionView.setUPSuggestion(MainActivity.this, getResources().getDrawable(R.drawable.email));
        Sapphire.initialize(MainActivity.this,"asas","bbb");
        //for testing purpose
        Calendar today = Calendar.getInstance();
        int hour = today.get(Calendar.HOUR);
        int min = today.get(Calendar.MINUTE);
        Log.d("Sapphire",""+hour+min);
        final ArrayList<String> m = new ArrayList<String>();
        final ArrayList<ImageView> arrayOfImgViews = new ArrayList<ImageView>();
        //m.add("b00000");
        m.add("frost");
        m.add("girl");
        m.add("Bumblebee");
        img = (ImageView) findViewById(R.id.img);
        img2 = (ImageView) findViewById(R.id.img2);
        Bitmap icon1 = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.lp);
        Bitmap icon2  = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.email);
        img.setImageBitmap(icon1);
        img2.setImageBitmap(icon2);
        img.setTag("frost");
        img2.setTag("girl");


        arrayOfImgViews.add(img);
        arrayOfImgViews.add(img2);
        Sapphire.with(MainActivity.this).registerImageViews(arrayOfImgViews);

        Bitmap bitmap = ((BitmapDrawable)img2.getDrawable()).getBitmap();
        Log.d("Sappitttt",""+bitmap.toString());
        final ArrayList<ImageView> imageViewArrayList = new ArrayList<ImageView>();
        imageViewArrayList.add(img);
        imageViewArrayList.add(img2);

        Sapphire.with(MainActivity.this).registerTags(m);

        demoObject = new DemoObject("Quest for android");
        bus.postSticky(new HelloWorld("Hellow orld"));
        HelloWorld stickyEvent = EventBus.getDefault().getStickyEvent(HelloWorld.class);
        // Better check that an event was actually posted before

        double mf = 0.87;

        if(stickyEvent != null) {
        // "Consume" the sticky even
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Sapphire.with(MainActivity.this).gain(img.getTag(), suggestionView);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Sapphire.with(MainActivity.this).gain(img2.getTag());
                //Sapphire.with(MainActivity.this).check(img);

//                Sapphire.with(MainActivity.this).gain(img2.getTag(), suggestionView);
                }
        });
       but.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View view) {
                //Sapphire.with(MainActivity.this).registerImageViews(arrayOfImgViews);
                //Sapphire.with(MainActivity.this).addTags(m);
                Intent ms = new Intent(MainActivity.this, ReceiveActivity.class);

                byte bmm = 90;
                float bmx = 33.43f;
                int bbb = 321;
                int[] mi  = {21,23};
                CharSequence cs = "string";
                short dd = 32;
                ArrayList<Integer> gg = new ArrayList<Integer>();
                gg.add(322);
                gg.add(565);

                boolean[] aar = {true,false};
                ms.putIntegerArrayListExtra("JOKER",gg);
                ms.putStringArrayListExtra("3",m);
                ms.putExtra("vamos","SSSS");
                ms.putExtra("VV",bbb);
                ms.putExtra("sSS", 43.09);
                ms.putExtra("Dses", bmx);
                ms.putExtra("bomb",bmm);
                ms.putExtra("ASSDD",mi);
                ms.putExtra("SA",3.3f);
                ms.putExtra("sdaa",cs);
                ms.putExtra("ASSS",dd);
                SapphireIntentHandler sapphireIntentHandler = new SapphireIntentHandler(MainActivity.this);
                try {
                    sapphireIntentHandler.setIntent(ms);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                sapphireIntentHandler.retrieveIntentData("Vamos");
                //Sapphire.with(MainActivity.this).gain(img.getTag(), suggestionView, ms);
               // startActivity(ms);
            }
                      });
    }





}
