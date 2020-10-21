package com.github.ugwulo.afrilang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.github.ugwulo.afrilang.databinding.ActivityLoginBinding;

/** @author Ndubuisi Ugwulo **/
/** {@link LoginActivity} class for handling all user login **/
public class LoginActivity extends AppCompatActivity {

    //view binding
    ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        populateUserTypeSpinner();
        populateSchoolNameSpinner();
    }

    /** displays a list of schools for selection **/
    private void populateSchoolNameSpinner() {
        ArrayAdapter<CharSequence> schoolAdapter = ArrayAdapter.createFromResource(this, R.array.school_name, R.layout.spinner_text);
        schoolAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        mBinding.spinnerSchoolName.setAdapter(schoolAdapter);
    }

    /** displays user type for selection **/
    private void populateUserTypeSpinner() {
        ArrayAdapter<CharSequence> userAdapter = ArrayAdapter.createFromResource(this, R.array.type_of_user, R.layout.spinner_text);
        userAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        mBinding.spinnerUserType.setAdapter(userAdapter);
    }
}