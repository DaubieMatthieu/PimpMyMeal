package com.example.pimpmymeal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;

public class SignUpActivity extends AppCompatActivity {

    private static final String usernameGeneric = "user";
    private static final String passwordGeneric = "12345";

    EditText username, password, passwordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username_edit_text);
        password = findViewById(R.id.password_edit_text);
        passwordConfirmation = findViewById(R.id.confirm_password_edit_text);
    }

    public void signUpClicked(View v) {
        //TODO can be improved
        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();
        String inputPasswordConfirmation =  passwordConfirmation.getText().toString();

        if (inputUsername.isEmpty() || inputPassword.isEmpty() || inputPasswordConfirmation.isEmpty()) {
            Toast.makeText(this, "Don´t leave fields in blank", Toast.LENGTH_SHORT).show();
        } else {
            if (validateEntrance(inputUsername, inputPassword, inputPasswordConfirmation)) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void signInClicked(View v) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    private boolean validateEntrance(String name, String pass, String passConf) {
        return name.equals(usernameGeneric) && pass.equals(passwordGeneric) && passConf.equals(passwordGeneric);
    }
}
