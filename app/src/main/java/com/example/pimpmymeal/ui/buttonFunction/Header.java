package com.example.pimpmymeal.ui.buttonFunction;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

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

    //TODO implement button actions
    private void inflateHeader() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.header, this);
        loginButton = findViewById(R.id.optionsButton);

        loginButton.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Menu Clicked", Toast.LENGTH_SHORT).show();
            PopupMenu menu = new PopupMenu(loginButton.getContext(), view);
            menu.getMenuInflater().inflate(R.menu.menu_config, menu.getMenu());
            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.helpItem:
                        Log.d("Items", "Help Clicked");
                        Toast.makeText(loginButton.getContext(), "Help Clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.settingsItem:
                        Log.d("Items", "Settings Clicked");
                        Toast.makeText(loginButton.getContext(), "Settings Clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.logOutItem:
                        Log.d("Items", "Log Out Clicked");
                        Toast.makeText(loginButton.getContext(), "Log Out Clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            });
            menu.show();
            Log.d("Items", "Menu Clicked");
        });
    }

}
