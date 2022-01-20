package com.example.pimpmymeal.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.services.ApiRepository;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Header header = findViewById(R.id.header);
        header.initHeader();
        Footer footer = findViewById(R.id.footer);
        footer.initFooter();

        ApiRepository apiRepository = new ApiRepository(this);
        Button btn = findViewById(R.id.launch_search);
        EditText editText = findViewById(R.id.search_edit_frame);
        btn.setOnClickListener(e -> apiRepository.search(recipes -> {
            RecipesAdapter adapter = new RecipesAdapter(this, recipes);
            GridView gridView = findViewById(R.id.recipes_list);
            gridView.setAdapter(adapter);
        }, String.valueOf(editText.getText())));
    }
}
