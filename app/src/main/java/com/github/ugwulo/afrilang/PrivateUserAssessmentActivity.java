package com.github.ugwulo.afrilang;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.github.ugwulo.afrilang.databinding.ActivityPrivateUserAssessmentBinding;

/** @author Ndubuisi Ugwulo **/
/** {@link PrivateUserAssessmentActivity} enables private users to take tests **/
public class PrivateUserAssessmentActivity extends AppCompatActivity {

    ActivityPrivateUserAssessmentBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityPrivateUserAssessmentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        populateTopicSelectionSpinner();
    }

    private void populateTopicSelectionSpinner() {
        ArrayAdapter<CharSequence> topicAdapter = ArrayAdapter.createFromResource(this, R.array.topic, R.layout.spinner_text);
        topicAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
    }
}