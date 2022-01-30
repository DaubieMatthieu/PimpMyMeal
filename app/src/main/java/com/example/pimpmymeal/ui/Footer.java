package com.example.pimpmymeal.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.pimpmymeal.R;

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

    public void initFooter() {
        inflateFooter();
    }

    private void inflateFooter() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.footer, this);

        ImageButton homeButton = findViewById(R.id.homeButtonNavigation);
        ImageButton profileButton = findViewById(R.id.userButtonNavigation);
        ImageButton searchButton = findViewById(R.id.fridgeButtonNavigation);

        if (getContext().getClass() == HomeActivity.class) {
            homeButton.setEnabled(false);
            homeButton.setImageResource(R.drawable.ic_baseline_home_active_24);
        } else if (getContext().getClass() == ProfileActivity.class) {
            profileButton.setEnabled(false);
            profileButton.setImageResource(R.drawable.ic_baseline_account_circle_active_24);
        } else if (getContext().getClass() == SearchActivity.class) {
            searchButton.setEnabled(false);
            searchButton.setImageResource(R.drawable.ic_baseline_image_search_active_24);
        }
        homeButton.setOnClickListener(this::goToHomeActivity);
        profileButton.setOnClickListener(this::goToProfileActivity);
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
}
