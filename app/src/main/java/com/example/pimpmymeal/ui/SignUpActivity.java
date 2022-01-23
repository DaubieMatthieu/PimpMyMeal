package com.example.pimpmymeal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.services.AuthenticationService;

public class SignUpActivity extends AppCompatActivity {
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
        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();
        String inputPasswordConfirmation = passwordConfirmation.getText().toString();
        AuthenticationService.trySignUp(this, inputUsername, inputPassword, inputPasswordConfirmation);
    }

    public void signInClicked(View v) {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.putExtra("launched_from", "sign-up");
        startActivity(intent);
    }
}
