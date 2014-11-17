package com.xiamubobby.mustick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MuStickPreviewBase extends View {
    public MuStickPreviewBase(Context context) {
        super(context);
    }

    public MuStickPreviewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MuStickPreviewBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected Bitmap mImage;
    protected void setImage(Bitmap image) {
        mImage = image;
    }
    protected void setImage(int imageResId) {
        mImage = BitmapFactory.decodeResource(getContext().getResources(), imageResId);
    }

    protected String mTitle;
    protected void setTitle(String title) {
        mTitle = title;
    }

    protected String mText;
    protected void setText(String text) {
        mText = text;
    }

    //protected

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
