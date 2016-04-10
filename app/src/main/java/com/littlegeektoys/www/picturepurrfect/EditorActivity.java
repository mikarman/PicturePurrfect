package com.littlegeektoys.www.picturepurrfect;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Michael Karman on 4/10/2016.
 */
public class EditorActivity extends AppCompatActivity {

    @LayoutRes
    protected int getLayoutResId() { return R.layout.editor_fragment_container; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

    }
}
