package com.example.pimpmymeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pimpmymeal.ui.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.UsernameEditText);
        password = findViewById(R.id.PasswordEditText);
        repassword = findViewById(R.id.ConfirmPasswordEditText);
        signup = findViewById(R.id.signup_button);
        signin = findViewById(R.id.signin_button);
    }

    public void SignUpClicked(View v)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void SignInClicked(View v)
    {
        Intent intent = new Intent(this, LoginActivitySignIn.class);
        startActivity(intent);
    }
}