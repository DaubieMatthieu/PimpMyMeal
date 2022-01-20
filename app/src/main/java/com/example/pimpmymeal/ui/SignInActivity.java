package com.example.pimpmymeal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.services.UserPreferencesService;

public class SignInActivity extends AppCompatActivity {

    private static final String usernameGeneric = "user";
    private static final String passwordGeneric = "12345";

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        UserPreferencesService.init(this);

        username = findViewById(R.id.UsernameEditTextSignIn);
        password = findViewById(R.id.PasswordEditTextSignIn);
    }

    public void logInClicked(View v) {
        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();
        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(this, "Don´t leave fields in blank", Toast.LENGTH_SHORT).show();
        } else {
            if (validateEntrance(inputUsername, inputPassword)) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateEntrance(String name, String pass) {
        return name.equals(usernameGeneric) && pass.equals(passwordGeneric);
    }

    public void signUpBackClicked(View v) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
