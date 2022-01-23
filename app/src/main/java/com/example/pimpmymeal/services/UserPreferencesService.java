package com.example.pimpmymeal.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.model.Recipe;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserPreferencesService {
    public static String username, password; //TODO later on the password should be encrypted
    public static int dietIndex;
    public static Set<Recipe> favoriteRecipes, recipesToTry;

    public static void init(Context context) {
        loadPreferences(context);
    }

    public static boolean isFirstAuth() {
        return !username.isEmpty();
    }

    public static void updateUsername(Context context, String username) {
        UserPreferencesService.username = username;
        saveString(context, username, R.string.username_pref);
    }

    public static void updatePassword(Context context, String password) {
        UserPreferencesService.password = password;
        saveString(context, password, R.string.password_pref);
    }

    public static void updateDietIndex(Context context, int dietIndex) {
        UserPreferencesService.dietIndex = dietIndex;
        saveInt(context, UserPreferencesService.dietIndex, R.string.diets_pref);
    }

    public static void addFavoriteRecipe(Context context, Recipe recipe) {
        favoriteRecipes.add(recipe);
        saveRecipeSet(context, favoriteRecipes, R.string.fav_recipes_pref);
    }

    public static void removeFavoriteRecipe(Context context, Recipe recipe) {
        favoriteRecipes.remove(recipe);
        saveRecipeSet(context, favoriteRecipes, R.string.fav_recipes_pref);
    }

    public static void addRecipeToTry(Context context, Recipe recipe) {
        recipesToTry.add(recipe);
        saveRecipeSet(context, recipesToTry, R.string.recipes_to_try_pref);
    }

    public static void removeRecipeToTry(Context context, Recipe recipe) {
        recipesToTry.remove(recipe);
        saveRecipeSet(context, recipesToTry, R.string.recipes_to_try_pref);
    }

    private static void loadPreferences(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        username = sharedPref.getString(context.getString(R.string.username_pref), null);
        password = sharedPref.getString(context.getString(R.string.password_pref), null);
        dietIndex = sharedPref.getInt(context.getString(R.string.diets_pref), 0);
        Set<String> recipesNameSet = sharedPref.getStringSet(context.getString(R.string.fav_recipes_pref), new HashSet<>());
        favoriteRecipes = recipeSetLoader(context, recipesNameSet);
        Set<String> recipeToTrySet = sharedPref.getStringSet(context.getString(R.string.recipes_to_try_pref), new HashSet<>());
        recipesToTry = recipeSetLoader(context, recipeToTrySet);
    }

    private static Set<Recipe> recipeSetLoader(Context context, Set<String> recipesNamesSet) {
        ApiRepository apiRepository = new ApiRepository(context);
        Set<Recipe> recipeSet = new HashSet<>();
        for (String recipeName : recipesNamesSet)
            apiRepository.search(recipes -> recipeSet.add(recipes.get(0)), recipeName);
        return recipeSet;
    }

    private static Set<String> recipeListCompressor(Set<Recipe> recipeSet) {
        return recipeSet.stream().map(x -> x.name).collect(Collectors.toSet());
    }

    private static void saveInt(Context context, int integer, int prefKeyId) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(context.getString(prefKeyId), integer);
        editor.apply();
    }

    private static void saveString(Context context, String str, int prefKeyId) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(prefKeyId), str);
        editor.apply();
    }

    private static void saveRecipeSet(Context context, Set<Recipe> recipeSet, int prefKeyId) {
        saveStringSet(context, recipeListCompressor(recipeSet), prefKeyId);
    }

    private static void saveStringSet(Context context, Set<String> stringSet, int prefKeyId) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(context.getString(prefKeyId), stringSet);
        editor.apply();
    }
}
