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
import android.widget.Toast;

import java.io.File;

/**
 * Created by Michael Karman on 4/10/2016.
 */
public class EditorActivity extends AppCompatActivity implements MenuToolInterface, TopMenuFragment.TopMenuCallback,
        CanvasView.CanvasCallback {

    /* Not used
    private static final String EXTRA_PIC_ID =
            "com.littlegeektoys.www.picturepurrfect.pic_id";
    */

    private static final String TAG = "EditorActivity";
    private static final int REQUEST_PHOTO = 0;
    private CanvasFragment canvasFragment;

    private static final String CLOSE_HOSTING_ACTIVITY =
            "com.littlegeektoys.www.picturepurrfect.close_hosting_activity_boolean";

    private Uri photo;
    private FragmentManager fm;
    public static Context mContext;

    private Uri savedImageUri;


    // Bottom menu callbacks
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
        return; // Unused on this version of the app
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

                savedImageUri = Uri.parse(canvasFragment.saveImage());
            }
        } else {
            savedImageUri = Uri.parse(canvasFragment.saveImage());
        }
    }

    @Override
    public void onReturnMenu() {
        finish();
    }

    @Override
    public void onShare() {
        onSave();
        final Intent i = new Intent(android.content.Intent.ACTION_SEND);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(Intent.EXTRA_STREAM, savedImageUri);
        i.setType("image/png");
        Intent.createChooser(i, getString(R.string.send_report));
        startActivity(i);

    }

    @Override
    public void onRetake() {
        FileMetadata mFile = new FileMetadata();
        File mPhotoFile = FileLab.get(this).getPhotoFile(mFile);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager pm = getPackageManager();
        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(pm) != null;
        if (canTakePhoto) {
            photo = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photo);
            Log.d(TAG, "URI photo " + photo.toString());
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

    /**
     * Implement CanvasView callback to hide/unhide menus in hosting activity
     */
    @Override
    public void hideMenu() {
        hideUnhide();
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

        if (editorImageFragment == null) {  // if there is no editorImageFragment yet, make new one
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
            Log.d(TAG, "Retake Result");
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "Retake Result OK");
                canvasFragment = (CanvasFragment) fm.findFragmentById(R.id.canvas_container);
                //Clear sticker
                canvasFragment.clearStickers();
                canvasFragment.updatePhoto();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Function for hiding and unhiding menus
    public void hideUnhide() {
        if (fm != null) {
            Fragment topMenu = fm.findFragmentById(R.id.topScrollView);
            Fragment bottomMenu = fm.findFragmentById(R.id.bottomScrollView);
            if (topMenu.isDetached() || bottomMenu.isDetached()) {
                fm.beginTransaction()
                        .attach(topMenu)
                        .attach(bottomMenu)
                        .commit();
            } else {
                fm.beginTransaction()
                        .detach(topMenu)
                        .detach(bottomMenu)
                        .commit();
            }

            Toast.makeText(this, "Tap with 2 fingers to Hide/Unhide Menus", Toast.LENGTH_SHORT).show();
        }
    }

}
