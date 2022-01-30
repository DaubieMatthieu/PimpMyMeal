package com.example.pimpmymeal.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.model.Recipe;
import com.example.pimpmymeal.services.UserPreferencesService;
import com.example.pimpmymeal.services.Utilities;

public class RecipeActivity extends AppCompatActivity {
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        TextView recipeNameTv = findViewById(R.id.recipe_name);
        recipeNameTv.setText(recipe.name);
        TextView sourceTv = findViewById(R.id.source);
        sourceTv.setText(recipe.source);
        Button browseRecipeBtn = findViewById(R.id.browse_recipe);
        browseRecipeBtn.setOnClickListener(view -> startActivity(
                new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.sourceUri))));
        ImageButton favoriteRecipeBtn = findViewById(R.id.favorite_recipe_btn);
        favoriteRecipeBtn.setOnClickListener(this::switchLike);
        if (recipe.liked) updateColor(favoriteRecipeBtn);
        ImageView recipeIv = findViewById(R.id.recipe_image);
        Utilities.displayRecipeImage(recipeIv, recipe);
        ListView ingredientsListLv = findViewById(R.id.ingredients_list);
        ingredientsListLv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipe.ingredients));
    }

    public void switchLike(View view) {
        if (recipe.liked) {
            recipe.liked = false;
            UserPreferencesService.removeFavoriteRecipe(this, recipe);
        } else {
            recipe.liked = true;
            UserPreferencesService.addFavoriteRecipe(this, recipe);
        }
        updateColor((ImageButton) view);
    }

    public void updateColor(ImageButton view) {
        int resId = (recipe.liked) ? R.drawable.ic_baseline_thumb_up_carmina_24 : R.drawable.ic_baseline_thumb_up_empty_24;
        view.setImageResource(resId);
    }
}
