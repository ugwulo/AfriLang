package com.github.ugwulo.afrilang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.github.ugwulo.afrilang.data.DataManager;
import com.github.ugwulo.afrilang.databinding.ActivityPrivateUserRegBinding;
import com.github.ugwulo.afrilang.models.PrivateUser;
import com.github.ugwulo.afrilang.utils.Settings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class PrivateUserRegActivity extends AppCompatActivity {

    ActivityPrivateUserRegBinding mUserRegBinding;
    private static final String TAG = "PrivateUserRegActivity";
    private static final List<String> mCountries = DataManager.getInstance().getCountries();
    private static final List<String> mLanguages = DataManager.getInstance().getLanguages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserRegBinding = ActivityPrivateUserRegBinding.inflate(getLayoutInflater());
        setContentView(mUserRegBinding.getRoot());

        mUserRegBinding.buttonPrivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check for null valued EditText fields
                if(!isEmpty(mUserRegBinding.editTextPrivEmail.getText().toString())
                        && !isEmpty(mUserRegBinding.editTextPrivPassword.getText().toString())
                        && !isEmpty(mUserRegBinding.editTextPrivConfirmPassword.getText().toString())){

                    //check if passwords match
                    if(doStringsMatch(mUserRegBinding.editTextPrivPassword.getText().toString(),
                            mUserRegBinding.editTextPrivConfirmPassword.getText().toString())){

                        //Initiate user registration task
                        registerNewEmail(mUserRegBinding.editTextPrivEmail.getText().toString(),
                                mUserRegBinding.editTextPrivPassword.getText().toString());
                    }else{
                        Snackbar.make(getCurrentFocus().getRootView(), "Passwords do not Match", Snackbar.LENGTH_SHORT).show();
                    }

                }else{
                    Snackbar.make(getCurrentFocus().getRootView(), "You must fill out all the fields", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        hideSoftKeyboard();

        populateCountrySpinner();
        populateLanguageSpinner();
    }

    private void populateLanguageSpinner() {
        ArrayAdapter<String> adapterLanguages =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mLanguages);
        adapterLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUserRegBinding.spinnerLanguage.setAdapter(adapterLanguages);
    }

    private void populateCountrySpinner() {
        ArrayAdapter<String> adapterCountries =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mCountries);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUserRegBinding.spinnerCountry.setAdapter(adapterCountries);
    }

    /**
     * Return true if the @param is null
     * @param string
     * @return
     */
    private boolean isEmpty(String string){
        return string.equals("");
    }


    private void showProgressDialog(){
        mUserRegBinding.progressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressDialog(){
        if(mUserRegBinding.progressBar.getVisibility() == View.VISIBLE){
            mUserRegBinding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * Redirects the user to the login screen
     * @param bundle
     */
    private void redirectLoginScreen(Bundle bundle){
       Intent loginIntent = new Intent(PrivateUserRegActivity.this, LoginActivity.class);
       loginIntent.putExtras(bundle);
       startActivity(loginIntent);
        finish();
    }
    /**
     * Return true if @param 's1' matches @param 's2'
     * @param s1
     * @param s2
     * @return
     */
    private boolean doStringsMatch(String s1, String s2){
        return s1.equals(s2);
    }

    /**
     * Register a new email and password to Firebase Authentication
     * @param email
     * @param password
     */
    public void registerNewEmail(String email, String password){

        showProgressDialog();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: AuthState: " + FirebaseAuth.getInstance().getCurrentUser().getUid());

                            // TODO("send verification email")
                            // sendVerificationEmail();

                            //insert some default user data
                            String selectedLanguage = mUserRegBinding.spinnerLanguage.getSelectedItem().toString();
                            String selectedCountry = mUserRegBinding.spinnerCountry.getSelectedItem().toString();
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            PrivateUser user = new PrivateUser();
                            user.setFirstName(mUserRegBinding.editTextFirstName.getText().toString());
                            user.setLastName(mUserRegBinding.editTextLastName.getText().toString());
                            user.setTelephone(mUserRegBinding.editTextPrivTelephone.getText().toString());
                            user.setCountry(selectedCountry);
                            user.setLanguage(selectedLanguage);
                            user.setUser_id(userId);


                            // get the document reference for private users
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            DocumentReference privateUserRef = db
                                    .collection(getString(R.string.private_user))
                                    .document(userId);

                            privateUserRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    hideProgressDialog();
                                    Snackbar.make(getCurrentFocus().getRootView(), "Registration Successful :)", Snackbar.LENGTH_SHORT).show();

                                    // sign current user out
                                    FirebaseAuth.getInstance().signOut();

                                    // send user to login screen on successful registration
                                    Bundle bundle = new Bundle();
                                    bundle.putString("TYPE_OF_USER", getString(R.string.private_user));
                                    redirectLoginScreen(bundle);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                   hideProgressDialog();
                                    Log.e( "Db Creation Failed", "Failed", e );
                                    Snackbar.make(getCurrentFocus().getRootView(), e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                }
                            });

                        }
                        if (!task.isSuccessful()) {
                            FirebaseAuthException e =  (FirebaseAuthException) task.getException();
                            Snackbar.make(getCurrentFocus().getRootView(), e.getMessage(), Snackbar.LENGTH_LONG).show();
                            Log.e( "User Registration: " , "Failed", e );
                        }
                        hideProgressDialog();
                    }
                });
    }



}