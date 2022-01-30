package com.example.pimpmymeal.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.model.Recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class UserPreferencesService {
    public static String username, password;
    public static int dietIndex;
    public static List<Recipe> favoriteRecipes;
    public static Consumer<Boolean> onInitCompleted = (b) -> {
    };

    public static void init(Context context) {
        loadPreferences(context);
    }

    public static boolean isFirstAuth() {
        return username == null;
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
        saveRecipeList(context, favoriteRecipes, R.string.fav_recipes_pref);
    }

    public static void removeFavoriteRecipe(Context context, Recipe recipe) {
        favoriteRecipes.removeIf(r -> r.equals(recipe));
        saveRecipeList(context, favoriteRecipes, R.string.fav_recipes_pref);
    }

    private static void loadPreferences(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        username = sharedPref.getString(context.getString(R.string.username_pref), null);
        password = sharedPref.getString(context.getString(R.string.password_pref), null);
        dietIndex = sharedPref.getInt(context.getString(R.string.diets_pref), 0);
        Set<String> recipesNameSet = sharedPref.getStringSet(context.getString(R.string.fav_recipes_pref), new HashSet<>());
        favoriteRecipes = recipeListLoader(context, recipesNameSet);
    }

    private static List<Recipe> recipeListLoader(Context context, Set<String> recipesNamesSet) {
        ApiRepository apiRepository = new ApiRepository(context);
        List<Recipe> recipeSet = new ArrayList<>();
        for (String recipeName : recipesNamesSet)
            apiRepository.search(recipes -> {
                Recipe recipe = recipes.get(0);
                recipeSet.add(recipe);
                recipe.liked = true;
            }, recipeName);
        Utilities.callbackAfterLoad(recipesNamesSet, recipeSet);
        return recipeSet;
    }

    private static Set<String> recipeListCompressor(List<Recipe> recipeSet) {
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

    private static void saveRecipeList(Context context, List<Recipe> recipeList, int prefKeyId) {
        saveStringSet(context, recipeListCompressor(recipeList), prefKeyId);
    }

    private static void saveStringSet(Context context, Set<String> stringSet, int prefKeyId) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(context.getString(prefKeyId), stringSet);
        editor.apply();
    }
}
