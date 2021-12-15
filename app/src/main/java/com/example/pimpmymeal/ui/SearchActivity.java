package com.example.pimpmymeal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.buttonFunction.NavigationsButtons;

public class SearchActivity extends NavigationsButtons {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
