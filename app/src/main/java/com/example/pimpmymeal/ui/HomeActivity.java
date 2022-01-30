package com.example.pimpmymeal.ui;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.services.UserPreferencesService;

public class HomeActivity extends AppCompatActivity {
    RecipesAdapter recipesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Header header = findViewById(R.id.header);
        header.initHeader();
        Footer footer = findViewById(R.id.footer);
        footer.initFooter();

        GridView favoriteRecipesView = findViewById(R.id.favorite_recipes);
        recipesAdapter = new RecipesAdapter(this,UserPreferencesService.favoriteRecipes);
        favoriteRecipesView.setAdapter(recipesAdapter);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        recipesAdapter.notifyDataSetChanged();
    }
}
