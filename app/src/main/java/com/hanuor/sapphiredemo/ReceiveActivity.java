package com.hanuor.sapphiredemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;


/**
 * Created by Shantanu Johri on 30-07-2016.
 */
public class ReceiveActivity extends AppCompatActivity {
    TextView name;

    private EventBus bus = EventBus.getDefault();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.receive);
        name = (TextView) findViewById(R.id.name);
        HelloWorld stickyEvent = EventBus.getDefault().getStickyEvent(HelloWorld.class);
        // Better check that an event was actually posted before
        Log.d("Hans","ss");
        if(stickyEvent != null) {
            // "Consume" the sticky event
            Toast.makeText(ReceiveActivity.this, ""+stickyEvent.getMessage(), Toast.LENGTH_SHORT).show();
        // Now do something with it
        }
    }
     @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {

        super.onStop();
    }
}
