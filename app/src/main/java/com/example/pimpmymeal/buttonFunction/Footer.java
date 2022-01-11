package com.example.pimpmymeal.buttonFunction;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.ui.GeneratorActivity;
import com.example.pimpmymeal.ui.HomeActivity;
import com.example.pimpmymeal.ui.ProfileActivity;
import com.example.pimpmymeal.ui.SearchActivity;

public class Footer extends LinearLayout {

    public static final String TAG = Footer.class.getSimpleName();

    private ImageButton homeButton, profileButton, recipeButton, searchButton;

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

        homeButton = (ImageButton) findViewById(R.id.homeButtonNavigation);
        profileButton = (ImageButton) findViewById(R.id.userButtonNavigation);
        recipeButton = (ImageButton) findViewById(R.id.recipeButtonNavigation);
        searchButton = (ImageButton) findViewById(R.id.fridgeButtonNavigation);

        homeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToHomeActivity(view);
            }
        });

        profileButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToProfileActivity(view);
            }
        });

        recipeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToGeneratorActivity(view);
            }
        });

        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToSearchActivity(view);
            }
        });
    }

    public void GoToHomeActivity(View view) {
        Intent intent = new Intent(view.getContext(), HomeActivity.class);
        view.getContext().startActivity(intent);
    }

    public void GoToProfileActivity(View view) {
        Intent intent = new Intent(view.getContext(), ProfileActivity.class);
        view.getContext().startActivity(intent);
    }

    public void GoToSearchActivity(View view) {
        Intent intent = new Intent(view.getContext(), SearchActivity.class);
        view.getContext().startActivity(intent);
    }

    public void GoToGeneratorActivity(View view) {
        Intent intent = new Intent(view.getContext(), GeneratorActivity.class);
        view.getContext().startActivity(intent);
    }

}
