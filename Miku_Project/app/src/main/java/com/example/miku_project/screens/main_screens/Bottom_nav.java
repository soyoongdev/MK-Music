package com.example.miku_project.screens.main_screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miku_project.R;
import com.example.miku_project.screens.fragment_screens.RadioFragment;
import com.example.miku_project.screens.fragment_screens.home.HomeFragment;
import com.example.miku_project.screens.fragment_screens.ExploreFragment;
import com.example.miku_project.screens.fragment_screens.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;

public class Bottom_nav extends AppCompatActivity {
    private RoundedImageView rounded_image_play_music_avatar;
    private TextView tv_name_song_play_music;
    private ImageButton img_on_pause_play_music, img_back_song_play_music, img_next_song_play_music;
    private MediaPlayer mediaPlayer;
    private boolean initialStage = true;
    private final String LINK_MP3 = "https://www.ssaurel.com/tmp/mymusic.mp3";
    private boolean playPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        initViewPlayMusic();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//          getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        android.graphics.drawable.Drawable background = Bottom_nav.this.getResources().getDrawable(R.color.bg_status_color);
//        getWindow().setBackgroundDrawable(background);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_frame_layout, new HomeFragment()).commit();
        // Default is Home screen
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        actionOnPauseMusic();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.icon_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.icon_explore:
                    selectedFragment = new ExploreFragment();
                    break;
                case R.id.icon_radio:
                    selectedFragment = new RadioFragment();
                    break;
                case R.id.icon_user:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            assert selectedFragment != null;
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
                playAudio();
            }
        });
    }

    private void pauseAudio() {
        // checking the media player
        // if the audio is playing or not.
        if (mediaPlayer.isPlaying()) {
            // pausing the media player if media player
            // is playing we are calling below line to
            // stop our media player.
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();

            // below line is to display a message
            // when media player is paused.
            Toast.makeText(Bottom_nav.this, "Audio has been paused", Toast.LENGTH_SHORT).show();
        } else {
            // this method is called when media
            // player is not playing.
            Toast.makeText(Bottom_nav.this, "Audio has not played", Toast.LENGTH_SHORT).show();
        }
    }

    private void playAudio() {
        // below line is use to set our
        // url to our media player.
        if (!playPause) {
            setToastMessage("Pause Streaming");
            if (initialStage) {
                new Player().execute(LINK_MP3);
            } else {
                if (!mediaPlayer.isPlaying()) mediaPlayer.start();
            }

            playPause = true;
        } else {
            setToastMessage("Launch Streaming");
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }

            playPause = false;
        }
        // below line is use to display a toast message.
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    class Player extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean prepared = false;

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        initialStage = true;
                        playPause = false;
                        setToastMessage("Launch Streaming");
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.prepare();
                prepared = true;
            } catch (Exception e) {
                Log.e("MyAudioStreamingApp", e.getMessage());
                prepared = false;
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            mediaPlayer.start();
            initialStage = false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setToastMessage("Buffering..");
        }
    }

    private void initViewPlayMusic() {
        rounded_image_play_music_avatar = findViewById(R.id.rounded_image_play_music_avatar);
        tv_name_song_play_music = findViewById(R.id.tv_name_song_play_music);
        img_on_pause_play_music = findViewById(R.id.img_on_pause_play_music);
        img_back_song_play_music = findViewById(R.id.img_back_song_play_music);
        img_next_song_play_music = findViewById(R.id.img_next_song_play_music);
    }

    private void setToastMessage(String message) {
        Toast.makeText(Bottom_nav.this, message, Toast.LENGTH_SHORT).show();
    }
}