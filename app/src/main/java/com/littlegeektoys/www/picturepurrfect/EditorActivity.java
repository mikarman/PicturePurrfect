package com.littlegeektoys.www.picturepurrfect;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Switch;

/**
 * Created by Michael Karman on 4/10/2016.
 */
public class EditorActivity extends AppCompatActivity implements MenuToolInterface, TopMenuFragment.TopMenuCallback {

    /* Not used
    private static final String EXTRA_PIC_ID =
            "com.littlegeektoys.www.picturepurrfect.pic_id";
    */

    private static final String TAG = "EditorActivity";
    private static final int REQUEST_PHOTO = 0;

    private static final String CLOSE_HOSTING_ACTIVITY =
            "com.littlegeektoys.www.picturepurrfect.close_hosting_activity_boolean";

    private Uri photo;
    private FragmentManager fm;
    public static Context mContext;

    @Override
    public void onStickerSelect(String sticker){
        CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
        canvasFragment.stickerOn(sticker);
    }

    @Override
    public void onToolSelect(ToolName tool) {


        switch (tool) {
            case COLOR: {
                if (fm != null) {
                    CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
                    canvasFragment.changeColor();
                }
                break;
            }
            case STICKER: {
                //CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
                //canvasFragment.stickerOn();
                break;
            }
            case TEXT: {

                break;
            }
            default:
                break;
        }
    }

    // Top Menu callbacks implementation
    @Override
    public void onSave() {
        CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
        if(Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                canvasFragment.saveImage();
            }
        } else {
            canvasFragment.saveImage();
        }
    }

    @Override
    public void onReturnMenu() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra(CLOSE_HOSTING_ACTIVITY, true);
        startActivity(intent);

    }

    @Override
    public void onShare() {
        Bitmap mBitmap;
        String pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap,"title", null);
        Uri bmpUri = Uri.parse(pathofBmp);
        final Intent emailIntent1 = new Intent(     android.content.Intent.ACTION_SEND);
        emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
        emailIntent1.setType("image/png");
    }

    @Override
    public void onRetake() {
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager pm = getPackageManager();
        if (captureImage.resolveActivity(pm) != null) {
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photo);
            startActivityForResult(captureImage, REQUEST_PHOTO);
        }

    }

    @Override
    public void onGoBack() {
        Log.d(TAG, "OnGoBack");
    }

    @Override
    public void onSwitchFragment(Fragment f) {
        Log.d(TAG, "OnGoBack");
    }

    public static Intent newIntent(Context packageContext, Uri pic) {
        mContext = packageContext;
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

        fm = getSupportFragmentManager();
        Fragment editorImageFragment = fm.findFragmentById(R.id.canvas_container);
        //Fragment topMenuFragment = fm.findFragmentById(R.id.top_menu_fragment);


        // Maybe check if editorImageFragment is not null, then close the fragment, update the fragment?
        if (editorImageFragment == null) {  // if there is no editorImageFragment yet, make new one
            //editorImageFragment = new EditorImageFragment();
            editorImageFragment = new CanvasFragment(); //Note that this is CanvasFragment, must merge CanvasFragment with EditorImageFragment!!!!!!
            Fragment topMenuFragment = new TopMenuFragment();
            Fragment bottomMenuFragment = new BottomMenuFragment();
            fm.beginTransaction()
                    .add(R.id.canvas_container, editorImageFragment)
                    .add(R.id.top_menu_fragment, topMenuFragment)
                    .add(R.id.bottom_menu_fragment, bottomMenuFragment)
                    .commit();
        }

    }

    public Uri getPhoto() { return photo; }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check which request we're responding to
        // Request Code from Camera
        if (requestCode == REQUEST_PHOTO) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
                //Clear stickers
                canvasFragment.clearStickers();
                canvasFragment.updatePhoto();
            }
        }
        super.onActivityResult(requestCode, resultCode, data); // test
    }
}
