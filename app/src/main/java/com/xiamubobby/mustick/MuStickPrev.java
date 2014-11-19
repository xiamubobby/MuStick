package com.xiamubobby.mustick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2014/11/13.
 */
public class MuStickPrev extends View {

    Bitmap b;
    String t;
    final String defaultTitle = "uhg?";
    int bgc;

    public MuStickPrev(Context context) {
        super(context);
    }

    public MuStickPrev(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MuStickPrev(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImg(Bitmap barg) {
        b = barg;
        invalidate();
    }
    public void setTitle(String targ) {
        t = targ;
        invalidate();
    }
    public void setBgColor(int carg) {
        bgc = carg;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        //drawBack~
        canvas.drawARGB(255, 0, 0, 0);

        //drawPic!
        if (b != null) {
            canvas.drawBitmap(b, null, new RectF(
                    ((float) (canvas.getWidth()*0.1)),
                    ((float) (canvas.getHeight()*0.1)),
                    ((float) (canvas.getWidth()*0.9)),
                    ((float) (b.getHeight() * (canvas.getWidth()*0.8) / b.getWidth() + canvas.getWidth()*0.1))),
                    paint);
        }

        //drawFrames-_-
        paint.setARGB(255,0,0,0);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(
                0,
                0,
                ((float) (canvas.getWidth()*0.1)),
                ((float) (canvas.getHeight())),
                paint);
        canvas.drawRect(
                ((float) (canvas.getWidth()*0.9)),
                0,
                ((float) (canvas.getWidth())),
                ((float) (canvas.getHeight())),
                paint);
        canvas.drawRect(
                0,
                0,
                ((float) (canvas.getWidth())),
                ((float) (canvas.getHeight()*0.1)),
                paint);
        canvas.drawRect(
                0,
                ((float) (canvas.getHeight()*0.7)),
                ((float) (canvas.getWidth())),
                ((float) (canvas.getHeight())),
                paint);

        //drawText :)
        paint.setARGB(255, 255, 255, 255);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fm = paint.getFontMetrics();
        if (t != null && t != "") {
            float s = ((float) (canvas.getWidth() * 0.85 / t.length()));
            paint.setTextSize((s<85)?s:85);
            Log.v("font",paint.getTextSize()+"");
            paint.getFontMetrics(fm);
            canvas.drawText(t,
                    canvas.getWidth()/2,
                    ((float) (canvas.getHeight()*0.85 - fm.descent + (fm.descent-fm.ascent)/2)),
                    paint);
        }
        else {
            /*paint.setTextSize(120);
            paint.getFontMetrics(fm);
            canvas.drawText(defaultTitle,
                    canvas.getWidth()/2,
                    ((float) (canvas.getHeight()*0.85 - fm.descent + (fm.descent-fm.ascent)/2)),
                    paint);*/
        }
    }

    GestureDetector detector = new GestureDetector(
            getContext(),
            new GestureDetector.SimpleOnGestureListener() {
                public void onLongPress(MotionEvent e) {
                    Log.v("moed","e");
                }
            });
    //detector.setIsLongpressEnabled(true);

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);


}
