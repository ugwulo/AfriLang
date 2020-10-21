package com.github.ugwulo.afrilang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;

import com.github.ugwulo.afrilang.databinding.ActivityStudentReportBinding;

/** @author Ndubuisi Ugwulo **/
/** {@link StudentReportActivity} displays student report overview **/
public class StudentReportActivity extends AppCompatActivity {

    // view binding
    ActivityStudentReportBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityStudentReportBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        populateLanguageNameSpinner();
        populateClassNameSpinner();
        populateReportTypeSpinner();
    }

    private void populateClassNameSpinner() {
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this, R.array.class_name, R.layout.spinner_text);
        classAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        mBinding.spinnerClass.setAdapter(classAdapter);
    }

    private void populateReportTypeSpinner() {
        ArrayAdapter<CharSequence> reportAdapter = ArrayAdapter.createFromResource(this, R.array.report_type, R.layout.spinner_text);
        reportAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        mBinding.spinnerReportType.setAdapter(reportAdapter);
    }

    private void populateLanguageNameSpinner() {
        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this, R.array.language_name, R.layout.spinner_text);
        languageAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        mBinding.spinnerLanguage.setAdapter(languageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_report, menu);
        return true;
    }
}