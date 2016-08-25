package com.hanuor.sapphiredemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    ImageView ee;
    DemoObject demoObject;
    ImageView img, img2;

    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but  = (Button) findViewById(R.id.button);
        Sapphire.initialize(MainActivity.this);

        //for testing purpose
        Calendar today = Calendar.getInstance();
        int hour = today.get(Calendar.HOUR);
        int min = today.get(Calendar.MINUTE);
        Log.d("Sapphire",""+hour+min);
        ArrayList<String> m = new ArrayList<String>();
        //m.add("b00000");
        m.add("frost");
        m.add("girl");
        //Sapphire.with(MainActivity.this).registerTags(m);
        Sapphire.with(MainActivity.this).addTags(m);


        img = (ImageView) findViewById(R.id.img);
        img2 = (ImageView) findViewById(R.id.img2);
        Bitmap icon1 = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.frost);
        Bitmap icon2  = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.j);
        img.setImageBitmap(icon1);
        img.setImageBitmap(icon2);
        img.setTag("frost");
        img2.setTag("girl");

final ArrayList<ImageView> imageViewArrayList = new ArrayList<ImageView>();
        imageViewArrayList.add(img);
        imageViewArrayList.add(img2);



        Toast.makeText(MainActivity.this,"Message "+Sapphire.initialize(MainActivity.this,"hanuor"),Toast.LENGTH_SHORT).show();
        demoObject = new DemoObject("Quest for android");
        bus.postSticky(new HelloWorld("Hellow orld"));
        HelloWorld stickyEvent = EventBus.getDefault().getStickyEvent(HelloWorld.class);
        // Better check that an event was actually posted before
        if(stickyEvent != null) {
        // "Consume" the sticky event
            Toast.makeText(MainActivity.this, "Yess", Toast.LENGTH_SHORT).show();
        // Now do something with it
        }
       // but.setTag();
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sapphire.with(MainActivity.this).gain("sir");
                Sapphire.with(MainActivity.this).registerImageViews(imageViewArrayList);


//                Intent myintent = new Intent(MainActivity.this, ReceiveActivity.class);
  //              startActivity(myintent);
             //   QuestApi.setforButton(MainActivity.this, "key", myintent, "parse", demoObject);
            }
        });
    }





}
