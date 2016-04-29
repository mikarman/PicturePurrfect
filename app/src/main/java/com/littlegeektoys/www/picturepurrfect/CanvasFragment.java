package com.littlegeektoys.www.picturepurrfect;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

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
}
