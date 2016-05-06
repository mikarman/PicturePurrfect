package com.littlegeektoys.www.picturepurrfect;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
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
    public void onTextInput(String text){
        CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
        canvasFragment.textOn(text);
    }

    @Override
    public void onColorSelect(String color) {
        CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
        canvasFragment.changeColor(color);
    }

    @Override
    public void onToolSelect(ToolName tool) {


        switch (tool) {
            case COLOR: {
                if (fm != null) {
                    CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
                  //  canvasFragment.changeColor();
                }
                break;
            }
            case STICKER: {
                //CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
                //canvasFragment.stickerOn();
                break;
            }
            case TEXT: {

                //CanvasFragment canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
                //canvasFragment.textOn();
                //break;
            }
            default:
                break;
        }
    }

    // Top Menu callbacks implementation

    /**
     * Checks for write permissions and then calls the saveImage method in the CanvasFragment
     */
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
        finish();
    }

    @Override
    public void onShare() {
     /*   Bitmap mBitmap;
        String pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap,"title", null);
        Uri bmpUri = Uri.parse(pathofBmp);
        final Intent emailIntent1 = new Intent(     android.content.Intent.ACTION_SEND);
        emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
        emailIntent1.setType("image/png");*/
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

    // Bottom Menu Callbacks
    @Override
    public void onGoBack() {
        Log.d(TAG, "OnGoBack");
    }

    @Override
    public void onSwitchFragment(Fragment f) {
        Log.d(TAG, "OnSwitchFragment");
    }

    // Creating Intent for this EditorActivity
    public static Intent newIntent(Context packageContext, Uri pic) {
        mContext = packageContext;
        Intent intent = new Intent(packageContext, EditorActivity.class);
        intent.setData(pic);
        intent.putExtra(CLOSE_HOSTING_ACTIVITY, true);
        return intent;
    }

    // EditorActivity Overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_fragment_container);

        photo = (Uri) getIntent().getData();
        final boolean closeActivity = (boolean) getIntent()
                .getBooleanExtra(CLOSE_HOSTING_ACTIVITY, true); //Uh, no idea if this will ever be needed, kill main menu activity maybe??

        fm = getSupportFragmentManager();
        Fragment editorImageFragment = fm.findFragmentById(R.id.canvas_container);

        // Maybe check if editorImageFragment is not null, then close the fragment, update the fragment?
        if (editorImageFragment == null) {  // if there is no editorImageFragment yet, make new one
            //editorImageFragment = new EditorImageFragment();
            editorImageFragment = new CanvasFragment(); //Note that this is CanvasFragment, must merge CanvasFragment with EditorImageFragment!!!!!!
            Fragment topMenuFragment = new TopMenuFragment();
            Fragment bottomMenuFragment = new BottomMenuFragment();
            fm.beginTransaction()
                    .replace(R.id.canvas_container, editorImageFragment)
                    .replace(R.id.topScrollView, topMenuFragment)
                    .replace(R.id.bottomScrollView, bottomMenuFragment)
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
                //Clear sticker
                canvasFragment.clearStickers();
                canvasFragment.updatePhoto();
            }
        }
        super.onActivityResult(requestCode, resultCode, data); // test
    }
}
