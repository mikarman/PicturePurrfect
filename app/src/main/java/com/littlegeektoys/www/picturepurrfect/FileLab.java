package com.littlegeektoys.www.picturepurrfect;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Michael Karman on 4/8/2016.
 */
public class FileLab {

    private static FileLab sPicture;
    private Context mContext;

    public static FileLab get(Context context) {
        if (sPicture == null) {
            sPicture = new FileLab(context);
        }
        return sPicture;
    }

    private FileLab(Context context) {
        mContext = context.getApplicationContext();
    }

    /*
    public FileMetadata getFileMetadata(UUID id) {
        for
    }
    */

    public File getPhotoFile(FileMetadata file) {
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, file.getPhotoFilename());
    }
}
