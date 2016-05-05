package com.littlegeektoys.www.picturepurrfect;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jesse DeMott on 4/22/2016.
 */
public class CanvasView extends View {
    private Bitmap mImage;
    private int mCanvasWidth;
    private int mCanvasHeight;
    private boolean stickerOn = false;
    private boolean textOn = false;
    private String sticker;
    private ArrayList<Sticker> stickers = new ArrayList<>();

    Paint p = new Paint();

    public CanvasView(Context context)   {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.RED);
        mCanvasWidth = canvas.getWidth();
        mCanvasHeight = canvas.getHeight();
        float centerX = mCanvasWidth/2 - mImage.getWidth()/2;
        float centerY = mCanvasHeight/2 - mImage.getHeight()/2;
        //mImage = toGrayscale(mImage);
        canvas.drawBitmap(mImage, centerX, centerY, null);
        //canvas.drawLine(centerX, centerY, 400, 400, p);
        //canvas.drawText("Cat", 400, 400, p);
        if(stickers.size()>0) {
            for (Sticker s : stickers){
                canvas.drawBitmap(s.getSticker(),s.getX(),s.getY(),null);
            }
        }
    }

    public void setImage(Bitmap image){
        mImage = image;
    }

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
                        default:
                            break;
                    }
                    invalidate();
                }
                break;
            default:
                return true;
        }

        return true;
    }


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


    public void setStickerOn(String sticker){
        stickerOn = true;
        textOn = false;
        this.sticker = sticker;
    }

}
