package com.littlegeektoys.www.picturepurrfect;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
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

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Jesse DeMott on 4/22/2016.
 */
public class CanvasFragment extends Fragment{
    private static final String TAG = "CanvasFragment";
    private CanvasView mCanvasView;
    private Bitmap mBitmap;
    private EditorActivity mHostingActivity;
    private File mPhotoFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");

    }

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
        }

        mCanvasView.setImage(mBitmap);

        return v;
    }

    //Update Photo
    public void updatePhoto() {
        mPhotoFile = new File(mHostingActivity.getPhoto().getPath());
        if (mPhotoFile != null || mPhotoFile.exists()) {
            mBitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), mHostingActivity);
            mCanvasView.setImage(mBitmap);
            mCanvasView.invalidate();
        }
    }

    public void changeColor (){
        mBitmap = mCanvasView.toGrayscale(mBitmap);
        mCanvasView.setImage(mBitmap);
        mCanvasView.invalidate();
        Log.d(TAG, "changeColor");
    }

    public void stickerOn(String sticker){
       mCanvasView.setStickerOn(sticker);
    }

    public void clearStickers(){
        mCanvasView.clearStickers();
    }

    public void textOn(String text){
        mCanvasView.setTextOn(text);
    }


    public void saveImage() {
        Bitmap bitmap = mCanvasView.getDrawingCache();
        Toast.makeText(getActivity(), "Saved!",
                    Toast.LENGTH_SHORT).show();
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "image", "image taken in picture purrfect");
        mCanvasView.destroyDrawingCache();

    }

}
