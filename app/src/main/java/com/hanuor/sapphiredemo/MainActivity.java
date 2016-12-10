package com.hanuor.sapphiredemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanuor.sapphire.Sapphire;
import com.hanuor.sapphire.hub.OnEventHandler;
import com.hanuor.sapphire.hub.SapphireIntentHandler;
import com.hanuor.sapphire.hub.SuggestionView;
import com.hanuor.sapphire.temporarydb.HintsStoreDB;
import com.hanuor.sapphire.utils.intentation.IntentationPrime;

import java.util.ArrayList;
import java.util.Calendar;

import de.greenrobot.event.EventBus;


public class MainActivity extends AppCompatActivity implements OnEventHandler, SensorEventListener {
    Button but;
    DemoObject demoObject;
    ImageView img, img2;
    SuggestionView suggestionView;
    IntentationPrime intentationPrime;
    private EventBus bus = EventBus.getDefault();
    String getJSON = null;
    final BaseSpringSystem mSpringSystem = SpringSystem.create();
    final ExampleSpringListener mSpringListener = new ExampleSpringListener();
    private SensorManager mSensorManager;
    ImageView iv;
    private Sensor mSensor;


    Spring mScaleSpring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but  = (Button) findViewById(R.id.button);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        Toast.makeText(this, "Hell" + mSensor.getMaximumRange(), Toast.LENGTH_SHORT).show();
        iv = (ImageView) findViewById(R.id.imageView1);
        ArrayList<String> vmm = new ArrayList<String>();
        vmm.add("Hi welcome to my new app");
        vmm.add("We have sapphire already integrated within it");
        vmm.add("Sapphire is an upcoming SDK which modifies the in-app notification concept");
        vmm.add("Sapphire is powered by Hanuor's own hoomcooked Dynalitic Engine");
        HintsStoreDB hintsStoreDB = new HintsStoreDB(MainActivity.this);
        hintsStoreDB.storeDetails(vmm);
        suggestionView = (SuggestionView) findViewById(R.id.suggest);
       // suggestionView.setUPSuggestion(MainActivity.this, getResources().getDrawable(R.drawable.email));
        Sapphire.initialize(MainActivity.this,"asas","bbb");
        intentationPrime = new IntentationPrime();
        Animation RightSwipe = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide);

        final Animation lswipe = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left);
        RightSwipe.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                       // suggestionView.startanimation(lswipe);
                        //suggestionView.setVisibility(View.INVISIBLE);
                    }
                }, 5000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        suggestionView.startAnimation(RightSwipe);
        Sapphire.with(MainActivity.this).setRandomMeasures(true,suggestionView);
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
                R.drawable.frost);
        Bitmap icon2  = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.email);
        img.setImageBitmap(icon1);
        img2.setImageBitmap(icon2);
        img.setTag("frost");
        img2.setTag("girl");


        arrayOfImgViews.add(img);
        arrayOfImgViews.add(img2);
        Sapphire.with(MainActivity.this).registerImageViews(arrayOfImgViews);
        Log.d("SapphireAccess","true");
        SapphireIntentHandler sapphireIntentHandlr = new SapphireIntentHandler(MainActivity.this);
        sapphireIntentHandlr.check(suggestionView);

        Bitmap bitmap = ((BitmapDrawable)img2.getDrawable()).getBitmap();
        Log.d("Sappitttt",""+bitmap.toString());
        final ArrayList<ImageView> imageViewArrayList = new ArrayList<ImageView>();
        imageViewArrayList.add(img);
        imageViewArrayList.add(img2);

        Sapphire.with(MainActivity.this).registerTags(m);
        //suggestionView.dismiss();

/*
        Animation a = new AlphaAnimation(1.00f, 0.00f);

        a.setDuration(1000);
        a.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            public void onAnimationEnd(Animation animation) {


            }
        });
        suggestionView.startanimation(a);*/


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
                Sapphire.with(MainActivity.this).gain(img.getTag());
               // Sapphire.with(MainActivity.this).gain(img.getTag(), suggestionView);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sapphire.with(MainActivity.this).gain(img2.getTag());
                //  Sapphire.with(MainActivity.this).gain(img2.getTag());
                //Sapphire.with(MainActivity.this).check(img);

//                Sapphire.with(MainActivity.this).gain(img2.getTag(), suggestionView);
                }
        });
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

        final GestureDetector gestureDetector = new GestureDetector(this, new SingleTapConfirm());
        mScaleSpring = mSpringSystem.createSpring();
        suggestionView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (gestureDetector.onTouchEvent(event)) {
                    // single tap
                    Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    // your code for move and drag
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // When pressed start solving the spring to 1.
                            mScaleSpring.setEndValue(1);
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            // When released start solving the spring to 0.
                            mScaleSpring.setEndValue(0);
                            break;
                    }

                    return true;
                }

            }
        });

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
           getJSON = intentationPrime.intentToJSON(MainActivity.this,ms);

            //sapphireIntentHandler.setIntent("gg",ms);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              SapphireIntentHandler sapphireIntentHandler1 = new SapphireIntentHandler(MainActivity.this);
                sapphireIntentHandler1.tast("hhn","mefa");
                //Sapphire.with(MainActivity.this).registerImageViews(arrayOfImgViews);
                //Sapphire.with(MainActivity.this).addTags(m);

               // sapphireIntentHandler.retrieveIntentData("Vamos");
                //Sapphire.with(MainActivity.this).gain(img.getTag(), suggestionView, ms);
               // startActivity(ms);*/
            }
                      });

    }
    @Override
    public void onResume() {
        super.onResume();
        // Add a listener to the spring when the Activity resumes.

        mSensorManager.registerListener(MainActivity.this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        mScaleSpring.addListener(mSpringListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void setUpEvent(Context context){
        Intent asss = new Intent();
        asss.setFlags(Intent.FILL_IN_ACTION);
        asss.setFlags(Intent.EXTRA_DOCK_STATE_DESK);
        SapphireIntentHandler sapphireIntentHandler1 = new SapphireIntentHandler(MainActivity.this);
        try {
            sapphireIntentHandler1.setIntent("pyasas",asss);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
       }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -0.01 && event.values[0]< .55) {
                //near

                Toast.makeText(this, ""+event.values[0], Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
            } else {
                //far

                Toast.makeText(getApplicationContext(), "far", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private class ExampleSpringListener extends SimpleSpringListener {
        @Override
        public void onSpringUpdate(Spring spring) {
            // On each update of the spring value, we adjust the scale of the image view to match the
            // springs new value. We use the SpringUtil linear interpolation function mapValueFromRangeToRange
            // to translate the spring's 0 to 1 scale to a 100% to 50% scale range and apply that to the View
            // with setScaleX/Y. Note that rendering is an implementation detail of the application and not
            // Rebound itself. If you need Gingerbread compatibility consider using NineOldAndroids to update
            // your view properties in a backwards compatible manner.
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
            suggestionView.setScaleX(mappedValue);
            suggestionView.setScaleY(mappedValue);
        }
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }

}
