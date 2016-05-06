package com.littlegeektoys.www.picturepurrfect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

    private static final String DIALOG_STICKER = "DialogSticker";
    private static final String DIALOG_TEXT_INPUT = "DialogTextInput";
    private static final String DIALOG_COLOR_INPUT = "DialogColorInput";
    private static final int REQUEST_STICKER = 0;
    private static final int REQUEST_TEXT = 1;
    private static final int REQUEST_COLOR = 2;

    private static final String TAG = "TopMenuFragment";
    private ImageButton mStickerButton;
    private ImageButton mColorButton;
    private ImageButton mTextButton;
    private Bitmap mBitmap;
    private File mPhotoFile;
    private EditorActivity mHostingActivity;
    private MenuToolInterface mCallbacks; //Added chapter 17
    private String sticker;
    private String text;
    private String color = "";

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
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            Log.i("BottomMenuFragment" , "sticker not set");
            return;
        }

        if(requestCode == REQUEST_STICKER){
            sticker = (String) data.getSerializableExtra(StickerPickerFragment.EXTRA_STICKER);
            Log.i("BottomMenuFragment" , sticker + " set");
            mCallbacks.onStickerSelect(sticker);
        }

        if(requestCode == REQUEST_TEXT){
            text = (String) data.getSerializableExtra(TextInputFragment.EXTRA_TEXT);
            Log.i("BottomMenuFragment" , text + " input");
            mCallbacks.onTextInput(text);
        }

        if(requestCode == REQUEST_COLOR){
            color = (String) data.getSerializableExtra(ColorPickerFragment.EXTRA_COLOR);
            Log.i("BottomMenu COLOR", " " + color + " set");
            if (color != null) {
                mCallbacks.onColorSelect(color);
            }
        }
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
                FragmentManager manager = getFragmentManager();
                StickerPickerFragment dialog = StickerPickerFragment.newInstance();
                dialog.setTargetFragment(BottomMenuFragment.this, REQUEST_STICKER);
                dialog.show(manager, DIALOG_STICKER);
            }

        });

        mColorButton = (ImageButton) v.findViewById(R.id.color_button);

        mColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                ColorPickerFragment dialog = ColorPickerFragment.newInstance();
                dialog.setTargetFragment(BottomMenuFragment.this, REQUEST_COLOR);
                dialog.show(manager, DIALOG_COLOR_INPUT);

            }

        });

        mTextButton = (ImageButton) v.findViewById(R.id.text_button);

        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TextInputFragment dialog = TextInputFragment.newInstance();
                dialog.setTargetFragment(BottomMenuFragment.this, REQUEST_TEXT);
                dialog.show(manager, DIALOG_TEXT_INPUT);
            }

        });

        return v;
    }
}
