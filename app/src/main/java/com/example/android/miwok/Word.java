package com.example.android.miwok;

import android.content.Context;
import android.media.Image;

/**
 * Created by pitrs on 11.03.2018.
 */

public class Word {

    /**
     * @param mDefaultTranslation as a parametr for default language.
     *
     */
    private String mDefaultTranslation;

    /**
     * @param mMiwoktTranslation as a parametr for miwok language.
     *
     */
    private String mMiwokTranslation;

    /**
     * @param mImage as a parametr for aditional image
     */
    private Integer mImageResourceID = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    private int mDefaultSound;


    /**Creating a new object
     * @param defaultTranslation
     */

    public Word(String defaultTranslation, String miwokTranslation, Integer defaultImage, int defaultSound) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = defaultImage;
        mDefaultSound = defaultSound;

    }

    public Word(String defaultTranslation, String miwokTranslation, int defaultSound) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mDefaultSound = defaultSound;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getDefaultImage () {
        return mImageResourceID;
    }

    public boolean hasImage(){
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }


    public int getDefaultSound () {
        return mDefaultSound;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mDefaultSound=" + mDefaultSound +
                '}';
    }
}
