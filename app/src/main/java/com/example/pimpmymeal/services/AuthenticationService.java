package com.example.pimpmymeal.services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.pimpmymeal.ui.HomeActivity;

public class AuthenticationService {

    public static void trySignIn(Context context, String username, String password) {
        if (username.isEmpty() || password.isEmpty())
            Toast.makeText(context, "Don´t leave fields in blank", Toast.LENGTH_SHORT).show();
        else if (username.equals(UserPreferencesService.username) && password.equals(UserPreferencesService.password))
            launchHome(context);
        else
            Toast.makeText(context, "Incorrect username or password", Toast.LENGTH_SHORT).show();
    }

    public static void trySignUp(Context context, String username, String password, String passwordConfirmation) {
        if (username.isEmpty() || password.isEmpty())
            Toast.makeText(context, "Don´t leave fields in blank", Toast.LENGTH_SHORT).show();
        else if (!password.equals(passwordConfirmation))
            Toast.makeText(context, "The password and the password confirmation should be the same", Toast.LENGTH_SHORT).show();
        else {
            UserPreferencesService.updateUsername(context, username);
            UserPreferencesService.updatePassword(context, password);
            launchHome(context);
        }
    }

    public static void launchHome(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }
}
