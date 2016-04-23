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
public class TopMenuFragment extends Fragment {
    private static final String TAG = "TopMenuFragment";
    private ImageButton mSaveButton;
    private ImageButton mMenuButton;
    private ImageButton mShareButton;
    private ImageButton mRetakeButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called");

        View v = inflater.inflate(R.layout.top_menu_fragment, container, false);

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

        return v;
    }
}
