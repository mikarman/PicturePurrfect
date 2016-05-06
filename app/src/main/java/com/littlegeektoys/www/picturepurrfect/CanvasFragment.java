package com.littlegeektoys.www.picturepurrfect;

import android.content.Intent;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Jesse DeMott on 4/22/2016.
 */
public class CanvasFragment extends Fragment{
    /**
     * TAG used for logs
     */
    private static final String TAG = "CanvasFragment";
    /**
     * Holds the CanvasView Created for fragment
     */
    private CanvasView mCanvasView;
    /**
     * Bitmap being edited
     */
    private Bitmap mBitmap;
    /**
     * Fragments Hosting activity
     */

    private Bitmap mBitmapOriginal;
    /**
     * Fragments Hosting activity
     */
    private EditorActivity mHostingActivity;
    /**
     * Image File received from camera or gallery before being turned into a Bitmap
     */
    private File mPhotoFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");

    }

    /**
     * Creates CanvasView to be housed in the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called");


        View v = inflater.inflate(R.layout.canvas_fragment, null);
        FrameLayout canvasContainer = (FrameLayout) v.findViewById(R.id.canvas_container);
        mCanvasView = new CanvasView(getContext());
        mCanvasView.setDrawingCacheEnabled(true);
        canvasContainer.addView(mCanvasView);

        mHostingActivity = (EditorActivity) getActivity();
        mPhotoFile = new File(mHostingActivity.getPhoto().getPath());
        //mCanvasView = (ImageView) v.findViewById(R.id.canvas);
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            //mCanvasView.setImageDrawable(null);
        } else {
            mBitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), mHostingActivity);
            mBitmapOriginal = mBitmap;
        }

        mCanvasView.setImage(mBitmap);
        return v;
    }

    /**
     * Update the bitmap in the CanvasView
     */
    public void updatePhoto() {
        mPhotoFile = new File(mHostingActivity.getPhoto().getPath());
        if (mPhotoFile != null || mPhotoFile.exists()) {
            mBitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), mHostingActivity);
            mCanvasView.setImage(mBitmap);
            mCanvasView.invalidate();
        }
    }

    public void changeColor (String color) {
        if (color.equals("color1")) {
            mBitmap = mCanvasView.toBlue(mBitmapOriginal);
        } else if (color.equals("color2")) {

            mBitmap = mCanvasView.toGrayscale(mBitmapOriginal);
        } else if(color.equals("color3")){
            mBitmap = mBitmapOriginal;
        }
        mCanvasView.setImage(mBitmap);

    }

    /**
     * Set the current sticker being used in the CanvasView
     * @param sticker
     */
    public void stickerOn(String sticker){
       mCanvasView.setStickerOn(sticker);
    }

/*    public void saveImage() {
        Toast.makeText(getActivity(), "This will save the image, being called from CanvasFragment",
                Toast.LENGTH_LONG).show();

        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), mBitmap, "image", "image taken in picture purrfect");
    }*/


    /**
     * Clears all sticker from the CanvasView
     */
    public void clearStickers(){
        mCanvasView.clearStickers();
    }

    /**
     * Set String for text sticker in CanvasView
     * @param text
     */
    public void textOn(String text){
        mCanvasView.setTextOn(text);
    }


    /**
     * Saves edited Bitmap to internal storage
     */
    public void saveImage() {
        Bitmap bitmap = mCanvasView.getDrawingCache();
        Toast.makeText(getActivity(), "Saved!",
                    Toast.LENGTH_SHORT).show();
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "image", "image taken in picture purrfect");
        mCanvasView.destroyDrawingCache();

  }
    }


