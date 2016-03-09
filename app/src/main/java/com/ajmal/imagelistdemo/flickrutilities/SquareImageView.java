package com.ajmal.imagelistdemo.flickrutilities;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 *  Created by ajmal on 7/3/16.
 *
 *  Imageview that makes scales the image to perfect squre
 *  The idea taken from http://stackoverflow.com/questions/16506275/imageview-be-a-square-with-dynamic-width.
 *
 */
public class SquareImageView extends ImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }
}