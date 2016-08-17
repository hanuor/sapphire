package com.hanuor.sapphiredemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hanuor.sapphire.Sapphire;
import com.hanuor.sapphire.hub.QuestApi;

import java.util.ArrayList;
import java.util.Calendar;

//©Hanuor, Inc. All rights reserved.

public class MainActivity extends AppCompatActivity {
    Button but;
    DemoObject demoObject;
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
        m.add("botham");
        m.add("viv");
        m.add("amey");
        m.add("Money");
        m.add("Pearl");
        //Sapphire.with(MainActivity.this).registerTags(m);
        //Client mC = new Client(MainActivity.this);

        // mC.makeJsonString(m);
        Toast.makeText(MainActivity.this,"Message "+Sapphire.initialize(MainActivity.this,"hanuor"),Toast.LENGTH_SHORT).show();
        demoObject = new DemoObject("Quest for android");
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, ReceiveActivity.class);
                QuestApi.setforButton(MainActivity.this, "key", myintent, "parse", demoObject);
            }
        });
    }
}
