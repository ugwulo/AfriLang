package com.github.ugwulo.afrilang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.ugwulo.afrilang.data.DataManager;
import com.github.ugwulo.afrilang.data.StudentInfo;

import java.util.List;

public class PrivateUserRegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_user_reg);


        Button submitBtn = (Button) findViewById(R.id.button_priv_submit);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrivateUserRegActivity.this, UserWelcomePageActivity.class);
                startActivity(intent);
            }
        });

        populateCountrySpinner();
        populateLanguageSpinner();
    }

    private void populateLanguageSpinner() {
        Spinner spinnerLanguage = findViewById(R.id.spinner_language);

        List<String> languages = DataManager.getInstance().getLanguages();
        ArrayAdapter<String> adapterLanguages =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapterLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapterLanguages);


    }

    private void populateCountrySpinner() {
        Spinner spinnerCountry = findViewById(R.id.spinner_country);

        List<String> countries = DataManager.getInstance().getCountries();
        ArrayAdapter<String> adapterCountries =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapterCountries);
    }


}