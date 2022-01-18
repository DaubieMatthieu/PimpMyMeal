package com.example.pimpmymeal.ui.buttonFunction;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.ui.GeneratorActivity;
import com.example.pimpmymeal.ui.HomeActivity;
import com.example.pimpmymeal.ui.ProfileActivity;
import com.example.pimpmymeal.ui.SearchActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.PopupMenu;
import android.widget.Toast;

//TODO there might be a way to simplify all this
public class NavigationButtons extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    public void goToHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void goToProfileActivity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goToSearchActivity(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void goToGeneratorActivity(View view) {
        Intent intent = new Intent(this, GeneratorActivity.class);
        startActivity(intent);
    }
    
    public void onMenuClicked(View view)
    {
        //TODO is it useful ? might remove it
        Toast.makeText(view.getContext(), "Menu Clicked", Toast.LENGTH_SHORT).show();
        PopupMenu menu = new PopupMenu(this, view);
        getMenuInflater().inflate(R.menu.menu_config, menu.getMenu());
        menu.setOnMenuItemClickListener(this);
        menu.show();
        Log.d("Items", "Menu Clicked");
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.helpItem:
                Log.d("Items", "Help Clicked");
                Toast.makeText(this, "Help Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settingsItem:
                Log.d("Items", "Settings Clicked");
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logOutItem:
                Log.d("Items", "Log Out Clicked");
                Toast.makeText(this, "Log Out Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

}
