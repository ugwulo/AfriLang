package com.github.ugwulo.afrilang.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

/**
 * Preferences for Users
 */
public class Settings {
    //Keys for Shared preferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "afrilang";

    //Check if the User is logged in or not
    public static final String LOGGED_IN_SHARED_PREF = "loggedin";


    //Check if it's user's first time launching the app.
    private static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";

    private static SharedPreferences settings;

    private static SharedPreferences defaultPrefs;

    public static void init(@NonNull Context context){
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isLoggedIn(){
        return settings.getBoolean(LOGGED_IN_SHARED_PREF, false);
    }

    public static void setLoggedInSharedPref(boolean loggedInSharedPref){
        settings.edit()
                .putBoolean(LOGGED_IN_SHARED_PREF, loggedInSharedPref)
                .apply();
    }


    /**
     * method to set app launch preferences
     * @param isFirstTimeLaunch
     */
    public static void setIsFirstTimeLaunch(boolean isFirstTimeLaunch){
        settings.edit()
                .putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTimeLaunch)
                .apply();
    }

    /**
     * checks if user current user has launched app and successfully logged in
     * @return
     */
    public static boolean isFirstTimeLaunch(){
        return settings.getBoolean(IS_FIRST_TIME_LAUNCH, false);
    }

}
