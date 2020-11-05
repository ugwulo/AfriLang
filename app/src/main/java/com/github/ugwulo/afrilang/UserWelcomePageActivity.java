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

import java.util.List;

public class UserWelcomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome_page);

        Button startLearning = (Button) findViewById(R.id.button_comm_learn);

        Button takeTest = (Button) findViewById(R.id.button_comm_learn);

        startLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserWelcomePageActivity.this, MainLearningPageActivity.class );
                startActivity(intent);
            }
        });

        takeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testIntent = new Intent(UserWelcomePageActivity.this, PrivateUserAssessmentActivity.class);
                startActivity(testIntent);
            }
        });






        populateLanguageSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item_view_profile){

            Intent viewProfileIntent = new Intent(this, PrivateUserProfileActivity.class);
            startActivity(viewProfileIntent);
        }



        return super.onOptionsItemSelected(item);
    }


    private void populateLanguageSpinner() {
        Spinner spinnerLanguage = findViewById(R.id.spinner_wp_language);

        List<String> languages = DataManager.getInstance().getLanguages();
        ArrayAdapter<String> adapterLanguages =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapterLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapterLanguages);


    }
}