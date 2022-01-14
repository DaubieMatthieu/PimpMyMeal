package com.example.pimpmymeal.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.model.Recipe;
import com.example.pimpmymeal.services.Utilities;

import java.util.List;

public class RecipesAdapter extends ArrayAdapter<Recipe> {

    public RecipesAdapter(Context context, List<Recipe> recipes) {
        super(context, 0, recipes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = getContext();
        Recipe recipe = getItem(position);
        if (convertView == null) convertView = LayoutInflater.from(context)
                .inflate(R.layout.recipe_preview, parent, false);

        FrameLayout recipeContainer = convertView.findViewById(R.id.recipe_container);
        recipeContainer.setOnClickListener(view -> {
            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra("recipe", recipe);
            context.startActivity(intent);
        });
        TextView recipeName = convertView.findViewById(R.id.recipe_name);
        recipeName.setText(recipe.name);
        ImageView recipeImage = convertView.findViewById(R.id.recipe_image);
        Utilities.displayRecipeImage(recipeImage, recipe);
        return convertView;
    }
}
