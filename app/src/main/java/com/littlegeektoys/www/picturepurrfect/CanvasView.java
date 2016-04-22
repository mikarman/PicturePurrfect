package com.littlegeektoys.www.picturepurrfect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by Jesse DeMott on 4/22/2016.
 */
public class CanvasView extends View {
    private Bitmap mImage;
    private int mCanvasWidth;
    private int mCanvasHeight;

    public CanvasView(Context context)   {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvasWidth = canvas.getWidth();
        mCanvasHeight = canvas.getHeight();
        int centerX = mCanvasWidth/2;
        int centerY = mCanvasHeight/2;
        canvas.drawBitmap(mImage, centerX, centerY, null);
    }

    public void setImage(Bitmap image){
        mImage = image;
    }

}
