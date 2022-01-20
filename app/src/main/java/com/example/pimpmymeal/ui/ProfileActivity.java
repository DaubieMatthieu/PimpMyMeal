package com.example.pimpmymeal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pimpmymeal.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Header header = findViewById(R.id.header);
        header.initHeader();

        Footer footer = findViewById(R.id.footer);
        footer.initFooter();
    }
}
