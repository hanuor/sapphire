package com.hanuor.sapphiredemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hanuor.sapphire.Sapphire;

import java.util.ArrayList;
import java.util.Calendar;

import de.greenrobot.event.EventBus;


public class MainActivity extends AppCompatActivity {
    Button but;
    DemoObject demoObject;
    ImageView img, img2;

    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but  = (Button) findViewById(R.id.button);
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
        //Sapphire.with(MainActivity.this).registerTags(m);


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
        if(stickyEvent != null) {
        // "Consume" the sticky even
            Toast.makeText(MainActivity.this, "Yess", Toast.LENGTH_SHORT).show();
        // Now do something with it
        }

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Sapphire.with(MainActivity.this).gain(img2.getTag());
                }
        });
       but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Sapphire.with(MainActivity.this).registerImageViews(arrayOfImgViews);
                Sapphire.with(MainActivity.this).addTags(m);
                Intent ms = new Intent(MainActivity.this, ReceiveActivity.class);
                startActivity(ms);
            }
                      });
    }





}
