package com.example.miku_project;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class RootData {
    public static String USER_FILE = "USER_FILE";


    //check primarykey nó sẽ lấy ra những thằng nào trùng và sẽ update thằng đó
    //Cho nên chúng ta sẽ dùng nó để check login
    public static void savePrefUserFile(Context context, String email, String password) {
        SharedPreferences pref = context.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("EMAIL", email);
        edit.putString("PASSWORD", password);
        //luu lai toan bo
        edit.commit();
    }

    public static void clearPrefUserFile(Context context) {
        SharedPreferences pref = context.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.clear();
    }

    public static boolean isUserFileExisted(Context context) {
        SharedPreferences pref = context.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        String email = pref.getString("EMAIL", "");
        String pass = pref.getString("PASSWORD", "");

        return !email.isEmpty() && !pass.isEmpty();
    }

}
