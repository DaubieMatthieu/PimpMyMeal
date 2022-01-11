package com.example.pimpmymeal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.buttonFunction.Footer;
import com.example.pimpmymeal.buttonFunction.Header;
import com.example.pimpmymeal.buttonFunction.NavigationsButtons;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Header header = (Header) findViewById(R.id.header);
        header.initHeader();

        Footer footer = (Footer) findViewById(R.id.footer);
        footer.initFooter();
    }
}
