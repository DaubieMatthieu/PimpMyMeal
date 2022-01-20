package com.example.pimpmymeal.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;

import com.example.pimpmymeal.R;

public class Header extends LinearLayout {
    private ImageButton loginButton;

    public Header(Context context) {
        super(context);
    }

    public Header(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Header(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initHeader() {
        inflateHeader();
    }

    private void inflateHeader() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.header, this);
        loginButton = findViewById(R.id.optionsButton);

        loginButton.setOnClickListener(view -> {
            PopupMenu menu = new PopupMenu(loginButton.getContext(), view);
            menu.getMenuInflater().inflate(R.menu.menu_config, menu.getMenu());
            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.helpItem:
                    case R.id.settingsItem:
                        return true;
                    case R.id.logOutItem:
                        Intent intent = new Intent(view.getContext(), SignInActivity.class);
                        view.getContext().startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            });
            menu.show();
        });
    }
}
