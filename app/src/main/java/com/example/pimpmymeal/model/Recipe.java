package com.example.pimpmymeal.model;

import org.json.JSONException;
import org.json.JSONObject;

//TODO complete
public class Recipe {
    public final String name;

    public Recipe(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString("label");
    }
}
