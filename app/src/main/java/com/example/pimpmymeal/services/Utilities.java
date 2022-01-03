package com.example.pimpmymeal.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

public interface Utilities {

    @Nullable
    static Bitmap loadImage(@NonNull String uri) {
        try {
            return BitmapFactory.decodeStream(new java.net.URL(uri).openStream());
        } catch (IOException e) {
            Log.e(e.getClass().getName(), e.getMessage());
        }
        return null;
    }
}

