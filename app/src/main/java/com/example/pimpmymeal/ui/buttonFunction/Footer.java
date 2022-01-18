package com.example.pimpmymeal.ui.buttonFunction;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.ui.GeneratorActivity;
import com.example.pimpmymeal.ui.HomeActivity;
import com.example.pimpmymeal.ui.ProfileActivity;
import com.example.pimpmymeal.ui.SearchActivity;

public class Footer extends LinearLayout {

    public Footer(Context context) {
        super(context);
    }

    public Footer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Footer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Footer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initFooter() {
        inflateFooter();
    }

    private void inflateFooter() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.footer, this);

        ImageButton homeButton = findViewById(R.id.homeButtonNavigation);
        ImageButton profileButton = findViewById(R.id.userButtonNavigation);
        ImageButton recipeButton = findViewById(R.id.recipeButtonNavigation);
        ImageButton searchButton = findViewById(R.id.fridgeButtonNavigation);

        homeButton.setOnClickListener(this::goToHomeActivity);

        profileButton.setOnClickListener(this::goToProfileActivity);

        recipeButton.setOnClickListener(this::goToGeneratorActivity);

        searchButton.setOnClickListener(this::goToSearchActivity);
    }

    public void goToHomeActivity(View view) {
        Intent intent = new Intent(view.getContext(), HomeActivity.class);
        view.getContext().startActivity(intent);
    }

    public void goToProfileActivity(View view) {
        Intent intent = new Intent(view.getContext(), ProfileActivity.class);
        view.getContext().startActivity(intent);
    }

    public void goToSearchActivity(View view) {
        Intent intent = new Intent(view.getContext(), SearchActivity.class);
        view.getContext().startActivity(intent);
    }

    public void goToGeneratorActivity(View view) {
        Intent intent = new Intent(view.getContext(), GeneratorActivity.class);
        view.getContext().startActivity(intent);
    }

}
