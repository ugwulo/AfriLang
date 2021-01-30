package com.github.ugwulo.afrilang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.github.ugwulo.afrilang.databinding.ActivityLoginBinding;
import com.github.ugwulo.afrilang.utils.Settings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Ndubuisi Ugwulo
/** {@link LoginActivity} class for handling all user login **/
public class LoginActivity extends AppCompatActivity  {

    private static final String TAG = "LoginActivity";
    //view binding
    ActivityLoginBinding mBinding;
    Bundle typeOfUserBundle;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        mAuth = FirebaseAuth.getInstance();
        typeOfUserBundle = getIntent().getExtras();
        setContentView(mBinding.getRoot());
        mBinding.userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(mBinding.userEmail.getText().toString(), mBinding.userPassword.getText().toString());
            }
        });

       mBinding.forgotPassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // reset password
           }
       });

        populateUserTypeSpinner();
        populateSchoolNameSpinner();


    }

    private void signInUser(String email, String password) {
        if (!validateLoginForm()){
            return;
        }
        showProgressDialog();

//        Authenticate user with Firebase
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Log.w(TAG, "signInWithEmail: failed" + task.getException());
                            Snackbar.make(getCurrentFocus().getRootView(), "Authentication Failed!", Snackbar.LENGTH_SHORT).show();
                            gotoUserHomePage(null);
                        }else {
                            Log.d(TAG, "signInWithEmail: successful");
                            FirebaseAuth user = FirebaseAuth.getInstance();

                            // set first time launch pref to true
                            Settings.setIsFirstTimeLaunch(true);
                            gotoUserHomePage(typeOfUserBundle);
                        }
                        hideProgressDialog();
                    }
                });
    }

    // type of user bundle determines where logged in user is redirected after login
    private void gotoUserHomePage(Bundle bundle) {
        if (bundle != null) {

            // test case
            if (typeOfUserBundle.get("TYPE_OF_USER").equals(getString(R.string.private_user))) {
                startActivity(new Intent(this, PrivateUserProfileActivity.class));
            }
        }
    }

    private boolean validateLoginForm() {
        boolean valid = true;

        String email =  mBinding.userEmail.getText().toString();
        String password = mBinding.userPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            mBinding.userEmail.setError("Required");
            valid = false;
        }else {
            mBinding.userEmail.setError(null);
        }

        if (TextUtils.isEmpty(password)){
            mBinding.userPassword.setError("Required");
            valid = false;
        }else {
            mBinding.userPassword.setError(null);
        }

        return valid;
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

    private void showProgressDialog(){
        mBinding.progressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressDialog(){
        if(mBinding.progressBar.getVisibility() == View.VISIBLE){
            mBinding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}