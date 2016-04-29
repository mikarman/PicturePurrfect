package com.littlegeektoys.www.picturepurrfect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Jesse DeMott on 4/22/2016.
 */
public class CanvasView extends View {
    private Bitmap mImage;
    private int mCanvasWidth;
    private int mCanvasHeight;

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
        int centerX = mCanvasWidth/2;
        int centerY = mCanvasHeight/2;
        canvas.drawBitmap(mImage, 0, 0, null);
        canvas.drawLine(centerX,centerY,400,400,p);
    }

    public void setImage(Bitmap image){
        mImage = image;
    }

}
