package com.xiamubobby.mustick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2014/11/13.
 */
public class MuStickPrev extends View {

    Bitmap b;
    String t;
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
        Paint p = new Paint();
        if (b != null) {
            canvas.drawBitmap(b, null, new RectF(0, 0, getWidth(), getHeight()), p);
        }
        p.setARGB(128, 128, 128, 128);
        p.setTextAlign(Paint.Align.LEFT);
       p.setTextSize(20f);
        if (t != null && t != "") {
            Log.v("im", "here");
            canvas.drawText(t, 0, 0, p);
        }
    }


}
