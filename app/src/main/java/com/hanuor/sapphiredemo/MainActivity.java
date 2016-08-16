package com.hanuor.sapphiredemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hanuor.sapphire.Sapphire;
import com.hanuor.sapphire.hub.QuestApi;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button but;
    DemoObject demoObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but  = (Button) findViewById(R.id.button);
        Sapphire.initialize(MainActivity.this);
        ArrayList<String> m = new ArrayList<String>();
        m.add("botham");
        m.add("viv");
        m.add("amey");
        Sapphire.with(MainActivity.this).registerTags(m);
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
