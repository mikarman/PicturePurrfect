package com.littlegeektoys.www.picturepurrfect;

import android.app.Activity;
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
    private TopMenuCallback mCallbacks;

    public interface TopMenuCallback {
        void onSave();
        void onReturnMenu();
        void onShare();
        void onRetake();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallbacks = (TopMenuCallback) activity;
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

        View v = inflater.inflate(R.layout.top_menu_fragment, container, false);

        mSaveButton = (ImageButton) v.findViewById(R.id.save_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onSave();
            }

        });

        mMenuButton = (ImageButton) v.findViewById(R.id.menu_button);

        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onReturnMenu();
            }

        });

        mShareButton = (ImageButton) v.findViewById(R.id.share_button);

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onShare();
            }

        });

        mRetakeButton = (ImageButton) v.findViewById(R.id.retake_button);

        mRetakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onRetake();
            }

        });

        return v;
    }
}
