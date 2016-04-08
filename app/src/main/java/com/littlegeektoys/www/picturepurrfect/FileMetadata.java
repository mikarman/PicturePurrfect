package com.littlegeektoys.www.picturepurrfect;

import java.util.UUID;

/**
 * Created by Michael Karman on 2/6/2016.
 */
public class FileMetadata {
    private UUID mId;
    private String mTitle;;
    private boolean mSolved;
    private String mSuspect;

    public FileMetadata() {
        // Generate unique identifier
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() { return mSuspect; }

    public void setSuspect(String suspect) { mSuspect = suspect; }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
