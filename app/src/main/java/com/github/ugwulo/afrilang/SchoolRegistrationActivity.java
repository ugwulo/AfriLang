package com.github.ugwulo.afrilang;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SchoolRegistrationActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_registration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate()");

        mFragmentManager = getSupportFragmentManager();
//        SchoolTypeFragment schoolTypeFragment = new SchoolTypeFragment();
//        FragmentTransaction transaction = mFragmentManager.beginTransaction();
//        transaction.add(R.id.nav_host_fragment, schoolTypeFragment, "SchoolTypeFrag");
//        transaction.commit();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");
    }
}