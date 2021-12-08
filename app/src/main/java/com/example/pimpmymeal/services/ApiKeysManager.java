package com.example.pimpmymeal.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.pimpmymeal.R;

public class ApiKeysManager {
    /**
     * an utility class meant to provide and switch between the different api keys of the project
     * since we are using the free version of the Edamam recipe search API,
     * we only have 1000 requests/month/key, so we have to switch keys when one is depleted
     * with 4 api keys we should be able to make 4000 requests/month which is more than enough
     */
    private enum API_KEYS {
        DAUBIE("505fca0ee6msh144409df3719721p12f7fcjsn1839144ccca9"),
        HEINZE("19db5ea4b0mshbaec07b8a3e32f0p100177jsnf4e18795ea6a"),
        ;

        private final String value;

        API_KEYS(String value) {
            this.value = value;
        }
    }

    /**
     * Returns the currently used API key (list of keys is in the API_KEYS enum)
     *
     * @param context Android Context, needed to fetch the preferences
     * @return The current API key, as a String
     */
    public static String getCurrentKey(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        String currentApiKey = sharedPref.getString(context.getString(R.string.current_api_key), API_KEYS.values()[0].toString());
        return API_KEYS.valueOf(currentApiKey).value;
    }

    /**
     * Switch to the next API key (in the API_KEYS enum).
     *
     * @param context Android Context, needed to fetch and modify the preferences
     * @return The new API key, as a String
     */
    public static String switchKey(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        Log.d("ApiKeysManager", "Switching API key");
        String currentKey = sharedPref.getString(context.getString(R.string.current_api_key), API_KEYS.values()[0].toString());
        int currentKeyIndex = API_KEYS.valueOf(currentKey).ordinal();
        int newKeyIndex = currentKeyIndex + 1 % API_KEYS.values().length;
        String newKey = API_KEYS.values()[newKeyIndex].toString();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.current_api_key), newKey);
        editor.apply();
        return newKey;
    }
}
