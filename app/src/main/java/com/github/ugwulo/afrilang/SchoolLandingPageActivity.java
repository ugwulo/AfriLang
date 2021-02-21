package com.github.ugwulo.afrilang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class SchoolLandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_landing_page);


        Button addStudent = (Button) findViewById(R.id.button_add_student);

        Button viewStudentProgress = (Button) findViewById(R.id.button_view_student_progress);

        Button studentTest = (Button) findViewById(R.id.button_student_test);

        Button printStudentResult = (Button) findViewById(R.id.button_print_student_report);


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addStudentIntent = new Intent(SchoolLandingPageActivity.this, AddStudentActivity.class);
                startActivity(addStudentIntent);

            }
        });

        viewStudentProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewProgressIntent = new Intent(SchoolLandingPageActivity.this, StudentProfileActivity.class);
                startActivity(viewProgressIntent);
            }
        });

        studentTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentTestIntent = new Intent(SchoolLandingPageActivity.this, StudentAssessmentActivity.class);
                startActivity(studentTestIntent);
            }
        });

        printStudentResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentResultIntent = new Intent(SchoolLandingPageActivity.this, StudentReportActivity.class);
                startActivity(studentResultIntent);
            }
        });
    }
}