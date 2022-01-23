package com.example.pimpmymeal.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pimpmymeal.R;
import com.example.pimpmymeal.services.UserPreferencesService;

public class ProfileActivity extends AppCompatActivity {
    EditText updateUsernameEt;
    EditText updatePasswordEt;
    Spinner dietsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Header header = findViewById(R.id.header);
        header.initHeader();
        Footer footer = findViewById(R.id.footer);
        footer.initFooter();

        updateUsernameEt = findViewById(R.id.update_username_edit_text);
        updatePasswordEt = findViewById(R.id.update_password_edit_text);
        dietsSpinner = findViewById(R.id.diets_spinner);
        Button saveBtn = findViewById(R.id.save_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);

        resetValues();
        saveBtn.setOnClickListener(view -> updateValues());
        cancelBtn.setOnClickListener(view -> resetValues());
    }

    private void resetValues() {
        updateUsernameEt.setText(UserPreferencesService.username);
        updatePasswordEt.setText(UserPreferencesService.password);
        dietsSpinner.setSelection(UserPreferencesService.dietIndex);
    }

    private void updateValues() {
        String updatedUsername = updateUsernameEt.getText().toString();
        String updatedPassword = updatePasswordEt.getText().toString();
        int updatedDietIndex = dietsSpinner.getSelectedItemPosition();

        if (!updatedUsername.equals(UserPreferencesService.username))
            UserPreferencesService.updateUsername(this, updatedUsername);
        if (!updatedPassword.equals(UserPreferencesService.password))
            UserPreferencesService.updatePassword(this, updatedPassword);
        if (updatedDietIndex != UserPreferencesService.dietIndex)
            UserPreferencesService.updateDietIndex(this, updatedDietIndex);
        Toast.makeText(this, "Preferences saved", Toast.LENGTH_SHORT).show();
    }
}
