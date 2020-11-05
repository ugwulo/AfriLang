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

public class StudentHomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        Button studentTest = (Button) findViewById(R.id.button_student_comm_test);

        Button studentLearning = (Button) findViewById(R.id.button_student_comm_learn);

        studentLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent learningIntent = new Intent (StudentHomePageActivity.this, MainLearningPageActivity.class );
                startActivity(learningIntent);
            }
        });

        studentTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testIntent = new Intent(StudentHomePageActivity.this, StudentAssessmentActivity.class);
                startActivity(testIntent);
            }
        });


        populateLanguageSpinner();
    }



    private void populateLanguageSpinner() {
        Spinner spinnerLanguage = findViewById(R.id.spinner_wp_language1);

        List<String> languages = DataManager.getInstance().getLanguages();
        ArrayAdapter<String> adapterLanguages =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapterLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapterLanguages);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_report, menu);


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_view_profile){

            Intent schoolProfileIntent = new Intent(this, StudentProfileActivity.class);
            startActivity(schoolProfileIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}