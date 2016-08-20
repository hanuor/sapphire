package com.hanuor.sapphire.hub;/*
 * Copyright (C) 2016 Hanuor Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanuor.sapphire.R;


public class SuggestionView extends RelativeLayout {
    private TextView textView;
    private String defaultheaderTextColor = "#eeeeee";
    private String defaultfooterTextColor = "#eeeeee";

    private float footerTextSize = 13f;
    private String BACKGROUND_COLOR = "#880E4F";
    private Context context;
    private float headerTextSize = 13f;
    String TEXT = "Suggestionbox";
    String Ftext = "Default";
    ImageView imageView;
    TextView valueTextView, footer;
    ImageView minusButton;
    TextView tv2;
    View rootView;
    public SuggestionView(Context context) {
        super(context);

        this.context = context;
        textView = new TextView(context);
        tv2 = new TextView(context);
        imageView = new ImageView(context);
        setUPSuggestion(context);

    }

    public SuggestionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        textView = new TextView(context, attrs);
        tv2 = new TextView(context);
        imageView = new ImageView(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.suggestionbox,0,0);

        try {
            String text = typedArray.getString(R.styleable.suggestionbox_headerText);
            String headerTextColor = typedArray.getString(R.styleable.suggestionbox_headertextColor);
            String background_color = typedArray.getString(R.styleable.suggestionbox_backgroundColor);
            float headerTextFontSize = typedArray.getFloat(R.styleable.suggestionbox_headerTextFontSize, headerTextSize);
            float footerTextFontSize = typedArray.getFloat(R.styleable.suggestionbox_footertextSize, footerTextSize);
            String footerTextColor = typedArray.getString(R.styleable.suggestionbox_footertextColor);
            String footerText = typedArray.getString(R.styleable.suggestionbox_footerText);

            if(footerText!=null){
                Ftext = footerText;
            }

            if(footerTextColor!=null){
                defaultfooterTextColor = footerTextColor;
            }

            if(footerTextFontSize!=0){
                footerTextSize = footerTextFontSize;
            }

            if (text!=null)
                TEXT = text;
            if (headerTextColor!=null)
                defaultheaderTextColor = headerTextColor;

            if (headerTextFontSize!=0)
                headerTextSize = headerTextFontSize;
            setUPSuggestion(context);

        } finally {
            typedArray.recycle();
        }


    }

    public SuggestionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        textView = new TextView(context, attrs, defStyleAttr);
        imageView = new ImageView(context, attrs, defStyleAttr);

        tv2 = new TextView(context);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.suggestionbox,0,0);

        try {

            String text = typedArray.getString(R.styleable.suggestionbox_headerText);
            String headerTextColor = typedArray.getString(R.styleable.suggestionbox_headertextColor);
            String background_color = typedArray.getString(R.styleable.suggestionbox_backgroundColor);
            float headerTextFontSize = typedArray.getFloat(R.styleable.suggestionbox_headerTextFontSize, headerTextSize);
            String footerTextColor = typedArray.getString(R.styleable.suggestionbox_footertextColor);
            float footerTextFontSize = typedArray.getFloat(R.styleable.suggestionbox_footertextSize, footerTextSize);
            String footerText = typedArray.getString(R.styleable.suggestionbox_footerText);

            if(footerText!=null){
                Ftext = footerText;
            }

            if(footerTextColor!=null){
                defaultfooterTextColor = footerTextColor;
            }

            if(footerTextFontSize!=0){
                footerTextSize = footerTextFontSize;
            }

            if (text!=null)
                TEXT = text;
            if (headerTextColor!=null)
                defaultheaderTextColor = headerTextColor;
           if (headerTextFontSize!=0)
                headerTextSize = headerTextFontSize;


            setUPSuggestion(context);

        } finally {
            typedArray.recycle();
        }


        setUPSuggestion(context);
    }

    private void setUPSuggestion(final Context context) {
        rootView = inflate(context, R.layout.questbox, this);
        valueTextView = (TextView) rootView.findViewById(R.id.valueTextView);
        footer = (TextView) rootView.findViewById(R.id.footer);


        valueTextView.setText(TEXT);
        valueTextView.setGravity(Gravity.CENTER);
        valueTextView.setTextSize(headerTextSize);
        valueTextView.setTextColor(Color.parseColor(defaultheaderTextColor));



        minusButton = (ImageView) rootView.findViewById(R.id.minusButton);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, minusButton.getId());
        params.addRule(RelativeLayout.BELOW, valueTextView.getId());

        minusButton.setLayoutParams(params);



        footer.setTextColor(Color.parseColor(defaultfooterTextColor));
        footer.setText(Ftext);
        footer.setTextSize(footerTextSize);
        RelativeLayout.LayoutParams paramsfooter = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsfooter.addRule(RelativeLayout.CENTER_HORIZONTAL, minusButton.getId());
        paramsfooter.addRule(RelativeLayout.BELOW, minusButton.getId());

        footer.setLayoutParams(paramsfooter);

        minusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked ;)", Toast.LENGTH_SHORT).show();
            }
        });







    }

}
