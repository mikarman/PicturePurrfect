package com.littlegeektoys.www.picturepurrfect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int TAKE_PHOTO = 0;
    private static final int GRAB_PHOTO = 1;

    private ImageButton mTakePhotoButton;
    private ImageButton mExistingPhotoButton;
    private File mPhotoFile;
    private FileMetadata mFile;
    private ImageView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.main_menu);

        mTitle = (ImageView) findViewById(R.id.Title);

        final PackageManager packageManager = getPackageManager();
        mFile = new FileMetadata();
        mPhotoFile = FileLab.get(this).getPhotoFile(mFile);

        mTakePhotoButton = (ImageButton) findViewById(R.id.TakePicture);

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
                startActivityForResult(captureImage, TAKE_PHOTO);
            }

        });

        mExistingPhotoButton = (ImageButton) findViewById(R.id.OpenExisting);

        mExistingPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
            }

        });

    }

    public void getImageFromGallery(){
        if(Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getBaseContext(), "Later the user will be able to select an image from the gallery to edit", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GRAB_PHOTO);
            }
        } else {
            Toast.makeText(getBaseContext(), "Later the user will be able to select an image from the gallery to edit", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, GRAB_PHOTO);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        // Request Code from Camera
        if (requestCode == TAKE_PHOTO) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Uri photo = Uri.fromFile(mPhotoFile);
                // Make intent to start EditorActivity with the photo Uri that we got from camera
                Intent intent = EditorActivity.newIntent(this, photo);
                startActivity(intent);
            }
        }
        else if (requestCode == GRAB_PHOTO) {
            Uri relativeUri = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(relativeUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String absPath = cursor.getString(columnIndex);
            cursor.close();
            Uri absUri = Uri.parse(absPath);
            Intent intent = EditorActivity.newIntent(this, absUri);
            startActivity(intent);
        }
        super.onActivityResult(requestCode, resultCode, data); //Check if this causes bugs
    }

}
