package com.example.miku_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.miku_project.helper.RootData;
import com.example.miku_project.screens.main_screens.Bottom_nav;
import com.example.miku_project.screens.activity_screens.LogIn_screen;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Intent mainActivity;
        if (!RootData.getPrefUserData(MainActivity.this).getEmail().isEmpty()){
            mainActivity = new Intent(getApplicationContext(), Bottom_nav.class);
        } else {
            mainActivity = new Intent(getApplicationContext(), LogIn_screen.class);
        }
        startActivity(mainActivity);
        finish();
    }
}
