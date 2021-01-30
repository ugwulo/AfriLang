package com.github.ugwulo.afrilang.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {
    public static FirebaseAuth mAuth;

    public static FirebaseAuth.AuthStateListener mAuthStateListener;
    private static  FirebaseUtil mInstance;

    private FirebaseUtil(){}

    public static synchronized FirebaseUtil getInstance(String ref){
        if (mInstance == null){
            mInstance = new FirebaseUtil();

        }
        return mInstance;
    }
}
