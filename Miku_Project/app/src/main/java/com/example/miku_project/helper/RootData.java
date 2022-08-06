package com.example.miku_project.helper;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.miku_project.models.User;

public class RootData {
    // Public package KEY
    public static String USER_KEY = "USER_CACHE_VALUE";
    // Public KEY
    public static String key_email = "EMAIL";
    public static String key_password = "PASSWORD";


    //check primarykey nó sẽ lấy ra những thằng nào trùng và sẽ update thằng đó
    //Cho nên chúng ta sẽ dùng nó để check login
    public static void savePrefUserFile(Context context, String email, String password) {
        SharedPreferences pref = context.getSharedPreferences(USER_KEY, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("EMAIL", email);
        edit.putString("PASSWORD", password);
        //luu lai toan bo
        edit.commit();
    }

    public static void clearPrefUserFile(Context context) {
        SharedPreferences pref = context.getSharedPreferences(USER_KEY, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.clear().commit();
    }

    public static boolean isUserFileExisted(Context context) {
        SharedPreferences pref = context.getSharedPreferences(USER_KEY, MODE_PRIVATE);
        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        String email = pref.getString("EMAIL", "");
        String pass = pref.getString("PASSWORD", "");

        return !email.isEmpty() && !pass.isEmpty();
    }

    public static User getPrefUserData(Context context) {
        SharedPreferences pref = context.getSharedPreferences(USER_KEY, MODE_PRIVATE);
        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        String email = pref.getString("EMAIL", "");
        String pass = pref.getString("PASSWORD", "");
        User user = new User();
        user.setEmail(email);
        user.setPassword(pass);
        return user;
    }



}
