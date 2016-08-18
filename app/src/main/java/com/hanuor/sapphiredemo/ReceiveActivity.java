package com.hanuor.sapphiredemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Shantanu Johri on 30-07-2016.
 */
public class ReceiveActivity extends AppCompatActivity {
    TextView name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive);
        name = (TextView) findViewById(R.id.name);
        Bundle b = getIntent().getExtras();
        if(b == null){
            Toast.makeText(ReceiveActivity.this, "Null", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ReceiveActivity.this, "Not null", Toast.LENGTH_SHORT).show();
           DemoObject obj = getIntent().getParcelableExtra("parse");
            String getInfo = obj.name;
            name.setText(getInfo);

        }
        }
}
