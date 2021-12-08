package com.example.pimpmymeal.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pimpmymeal.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ApiRepository {
    private static final String ENDPOINT_URL = "https://edamam-recipe-search.p.rapidapi.com/search";
    private final Context context;
    private String API_KEY;

    public ApiRepository(Context context) {
        this.context = context;
        API_KEY = ApiKeysManager.getCurrentKey(context);
    }

    /**
     * public method to make a simple search call to the Edamam API.
     * More parameters might be added later.
     *
     * @param callback   callback function, consumes the API response parsed as a Recipe List
     * @param searchKeys list of search keys in string, separator (space, coma) is irrelevant
     */
    public void search(Consumer<List<Recipe>> callback, String searchKeys) {
        HashMap<String, String> args = new HashMap<String, String>() {{
            put("q", searchKeys);
        }};
        Consumer<JSONObject> jsonObjectConsumer = response -> {
            try {
                callback.accept(jsonArrayToRecipeList(response.getJSONArray("hits")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        call(args, jsonObjectConsumer, true);
    }

    /**
     * private method to build and execute calls to the Edamam API.
     *
     * @param args         parameters of the API call, provided as a Map
     * @param callback     callback function (consumes the response of the API as a JSONObject)
     * @param switchOnFail If True: on 401 HTTP error, switch API key and relaunch the call once
     */
    private void call(Map<String, String> args, Consumer<JSONObject> callback, boolean switchOnFail) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String argsString = args.keySet().stream()
                .map(key -> key + "=" + args.get(key))
                .collect(Collectors.joining("&", "?", ""));
        String url = ENDPOINT_URL + argsString;
        Response.Listener<JSONObject> responseConsumer = response -> {
            Log.d("Volley response", response.toString());
            callback.accept(response);
        };
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, responseConsumer, error -> {
            Log.e("Volley response", error.toString());
            if (error.networkResponse.statusCode == 401) {
                if (switchOnFail) {
                    API_KEY = ApiKeysManager.switchKey(context);
                    call(args, callback, false);
                } else {
                    Log.d("ApiRepository", "API queries limit reached");
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-rapidapi-key", API_KEY);
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }

    private static ArrayList<Recipe> jsonArrayToRecipeList(JSONArray jsonArray) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonRecipe = jsonArray.getJSONObject(i).getJSONObject("recipe");
                recipes.add(new Recipe(jsonRecipe));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
