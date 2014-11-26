package com.xiamubobby.mustick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2014/11/26.
 */
public class PosterPreview extends View {
    public PosterPreview(Context context) {
        super(context);
    }

    public PosterPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PosterPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Poster mp;

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float cw = canvas.getWidth();
        float ch = canvas.getHeight();

        Paint p = new Paint();
        Paint.FontMetrics fm = p.getFontMetrics();

        if (mp != null) {
            canvas.drawColor(mp.bgColor);
            if (mp.image != null) {
                mp.imageScaleRatio = cw * (1 - 2 * mp.insetRatio) / mp.image.getWidth();
                canvas.drawBitmap(mp.image, null, new RectF(
                    cw * mp.insetRatio,
                    ch * mp.insetRatio,
                    cw * (1 - mp.insetRatio),
                    ch * mp.insetRatio + mp.image.getHeight() * mp.imageScaleRatio
                ), p);
            }
            if (mp.textSecond == null) {
                if (mp.textFirst != null) {
                    p.setARGB(255, 255, 255, 255);
                    p.setTextSize(cw * mp.textSizeRatio);
                    p.setTextAlign(Paint.Align.CENTER);
                    p.getFontMetrics(fm);
                    canvas.drawText(mp.textFirst, cw /2,
                            ch / 2 + mp.image.getHeight() * mp.imageScaleRatio / 2 + (fm.descent - fm.ascent) / 2,
                            p);
                }
            }
        }
    }

    public void attachPoster(Poster poster) {
        mp = poster;
    }
}
