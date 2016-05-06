package com.littlegeektoys.www.picturepurrfect;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by admin on 5/4/2016.
 */
public class Sticker {
    /**
     * Bitmap for image based sticker
     */
    private Bitmap sticker;
    /**
     * String for text based sticker
     */
    private String text;
    /**
     * x position for drawing sticker
     */
    private float x;
    /**
     * y position for drawing sticker
     */
    private float y;
    /**
     * Flag for if the sticker is text based
     */
    private boolean isText = false;

    /**
     * Constructor for Bitmap based sticker
     * @param sticker
     * @param x
     * @param y
     */
    public Sticker(Bitmap sticker, float x, float y){
        this.sticker = sticker;
        this.x = x;
        this.y = y;
        this.isText = false;
    }

    /**
     * Constructor for text based sticker
     * @param text
     * @param x
     * @param y
     */
    public Sticker(String text, float x, float y){
        this.text = text;
        this.x = x;
        this.y = y;
        this.isText = true;
    }

    /**
     * Get image based sticker Bitmap
     * @return
     */
    public Bitmap getSticker() {
        return sticker;
    }

    /**
     * Get text based sticker String
     * @return
     */
    public String getTextSticker() {
        return text;
    }

    /**
     * Get stickers x position
     * @return
     */
    public float getX() {
        return x;
    }

    /**
     * Get stickers y position
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     * Get flag for if sticker is text based
     * @return
     */
    public boolean isText() {
        return isText;
    }


}
