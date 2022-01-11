package com.example.pimpmymeal.buttonFunction;

import android.widget.PopupMenu;
import android.widget.Toast;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.pimpmymeal.R;

public class Header extends LinearLayout {

    public static final String TAG = Header.class.getSimpleName();

    protected ImageView logo;
    private View label;
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

    public Header(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initHeader() {
        inflateHeader();
    }

    private void inflateHeader() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.header, this);
        logo = (ImageView) findViewById(R.id.logoButton);
        label = (View) findViewById(R.id.headerEmptySpace);
        loginButton = (ImageButton) findViewById(R.id.optionsButton);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Menu Clicked", Toast.LENGTH_SHORT).show();
                PopupMenu menu = new PopupMenu(loginButton.getContext(), view);
                menu.getMenuInflater().inflate(R.menu.menu_config, menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
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
                    }
                });
                menu.show();
                Log.d("Items", "Menu Clicked");
            }
        });
    }

}
