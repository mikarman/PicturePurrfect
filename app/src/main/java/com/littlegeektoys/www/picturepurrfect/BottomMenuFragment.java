package com.littlegeektoys.www.picturepurrfect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Michael Karman on 4/22/2016.
 */
public class BottomMenuFragment extends Fragment {
    private static final String TAG = "TopMenuFragment";
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
