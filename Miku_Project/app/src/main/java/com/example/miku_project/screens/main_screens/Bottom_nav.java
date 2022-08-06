package com.example.miku_project.screens.main_screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miku_project.R;
import com.example.miku_project.screens.fragment_screens.RadioFragment;
import com.example.miku_project.screens.fragment_screens.home.HomeFragment;
import com.example.miku_project.screens.fragment_screens.ExploreFragment;
import com.example.miku_project.screens.fragment_screens.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.makeramen.roundedimageview.RoundedImageView;

public class Bottom_nav extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private RoundedImageView rounded_image_play_music_avatar;
    private TextView tv_name_song_play_music;
    private ImageButton img_on_pause_play_music, img_back_song_play_music, img_next_song_play_music;
    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        initViewPlayMusic();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        android.graphics.drawable.Drawable background = Bottom_nav.this.getResources().getDrawable(R.color.bg_status_color);
//        getWindow().setBackgroundDrawable(background);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
        bottomNavigation.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_frame_layout, new HomeFragment()).commit();

        actionOnPauseMusic();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case 1:
                    selectedFragment = new HomeFragment();
                    break;
                case 2:
                    selectedFragment = new ExploreFragment();
                    break;
                case 3:
                    selectedFragment = new RadioFragment();
                    break;
                case 5:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.bottom_frame_layout, selectedFragment)
                    .setReorderingAllowed(true)
                    .commit();
            return true;
        }
    };

    private void actionOnPauseMusic() {
        img_on_pause_play_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(">>>" + img_on_pause_play_music.getResources());
            }
        });
    }

    private void initViewPlayMusic() {
        rounded_image_play_music_avatar = findViewById(R.id.rounded_image_play_music_avatar);
        tv_name_song_play_music = findViewById(R.id.tv_name_song_play_music);
        img_on_pause_play_music = findViewById(R.id.img_on_pause_play_music);
        img_back_song_play_music = findViewById(R.id.img_back_song_play_music);
        img_next_song_play_music = findViewById(R.id.img_next_song_play_music);
    }
}