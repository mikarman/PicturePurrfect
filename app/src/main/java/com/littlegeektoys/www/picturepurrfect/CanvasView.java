package com.littlegeektoys.www.picturepurrfect;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/*
 * Created by Jesse DeMott on 4/22/2016.
 */

/**
 * CanvasView contains the canvas that is used to draw the new bitmap.
 */
public class CanvasView extends View {
    /**
     * Bitmap passed from the camera or gallery
     */
    private Bitmap mImage;
    /**
     * Width of canvas
     */
    private int mCanvasWidth;
    /**
     * Height of canvas
     */
    private int mCanvasHeight;
    /**
     * Flag that for sticker tool
     */
    private boolean stickerOn = false;
    /**
     * Flag that for text tool
     */
    private boolean textOn = false;
    /**
     * Current sticker being drawn
     */
    private String sticker;
    /**
     * Current text being drawn
     */
    private String text;
    /**
     * Array of all sticker and text that have been drawn
     */
    private ArrayList<Sticker> stickers = new ArrayList<>();
    //private ArrayList<TextSticker> textStickers = new ArrayList<>();

    Paint paint = new Paint();

    /**
     * Canvas view constructor
     * @param context
     */
    public CanvasView(Context context)   {
        super(context);
    }

    /**
     * onDraw method draws everything onto the canvas.
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvasWidth = canvas.getWidth();
        mCanvasHeight = canvas.getHeight();
        float centerX = mCanvasWidth/2 - mImage.getWidth()/2;
        float centerY = mCanvasHeight/2 - mImage.getHeight()/2;
        canvas.drawBitmap(mImage, centerX, centerY, null);
        if(stickers.size()>0) {
            for (Sticker s : stickers){
                if(s.isText()){
                    Log.i("CanvasView","text drawn");
                    paint.setColor(Color.WHITE);
                    paint.setTextSize(100);
                    canvas.drawText(s.getTextSticker(), s.getX(), s.getY(), paint);
                } else {
                    Log.i("CanvasView","sticker drawn");
                    int width = s.getSticker().getWidth();
                    int height = s.getSticker().getWidth();
                    float stickerX = s.getX() - width / 2;
                    float stickerY = s.getY() - height / 2;
                    canvas.drawBitmap(s.getSticker(), stickerX, stickerY, null);
                }
            }
        }
    }

    /**
     * Set the image being edited and drawn on
     * @param image
     */
    public void setImage(Bitmap image){
        mImage = image;
    }

    /**
     * onTouchEvent used to draw stickers and text on canvas
     * @param e
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(stickerOn) {
                    Bitmap icon;
                    switch (sticker) {
                        case "sticker1":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cat);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker2":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.heart);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker3":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paws);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker4":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.planet);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker5":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.star);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker6":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.star_two);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker7":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.panda);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker8":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bunny);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker9":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.butterfly);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker10":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.hearts);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker11":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.dog);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker12":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pig);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker13":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.stars);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker14":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.green);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        case "sticker15":
                            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.omg);
                            stickers.add(new Sticker(icon, e.getX(), e.getY()));
                            break;
                        default:
                            break;
                    }
                }

                if(textOn){
                    Log.i("CanvasView", "Text touch event triggered");
                    stickers.add(new Sticker(text,e.getX(),e.getY()));
                }

                break;
            default:
                return true;
        }
        invalidate();
        return true;
    }


    /**
     * Takes a Bitmap and returns a grayscale version.
     * @param bmpOriginal
     * @return
     */
    public Bitmap toGrayscale(Bitmap bmpOriginal)
    {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    public Bitmap toBlue(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorFilter filter = new LightingColorFilter(100, 1);
        paint.setColorFilter(filter);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

<<<<<<< HEAD
    public static Bitmap bw(Bitmap image) {
        // This function turns a bitmap to black and white
        double red = 0.5;   // We can change these
        double green = 0.5;
        double blue = 1.5;
        int width = image.getWidth();
        int height = image.getHeight();
        Bitmap bwBitmap = Bitmap.createBitmap(width, height, image.getConfig());
        double SCALE = 0.25;
        int A, R, G, B;
        int pixel;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixel = image.getPixel(i, j);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                B = G = R = (int) (SCALE * R + SCALE * G + SCALE * B);
                R += (red);
                if (R > 255) {
                    R = 255;
                }
                G += (green);
                if (G > 255) {
                    G = 255;
                }
                B += (blue);
                if (B > 255) {
                    B = 255;
                }
                bwBitmap.setPixel(i, j, Color.argb(A, R, G, B));
            }
        }
        return bwBitmap;
    }
    
    public static Bitmap color(Bitmap image) {
        // This function turns a bitmap to black and white
        double red = 2.0;   // We can change these
        double green = 0.5;
        double blue = 1.5;
        int width = image.getWidth();
        int height = image.getHeight();
        Bitmap bwBitmap = Bitmap.createBitmap(width, height, image.getConfig());
        double SCALE = 0.25;
        int A, R, G, B;
        int pixel;

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                pixel = image.getPixel(i, j);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                B = G = R = (int)( SCALE * R + SCALE * G + SCALE * B);
                R += (red);
                if(R > 255) {
                    R = 255;
                }
                G += (green);
                if(G > 255) {
                    G = 255;
                }
                B += (blue);
                if(B > 255) {
                    B = 255;
                }
                else if (B < 0) {
                    B = 0;
                }
                bwBitmap.setPixel(i, j, Color.argb(A, R, G, B));
            }
        }
        return bwBitmap;
    }

    /**
     * Sets the current sticker and the stickerOn and textOn flags.
     * @param sticker
     */
    public void setStickerOn(String sticker){
        stickerOn = true;
        textOn = false;
        this.sticker = sticker;
    }

    /**
     * clears all stickers and text
     */
    public void clearStickers(){
        stickers.clear();
    }

    /**
     * Sets the current text and the stickerOn and textOn flags
     * @param text
     */
    public void setTextOn(String text){
        stickerOn = false;
        textOn = true;
        this.text = text;
        Log.i("CanvasView", "text set to " + text);
    }

}
