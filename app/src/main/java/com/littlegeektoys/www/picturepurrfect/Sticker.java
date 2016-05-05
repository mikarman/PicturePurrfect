package com.littlegeektoys.www.picturepurrfect;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by admin on 5/4/2016.
 */
public class Sticker {
    private Bitmap sticker;
    private float x;
    private float y;
    private String text;
    private boolean isText = false;

    public Sticker(Bitmap sticker, float x, float y){
        this.sticker = sticker;
        this.x = x;
        this.y = y;
        this.isText = false;
    }

    public Sticker(String text, float x, float y){
        this.text = text;
        this.x = x;
        this.y = y;
        this.isText = true;
    }

    public Bitmap getSticker() {
        return sticker;
    }

    public String getTextSticker() {
        return text;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isText() {
        return isText;
    }


}
