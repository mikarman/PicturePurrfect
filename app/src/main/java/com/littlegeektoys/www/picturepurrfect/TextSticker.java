package com.littlegeektoys.www.picturepurrfect;

import android.graphics.Bitmap;

/**
 * Created by jesse on 5/5/16.
 */
public class TextSticker {
    private String text;
    private float x;
    private float y;

    public TextSticker(String text, float x, float y){
        this.text = text;
        this.x = x;
        this.y = y;
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
}
