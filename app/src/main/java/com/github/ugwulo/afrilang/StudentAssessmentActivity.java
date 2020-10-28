package com.github.ugwulo.afrilang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class StudentAssessmentActivity extends AppCompatActivity {

    private Spinner mSpinnerTopic;
    private TextView mTestLanguageView;
    private LinearLayout mTestAreaLayout;
    private Button mNextButton;
    private Button mPrevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_assessment);

        mTestLanguageView = findViewById(R.id.text_view_lang_to_test);
        mSpinnerTopic = findViewById(R.id.spinner_select_test_topic);
        mTestAreaLayout = findViewById(R.id.LinearLayout_test_area);

        mNextButton = findViewById(R.id.button_test_next_question);
        mPrevButton = findViewById(R.id.button_test_previous_question);

        populateTopicSpinner();

    }
    private void populateTopicSpinner() {
        ArrayAdapter<CharSequence> languageTopicAdapter = ArrayAdapter.createFromResource(this, R.array.language_topic, R.layout.spinner_text);
        languageTopicAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        mSpinnerTopic.setAdapter(languageTopicAdapter);
    }
}