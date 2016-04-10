package com.littlegeektoys.www.picturepurrfect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Michael Karman on 4/8/2016.
 * Fragment for image editor (canvas)
 */
public class EditorImageFragment extends Fragment {
    private static final String TAG = "EditorImageFragment";

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

        return v;
    }

}
