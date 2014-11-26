package com.xiamubobby.mustick;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;

/**
 * Created by Administrator on 2014/11/26.
 */
public class Poster {

    private static Poster INSTANCE;

    int bgColor;
    Bitmap image;
    String textFirst, textSecond;
    String title;
    float textSizeRatio;

    float imageScaleRatio;
    PointF imageOffset;
    PointF textFirstOffset;
    PointF textSecondOffset;

    float insetRatio;

    static public Poster getInstance() {
        INSTANCE = new Poster();
        return INSTANCE;
    }

    private Poster() {
        bgColor = Color.argb(255, 0, 0, 0);
        image = null;
        textFirst = null;
        textSecond = null;
        title = null;
        textSizeRatio = 0.125f;
        imageScaleRatio = 1f;
        imageOffset = new PointF(0f, 0f);
        textFirstOffset = new PointF(0f, 0f);
        textSecondOffset = new PointF(0f, 0f);

        insetRatio = 0.05f;
    }
}
