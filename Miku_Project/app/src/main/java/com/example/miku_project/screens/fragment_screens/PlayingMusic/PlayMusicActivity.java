package com.example.miku_project.screens.fragment_screens.PlayingMusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.miku_project.R;

public class PlayMusicActivity extends AppCompatActivity {

    private ViewPager2 viewPager_tabPlayingMusic;
    private ImageButton imgBtn_backLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        initView();

        // get action..
        backBtnAction();

    }

    private void backBtnAction() {
        imgBtn_backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        imgBtn_backLogin = findViewById(R.id.imgBtn_backLogin);
        viewPager_tabPlayingMusic = findViewById(R.id.viewPager_tabPlayingMusic);
    }
}