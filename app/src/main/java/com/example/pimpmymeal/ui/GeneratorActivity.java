package com.example.pimpmymeal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.buttonFunction.Footer;
import com.example.pimpmymeal.buttonFunction.NavigationsButtons;
import com.example.pimpmymeal.buttonFunction.Header;

public class GeneratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        Header header = (Header) findViewById(R.id.header);
        header.initHeader();

        Footer footer = (Footer) findViewById(R.id.footer);
        footer.initFooter();
    }
}
