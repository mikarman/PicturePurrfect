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

    public Sticker(Bitmap sticker, float x, float y){
        this.sticker = sticker;
        this.x = x;
        this.y = y;
    }

    public Bitmap getSticker() {
        return sticker;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
