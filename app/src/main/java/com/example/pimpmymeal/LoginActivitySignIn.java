package com.example.pimpmymeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pimpmymeal.ui.HomeActivity;

public class LoginActivitySignIn extends AppCompatActivity {

    EditText username, password;
    Button signup, signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_in);

        username = findViewById(R.id.UsernameEditText);
        password = findViewById(R.id.PasswordEditText);
        signup = findViewById(R.id.signup_button);
        signin = findViewById(R.id.signin_button);

    }

    public void LogInClicked(View v)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void SignUpBackClicked(View v)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}