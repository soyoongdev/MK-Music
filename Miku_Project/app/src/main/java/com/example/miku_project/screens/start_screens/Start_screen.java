package com.example.miku_project.screens.start_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.miku_project.R;

public class Start_screen extends AppCompatActivity {

    private RelativeLayout rl_login, rl_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rl_login = (RelativeLayout) findViewById(R.id.rl_login);
        rl_signup = (RelativeLayout) findViewById(R.id.rl_signup);

        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start_screen.this, LogIn_screen.class));
                finish();
            }
        });

        rl_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start_screen.this, SignUp_screen.class));
            }
        });
    }
}