package com.littlegeektoys.www.picturepurrfect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Michael Karman on 4/22/2016.
 */
public class BottomMenuFragment extends Fragment {
    private static final String TAG = "TopMenuFragment";
    private ImageButton mStickerButton;
    private ImageButton mColorButton;
    private ImageButton mTextButton;
    private Bitmap mBitmap;
    private File mPhotoFile;
    private EditorActivity mHostingActivity;
    private MenuToolInterface mCallbacks; //Added chapter 17

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallbacks = (MenuToolInterface) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called");

        View v = inflater.inflate(R.layout.bottom_menu_fragment, container, false);


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
                mCallbacks.onToolSelect(MenuToolInterface.ToolName.COLOR);
                Toast.makeText(getContext(), "This will allow users to change the color of the picture they are editing", Toast.LENGTH_LONG).show();
            }

        });

        mTextButton = (ImageButton) v.findViewById(R.id.text_button);

        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onToolSelect(MenuToolInterface.ToolName.TEXT);
                //Toast.makeText(getContext(), "This will allow users to put text on the picture they are editing", Toast.LENGTH_LONG).show();
            }

        });
        Toast.makeText(getContext(), "We are working out the bugs with our fragments here. The picture should be displayed in the middle and the menu should only appear once.", Toast.LENGTH_LONG).show();

        return v;
    }
}
