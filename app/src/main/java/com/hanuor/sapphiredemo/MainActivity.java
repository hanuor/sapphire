package com.hanuor.sapphiredemo;

import android.content.Intent;
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
        m.add("sir");
        m.add("dweney");
        m.add("Inca");
        m.add("Sapphire");
        //Sapphire.with(MainActivity.this).registerTags(m);
        Sapphire.with(MainActivity.this).addTags(m);
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
                 Intent myintent = new Intent(MainActivity.this, ReceiveActivity.class);
                startActivity(myintent);
             //   QuestApi.setforButton(MainActivity.this, "key", myintent, "parse", demoObject);
            }
        });
    }





}
