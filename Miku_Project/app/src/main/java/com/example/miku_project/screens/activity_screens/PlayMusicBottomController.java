package com.example.miku_project.screens.activity_screens;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miku_project.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class PlayMusicBottomController extends AppCompatActivity {
    private RoundedImageView rounded_image_play_music_avatar;
    private TextView tv_name_song_play_music;
    private ImageView img_on_pause_play_music, img_back_song_play_music, img_next_song_play_music;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_is_playing_view);
        initView();

        img_on_pause_play_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println( img_on_pause_play_music.getDrawable() + "");
            }
        });

    }

    private void initView() {
        rounded_image_play_music_avatar = findViewById(R.id.rounded_image_play_music_avatar);
        tv_name_song_play_music = findViewById(R.id.tv_name_song_play_music);
        img_on_pause_play_music = findViewById(R.id.img_on_pause_play_music);
        img_back_song_play_music = findViewById(R.id.img_back_song_play_music);
        img_next_song_play_music = findViewById(R.id.img_next_song_play_music);
    }
}
