package com.example.pimpmymeal.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pimpmymeal.model.Recipe;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public interface Utilities {
    /**
     * Asynchronously load the image of a recipe and then display it on it ImageView.
     *
     * @param iv     ImageView on which to display the recipe image
     * @param recipe Recipe for which the image must be loaded
     */
    static void displayRecipeImage(@NonNull ImageView iv, @NonNull Recipe recipe) {
        new TaskRunner().executeAsync(recipe::getImage, iv::setImageBitmap);
    }

    static void displayImage(@NonNull ImageView iv, @NonNull String uri) {
        new TaskRunner().executeAsync(() -> loadImage(uri), iv::setImageBitmap);
    }

    @Nullable
    static Bitmap loadImage(@NonNull String uri) {
        try {
            return BitmapFactory.decodeStream(new java.net.URL(uri).openStream());
        } catch (IOException e) {
            Log.e(e.getClass().getName(), e.getMessage());
        }
        return null;
    }

    class TaskRunner {
        private final Executor executor = Executors.newSingleThreadExecutor();
        private final Handler handler = new Handler(Looper.getMainLooper());

        /**
         * Create an asynchronous task, run parallel to the main thread
         *
         * @param callable Task to do asynchronously in background
         * @param callback Task to execute once the main one is done
         * @param <R>      Type of the object returned by the callable to the callback
         */
        public <R> void executeAsync(@NonNull Callable<R> callable, @NonNull Consumer<R> callback) {
            executor.execute(() -> {
                try {
                    final R result = callable.call();
                    handler.post(() -> callback.accept(result));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

