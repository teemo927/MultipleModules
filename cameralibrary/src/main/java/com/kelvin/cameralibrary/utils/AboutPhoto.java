package com.kelvin.cameralibrary.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.kelvin.cameralibrary.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Authority: Asus
 * Date: 2018-03-06  15:13
 */

public class AboutPhoto {

    public static List<ImageModel> getImages(Context context) {
        List<ImageModel> list = new ArrayList<ImageModel>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA,};
        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " desc";
        Cursor cursor = contentResolver.query(uri, projection, null, null, sortOrder);
        int iId = cursor.getColumnIndex(MediaStore.Images.Media._ID);
        int iPath = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(iId);
            String path = cursor.getString(iPath);
            ImageModel imageModel = new ImageModel(id, path);
            list.add(imageModel);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
