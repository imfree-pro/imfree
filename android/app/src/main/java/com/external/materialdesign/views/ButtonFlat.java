package com.external.materialdesign.views;
import com.external.materialdesign.utils.Utils;
import com.imfree.imfree.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * Created by 종열 on 2015-06-20.
 */
public class ButtonFlat extends Button {

    TextView textButton;

    public ButtonFlat(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    protected void setDefaultProperties(){
        minHeight = 36;
        minWidth = 88;
        rippleSize = 3;
        // Min size
        setMinimumHeight(Utils.dpToPx(minHeight, getResources()));
        setMinimumWidth(Utils.dpToPx(minWidth, getResources()));
        setBackgroundResource(R.drawable.background_transparent);
    }

    @Override
    protected void setAttributes(AttributeSet attrs) {
        // Set text button
        String text = null;
        int textResource = attrs.getAttributeResourceValue(ANDROIDXML,"text",-1);
        if(textResource != -1){
            text = getResources().getString(textResource);
        }else{
            text = attrs.getAttributeValue(ANDROIDXML,"text");
        }
        if(text != null){
            textButton = new TextView(getContext());
            textButton.setText(text.toUpperCase());
            textButton.setTextSize(14);
            textButton.setTextColor(backgroundColor);
            textButton.setTypeface(null, Typeface.BOLD);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            textButton.setLayoutParams(params);
            addView(textButton);
        }
        int backgroundColor = attrs.getAttributeResourceValue(ANDROIDXML,"background",-1);
        if(backgroundColor != -1){
            setBackgroundColor(getResources().getColor(backgroundColor));
        }else{
            // Color by hexadecimal
            // Color by hexadecimal
            background = attrs.getAttributeIntValue(ANDROIDXML, "background", -1);
            if (background != -1)
                setBackgroundColor(background);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (x != -1) {
            setBackgroundColor(makePressColor());
            if(onClickListener != null&& clickAfterRipple)
                onClickListener.onClick(this);
        }
    }

    /**
     * Make a dark color to ripple effect
     * @return
     */
    @Override
    protected int makePressColor(){
        return Color.parseColor("#88DDDDDD");
    }

    public void setText(String text){
        textButton.setText(text.toUpperCase());
    }

    // Set color of background
    public void setBackgroundColor(int color){
        backgroundColor = color;
        if(isEnabled())
            beforeBackground = backgroundColor;
        textButton.setTextColor(color);
    }

    @Override
    public TextView getTextView() {
        return textButton;
    }

    public String getText(){
        return textButton.getText().toString();
    }

}

