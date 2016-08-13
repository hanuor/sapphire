package com.hanuor.sapphire.hub;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanuor.sapphire.R;


/**
 * Created by Shantanu Johri on 30-07-2016.
 */
public class Questbox extends RelativeLayout {
    private TextView textView;
    private String TEXT_COLOR = "#eeeeee";
    private String BACKGROUND_COLOR = "#880E4F";
    private Context context;
    String TEXT = "Suggestionbox";
    ImageView imageView;

    private String ICON_COLOR = "#880E4F";
    TextView tv2;
    //Java Inflation
    public Questbox(Context context) {
        super(context);

        this.context = context;
        textView = new TextView(context);
        tv2 = new TextView(context);
        imageView = new ImageView(context);
        setUPSuggestion(context);

    }


    //XML Inflation
    public Questbox(Context context, AttributeSet attrs) {
        super(context, attrs);

        textView = new TextView(context, attrs);
        tv2 = new TextView(context);
        imageView = new ImageView(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.suggestionbox,0,0);

        try {

            String text = typedArray.getString(R.styleable.suggestionbox_headerText);
            String text_color = typedArray.getString(R.styleable.suggestionbox_headertextColor);
            String background_color = typedArray.getString(R.styleable.suggestionbox_backgroundColor);

            if (text!=null)
                TEXT = text;
            if (text_color!=null)
                TEXT_COLOR = text_color;
            if (background_color!=null)
                ICON_COLOR = background_color;

            setUPSuggestion(context);

        } finally {
            typedArray.recycle();
        }


    }

    //XMl Inflation
    public Questbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        textView = new TextView(context, attrs, defStyleAttr);
        imageView = new ImageView(context, attrs, defStyleAttr);

        tv2 = new TextView(context);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.suggestionbox,0,0);

        try {

            String text = typedArray.getString(R.styleable.suggestionbox_headerText);
            String text_color = typedArray.getString(R.styleable.suggestionbox_headertextColor);
            String background_color = typedArray.getString(R.styleable.suggestionbox_backgroundColor);

            if (text!=null)
                TEXT = text;
            if (text_color!=null)
                TEXT_COLOR = text_color;
            if (background_color!=null)
                ICON_COLOR = background_color;

            setUPSuggestion(context);

        } finally {
            typedArray.recycle();
        }


        setUPSuggestion(context);
    }

    private void setUPSuggestion(Context context) {
        architecture();
        addText();
        addSubText();
        addIcon();
        //move the whole layout in the center of the screen
        //moveToCenter();

        //invalidate and redraw the views.
        invalidate();
        requestLayout();

    }

    private void addText() {

        textView.setText(TEXT);
            textView.setPadding(130,30,11,3);

        textView.setGravity(Gravity.RIGHT);
        textView.setTextColor(Color.parseColor(TEXT_COLOR));
      //  int nw = LayoutParams.MATCH_PARENT-11;
        RelativeLayout.LayoutParams layoutParams = new
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(124,30,0,0);
        layoutParams.addRule(RelativeLayout.BELOW, imageView.getId());

        this.addView(textView, layoutParams);

    }

    private void addSubText() {

        tv2.setText("Blasphemy");
        tv2.setPadding(120,7,11,7);
        tv2.setGravity(Gravity.RIGHT);
        tv2.setTextColor(Color.parseColor(TEXT_COLOR));
        //int re = LayoutParams.MATCH_PARENT-11;
        RelativeLayout.LayoutParams layoutParams = new
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(120,3,0,0);
        layoutParams.addRule(RelativeLayout.BELOW, textView.getId());

        this.addView(tv2, layoutParams);

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addIcon() {

        imageView.setImageResource(R.drawable.frost);


        imageView.setPadding(0,0,0,0);
        //imageView.setForegroundGravity(Gravity.RIGHT);

        // imageView.setColorFilter(Color.parseColor(ICON_COLOR), PorterDuff.Mode.SRC_ATOP);

        RelativeLayout.LayoutParams layoutParams = new
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(240,0,0,0);
        layoutParams.setLayoutDirection(RelativeLayout.ALIGN_RIGHT);

        this.addView(imageView, layoutParams);


    }

    private void architecture() {

        RoundRectShape rect = new RoundRectShape(
                new float[]{50, 50, 50, 0, 50, 0, 50, 50},
                null,
                null);


        ShapeDrawable sd = new ShapeDrawable(rect);
        sd.getPaint().setColor(Color.parseColor(BACKGROUND_COLOR));


        ShapeDrawable sds = new ShapeDrawable(rect);
        sds.setShaderFactory(new ShapeDrawable.ShaderFactory() {

            @Override
            public Shader resize(int width, int height) {
                LinearGradient lg = new LinearGradient(0, 0, 0, height,
                        new int[]{Color.parseColor("#dddddd"),
                                Color.parseColor("#dddddd"),
                                Color.parseColor("#dddddd"),
                                Color.parseColor("#dddddd")}, new float[]{0,
                        0.50f, 0.50f, 1}, Shader.TileMode.REPEAT);
                return lg;
            }
        });

        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{sds, sd});
        layerDrawable.setLayerInset(0, 0, 0, 0, 0); // inset the shadow so it doesn't start right at the left/top
        layerDrawable.setLayerInset(0, 0, 0, 0, 0); //

        this.setBackgroundDrawable(layerDrawable);


    }



    public void updateText(String text){

        this.TEXT = text;
        this.textView.setText(this.TEXT);

    }
}
