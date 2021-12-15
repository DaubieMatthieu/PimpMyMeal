package com.example.pimpmymeal.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.buttonFunction.NavigationsButtons;

public class HomeActivity extends NavigationsButtons {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
