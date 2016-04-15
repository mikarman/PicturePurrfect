package com.littlegeektoys.www.picturepurrfect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Michael Karman on 4/8/2016.
 * Fragment for image editor (canvas)
 */
public class EditorImageFragment extends Fragment {
    private static final String TAG = "EditorImageFragment";
    private ImageView mCanvasView;
    private EditorActivity mHostingActivity;
    private File mPhotoFile;
    private ImageButton mSaveButton;
    private ImageButton mMenuButton;
    private ImageButton mShareButton;
    private ImageButton mRetakeButton;
    private ImageButton mStickerButton;
    private ImageButton mColorButton;
    private ImageButton mTextButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called");

        View v = inflater.inflate(R.layout.image_editor_fragment, container, false);

        mHostingActivity = (EditorActivity) getActivity();
        mPhotoFile = new File(mHostingActivity.getPhoto().getPath());
        mCanvasView = (ImageView) v.findViewById(R.id.canvas);
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mCanvasView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), mHostingActivity);
            mCanvasView.setImageBitmap(bitmap);
        }

        mSaveButton = (ImageButton) v.findViewById(R.id.save_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This will allow users to save the picture to their photo gallery after the y are done editing.", Toast.LENGTH_LONG).show();
            }

        });

        mMenuButton = (ImageButton) v.findViewById(R.id.menu_button);

        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This will take the user back to the main menu", Toast.LENGTH_LONG).show();
            }

        });

        mShareButton = (ImageButton) v.findViewById(R.id.share_button);

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This will allow the user to share there picture through email and social media", Toast.LENGTH_LONG).show();
            }

        });

        mRetakeButton = (ImageButton) v.findViewById(R.id.retake_button);

        mRetakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This will allow the user to take a new picture.", Toast.LENGTH_LONG).show();
            }

        });

        mStickerButton = (ImageButton) v.findViewById(R.id.sticker_button);

        mStickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This will allow users to put stickers on the picture they are editing", Toast.LENGTH_LONG).show();
            }

        });

        mColorButton = (ImageButton) v.findViewById(R.id.color_button);

        mColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This will allow users to change the color of the picture they are editing", Toast.LENGTH_LONG).show();
            }

        });

        mTextButton = (ImageButton) v.findViewById(R.id.text_button);

        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This will allow users to put text on the picture they are editing", Toast.LENGTH_LONG).show();
            }

        });
        Toast.makeText(getContext(), "We are working out the bugs with our fragments here. The picture should be displayed in the middle and the menu should only appear once.", Toast.LENGTH_LONG).show();

        return v;
    }

}