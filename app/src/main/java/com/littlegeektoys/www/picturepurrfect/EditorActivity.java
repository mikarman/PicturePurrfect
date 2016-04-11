package com.littlegeektoys.www.picturepurrfect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

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

    public static Intent newIntent(Context packageContext, Uri pic) {
        Intent intent = new Intent(packageContext, EditorActivity.class);
        intent.setData(pic);
        intent.putExtra(CLOSE_HOSTING_ACTIVITY, true);
        return intent;
    }

   // protected Fragment createFragment() {
   //     return new EditorImageFragment();
   // }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.editor_fragment_container);
        setContentView(R.layout.image_editor_fragment);
        photo = (Uri) getIntent().getData();
        final boolean closeActivity = (boolean) getIntent()
                .getBooleanExtra(CLOSE_HOSTING_ACTIVITY, true); //Uh, no idea if this will ever be needed, kill main menu activity maybe??

        //FragmentManager fm = getSupportFragmentManager();
        //Fragment fragment = fm.findFragmentById(R.id.image_editor_fragment_container);

        //if (fragment == null) {
        //    fragment = createFragment();
        //    fm.beginTransaction()
        //            .add(R.id.image_editor_fragment_container,fragment)
        //            .commit();
        //}

        mPhotoFile = new File(photo.getPath());
        mCanvasView = (ImageView) findViewById(R.id.canvas);
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mCanvasView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), this);
            mCanvasView.setImageBitmap(bitmap);
        }

        mSaveButton = (ImageButton) findViewById(R.id.save_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "This will allow users to save the picture to their photo gallery after the y are done editing.", Toast.LENGTH_LONG).show();
            }

        });

        mMenuButton = (ImageButton) findViewById(R.id.menu_button);

        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "This will take the user back to the main menu", Toast.LENGTH_LONG).show();
            }

        });

        mShareButton = (ImageButton) findViewById(R.id.share_button);

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "This will allow the user to share there picture through email and social media", Toast.LENGTH_LONG).show();
            }

        });

        mRetakeButton = (ImageButton) findViewById(R.id.retake_button);

        mRetakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "This will allow the user to take a new picture.", Toast.LENGTH_LONG).show();
            }

        });

        mStickerButton = (ImageButton) findViewById(R.id.sticker_button);

        mStickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "This will allow users to put stickers on the picture they are editing", Toast.LENGTH_LONG).show();
            }

        });

        mColorButton = (ImageButton) findViewById(R.id.color_button);

        mColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "This will allow users to change the color of the picture they are editing", Toast.LENGTH_LONG).show();
            }

        });

        mTextButton = (ImageButton) findViewById(R.id.text_button);

        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "This will allow users to put text on the picture they are editing", Toast.LENGTH_LONG).show();
            }

        });
        Toast.makeText(getBaseContext(), "This is currently just an activity. We are working on using fragments properly", Toast.LENGTH_LONG).show();

    }

    public Uri getPhoto() { return photo; }
}
