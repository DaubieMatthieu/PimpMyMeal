package com.example.pimpmymeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pimpmymeal.ui.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    private final String usernameGeneric = "user";
    private final String passwordGeneric = "12345";

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
        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();

        if(inputUsername.isEmpty() || inputPassword.isEmpty())
        {
            Toast.makeText(this, "Don´t leave fields in blank", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(validateEntrance(inputUsername, inputPassword))
            {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void SignInClicked(View v)
    {
        Intent intent = new Intent(this, LoginActivitySignIn.class);
        startActivity(intent);
    }

    private boolean validateEntrance(String name, String pass)
    {
        if(name.equals(usernameGeneric) && pass.equals(passwordGeneric))
        {
            return true;
        }
        else
            return false;
    }
}