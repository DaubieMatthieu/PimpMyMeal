package com.example.pimpmymeal.model;

import android.graphics.Bitmap;

import com.example.pimpmymeal.services.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Recipe {
    public final String name;
    public final String edamamUri;
    public final String imageUri;
    private Bitmap image;
    public final String source;
    public final String sourceUri;
    public final ArrayList<String> ingredients = new ArrayList<>();

    public Recipe(JSONObject jsonObject) throws JSONException {
        this(jsonObject, true);
    }

    public Recipe(JSONObject jsonObject, boolean lazyLoading) throws JSONException {
        name = jsonObject.getString("label");
        edamamUri = jsonObject.getString("uri");
        imageUri = jsonObject.getString("image");
        if (!lazyLoading) loadImage(); // Only load if used in UI
        source = jsonObject.getString("source");
        sourceUri = jsonObject.getString("url");
        JSONArray ingredientsJson = jsonObject.getJSONArray("ingredientLines");
        // Later on we might want to use the "ingredients" key instead
        // which gives ingredients as objects (so with more information on each one).
        for (int i = 0; i < ingredientsJson.length(); i++) {
            ingredients.add(ingredientsJson.getString(i));
        }
        // More info can be extracted from the json response.
    }

    public void loadImage() {
        image = Utilities.loadImage(imageUri);
    }

    public Bitmap getImage() {
        if (image == null) loadImage();
        return image;
    }
}
