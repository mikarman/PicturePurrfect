package com.littlegeektoys.www.picturepurrfect;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.UUID;

/**
 * Created by Michael Karman on 4/8/2016.
 */
public class FileLab {

    private static FileLab sFileLab;
    private Context mContext;

    public static FileLab get(Context context) {
        if (sFileLab == null) {
            sFileLab = new FileLab(context);
        }
        return sFileLab;
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
