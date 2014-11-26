package com.xiamubobby.mustick;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2014/11/26.
 */
public abstract class MustickPreview extends View{
    public MustickPreview(Context context) {
        super(context);
    }

    public MustickPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MustickPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawTitle(canvas);
        drawImage(canvas);
        drawText(canvas);
        drawTrinkets(canvas);
    }

    abstract void drawBackground(Canvas canvas);
    abstract void drawTitle(Canvas canvas);
    abstract void drawImage(Canvas canvas);
    abstract void drawText(Canvas canvas);
    abstract void drawTrinkets(Canvas canvas);
}
