package com.xiamubobby.mustick;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2014/11/25.
 */
public class Poster implements Parcelable{

    private final static Poster INSTANCE = new Poster();

    private int bgColor;
    private Bitmap image;
    private String textFirst, textSecond;
    private String title;
    private float textSize;
    private float imageScale;
    private PointF imageOffset;
    private PointF textFirstOffset;
    private PointF textSecondOffset;

    private float frameInset;

    private Poster() {
        bgColor = Color.argb(255, 0, 0, 0);
        image = null;
        textFirst = null;
        textSecond = null;
        title = null;
        textSize = 0.125f;
        imageScale = 1f;
        imageOffset = new PointF(0f, 0f);
        textFirstOffset = new PointF(0f, 0f);
        textSecondOffset = new PointF(0f, 0f);

        frameInset = 0.05f;
    }

    public static Poster getInstance() {
        return INSTANCE;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTextFirst() {
        return textFirst;
    }

    public void setTextFirst(String textFirst) {
        this.textFirst = textFirst;
    }

    public String getTextSecond() {
        return textSecond;
    }

    public void setTextSecond(String textSecond) {
        this.textSecond = textSecond;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getImageScale() {
        return imageScale;
    }

    public void setImageScale(float imageScale) {
        this.imageScale = imageScale;
    }

    public PointF getImageOffset() {
        return imageOffset;
    }

    public void setImageOffset(PointF imageOffset) {
        this.imageOffset = imageOffset;
    }

    public PointF getTextFirstOffset() {
        return textFirstOffset;
    }

    public void setTextFirstOffset(PointF textFirstOffset) {
        this.textFirstOffset = textFirstOffset;
    }

    public PointF getTextSecondOffset() {
        return textSecondOffset;
    }

    public void setTextSecondOffset(PointF textSecondOffset) {
        this.textSecondOffset = textSecondOffset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    public static final Parcelable.Creator<Poster> CREATOR = new Creator<Poster>() {

        @Override
        public Poster createFromParcel(Parcel parcel) {
                return null;
        }

        @Override
        public Poster[] newArray(int i) {
            return new Poster[0];
        }
    }
}
