package com.example.pimpmymeal.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Header header = findViewById(R.id.header);
        header.initHeader();

        Footer footer = findViewById(R.id.footer);
        footer.initFooter();
    }
}
