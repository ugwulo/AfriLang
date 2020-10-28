package com.github.ugwulo.afrilang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SchoolProfileActivity extends AppCompatActivity {

    private Button mPrintSchoolProfile;
    private TextView mSchoolName;
    private ImageView mSchoolLogo;
    private TextView mNum_of_school_enrolled_lang;
    private TextView mNum_of_school_students;
    private LinearLayout mLangListLayout;
    private LinearLayout mSchoolInfoLayout;
    private LinearLayout mSchoolSettingsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_profile);

        mPrintSchoolProfile = findViewById(R.id.button_print_school_profile);
        mSchoolName = findViewById(R.id.text_view_school_name_title);
        mSchoolLogo = findViewById(R.id.image_school_profile_logo);
        mNum_of_school_enrolled_lang = findViewById(R.id.text_view_school_num_of_enroll_languages);
        mNum_of_school_students = findViewById(R.id.text_view_number_of_students);

        mLangListLayout = findViewById(R.id.LinearLayout_lang_student_list);
        mSchoolInfoLayout = findViewById(R.id.LinearLayout_other_school_info);
        mSchoolSettingsLayout = findViewById(R.id.LinearLayout_school_settings);

    }
}