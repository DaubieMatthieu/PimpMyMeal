package com.example.pimpmymeal.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.model.Recipe;
import com.example.pimpmymeal.services.Utilities;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        TextView recipeNameTv = findViewById(R.id.recipe_name);
        recipeNameTv.setText(recipe.name);
        TextView sourceTv = findViewById(R.id.source);
        sourceTv.setText(recipe.source);
        Button browseRecipeBtn = findViewById(R.id.browse_recipe);
        browseRecipeBtn.setOnClickListener(view -> startActivity(
                new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.sourceUri))));
        ImageView recipeIv = findViewById(R.id.recipe_image);
        Utilities.displayRecipeImage(recipeIv, recipe);
        ListView ingredientsListLv = findViewById(R.id.ingredients_list);
        ingredientsListLv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipe.ingredients));
    }
}
