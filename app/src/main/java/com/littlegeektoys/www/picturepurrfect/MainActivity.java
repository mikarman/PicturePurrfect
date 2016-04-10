package com.littlegeektoys.www.picturepurrfect;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_PHOTO = 0;

    private Button mTakePhotoButton;
    private Button mExistingPhotoButton;
    private File mPhotoFile;
    private FileMetadata mFile;
    private ImageView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.main_menu);

        mTitle = (ImageView) findViewById(R.id.Title);

        PackageManager packageManager = getPackageManager();
        mFile = new FileMetadata();
        mPhotoFile = FileLab.get(this).getPhotoFile(mFile);

        mTakePhotoButton = (Button) findViewById(R.id.TakePicture);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mTakePhotoButton.setEnabled(canTakePhoto);

        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mTakePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }

        });
        mExistingPhotoButton = (Button) findViewById(R.id.OpenExisting);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");

        /* Testing purpose, set title image to photo
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mTitle.setImageDrawable(null);
            Toast.makeText(this, "no image :(", Toast.LENGTH_LONG).show();
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), this);
            mTitle.setImageBitmap(bitmap);
        }
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_PHOTO) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Uri photo = data.getData();
                // TODO: Call editor activity
            }
        }
    }
}
