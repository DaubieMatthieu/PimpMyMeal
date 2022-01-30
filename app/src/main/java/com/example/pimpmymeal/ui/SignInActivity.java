package com.example.pimpmymeal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.services.AuthenticationService;
import com.example.pimpmymeal.services.UserPreferencesService;

public class SignInActivity extends AppCompatActivity {
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.UsernameEditTextSignIn);
        password = findViewById(R.id.PasswordEditTextSignIn);

        String launchedFrom = getIntent().getStringExtra("launched_from");
        if (launchedFrom == null) launchedFrom = "app-start";
        if (launchedFrom.equals("app-start")) UserPreferencesService.init(this);
        if (!UserPreferencesService.isFirstAuth()) {
            username.setText(UserPreferencesService.username);
            password.setText(UserPreferencesService.password);
            if (launchedFrom.equals("app-start")) {
                UserPreferencesService.onInitCompleted = (b) -> logInClicked(null);
            }
        } else if (!launchedFrom.equals("sign-up")) signUpBackClicked(null);
    }

    public void logInClicked(View v) {
        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();
        AuthenticationService.trySignIn(this, inputUsername, inputPassword);
    }

    public void signUpBackClicked(View v) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
