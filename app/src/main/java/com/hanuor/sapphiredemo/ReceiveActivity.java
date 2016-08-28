package com.hanuor.sapphiredemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        if(stickyEvent != null) {
// "Consume" the sticky event
            Toast.makeText(ReceiveActivity.this, ""+stickyEvent.getMessage(), Toast.LENGTH_SHORT).show();
// Now do something with it
        }


        }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)    public void onEvent(HelloWorld event){
        Log.d("Joker",""+event.getMessage());
        Toast.makeText(ReceiveActivity.this, ""+event.getMessage(), Toast.LENGTH_SHORT).show();
       }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this); // registering the bus

    }

    @Override
    public void onStop() {
        bus.unregister(this); // un-registering the bus
        super.onStop();
    }
}
