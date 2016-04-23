package com.littlegeektoys.www.picturepurrfect;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Michael Karman on 4/10/2016.
 */
public class EditorActivity extends AppCompatActivity {

    /* Not used
    private static final String EXTRA_PIC_ID =
            "com.littlegeektoys.www.picturepurrfect.pic_id";
    */

    private static final String CLOSE_HOSTING_ACTIVITY =
            "com.littlegeektoys.www.picturepurrfect.close_hosting_activity_boolean";

    private Uri photo;

    public static Intent newIntent(Context packageContext, Uri pic) {
        Intent intent = new Intent(packageContext, EditorActivity.class);
        intent.setData(pic);
        intent.putExtra(CLOSE_HOSTING_ACTIVITY, true);
        return intent;
    }
/*
    protected Fragment createFragment() {
        return new EditorImageFragment();
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_fragment_container);

        photo = (Uri) getIntent().getData();
        final boolean closeActivity = (boolean) getIntent()
                .getBooleanExtra(CLOSE_HOSTING_ACTIVITY, true); //Uh, no idea if this will ever be needed, kill main menu activity maybe??

        FragmentManager fm = getSupportFragmentManager();
        Fragment editorImageFragment = fm.findFragmentById(R.id.image_editor_fragment);
        //Fragment topMenuFragment = fm.findFragmentById(R.id.top_menu_fragment);


        // Maybe check if editorImageFragment is not null, then close the fragment, update the fragment?
        if (editorImageFragment == null) {  // if there is no editorImageFragment yet, make new one
            editorImageFragment = new EditorImageFragment();
            Fragment topMenuFragment = new TopMenuFragment();
            Fragment bottomMenuFragment = new BottomMenuFragment();
            fm.beginTransaction()
                    .add(R.id.editor_fragment_container, editorImageFragment)
                    .add(R.id.editor_fragment_container, topMenuFragment)
                    .add(R.id.editor_fragment_container, bottomMenuFragment)
                    .commit();
        }

    }

    public Uri getPhoto() { return photo; }
}
