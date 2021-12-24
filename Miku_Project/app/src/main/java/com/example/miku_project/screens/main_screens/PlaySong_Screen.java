package com.example.miku_project.screens.main_screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miku_project.R;
import com.example.miku_project.adapters.ViewPagerPlaylistSong;
import com.example.miku_project.fragments.SongDiscFragment;
import com.example.miku_project.fragments.SongPlayListFragment;
import com.example.miku_project.models.Product;
import com.example.miku_project.models.Recommend;
import com.example.miku_project.screens.MediaNotification;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlaySong_Screen extends AppCompatActivity {

    private TextView tv_product_name, tv_product_singer, tv_time_total, tv_start;
    private ImageView iv_back, iv_play, iv_foward, iv_repeat, iv_random;
    private SeekBar seekBar;
    private ViewPager viewPager;
    private MediaPlayer mediaPlayer;
    private NotificationManager notificationManager;

    public static ArrayList<Product> productArrayList = new ArrayList<>();
    public static ViewPagerPlaylistSong adapter_songlist;

    private SongDiscFragment songDiscFragment;
    private SongPlayListFragment songPlayListFragment;

    private int position = 0;
    private boolean repeat = false;
    private boolean checkrandom = false;
    private boolean next = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!productArrayList.isEmpty()){
            mediaPlayer.stop();
            productArrayList.clear();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (productArrayList.isEmpty()){
            Toast.makeText(PlaySong_Screen.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = PlaySong_Screen.this.getResources().getDrawable(R.color.bg_status_color);
        getWindow().setBackgroundDrawable(background);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        tv_product_name = findViewById(R.id.tv_product_name);
        tv_product_singer = findViewById(R.id.tv_product_singer);
        iv_back = findViewById(R.id.fast_back_product);
        iv_foward = findViewById(R.id.fast_foward_product);
        iv_play = findViewById(R.id.iv_play_product);
        iv_repeat = findViewById(R.id.repeat_product);
        iv_random = findViewById(R.id.play_random);
        viewPager = findViewById(R.id.viewpager_playsong);
        tv_time_total = findViewById(R.id.time_total);
        tv_start = findViewById(R.id.time_start);
        seekBar = findViewById(R.id.seekBar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }

        songDiscFragment = new SongDiscFragment();
        songPlayListFragment = new SongPlayListFragment();

        adapter_songlist = new ViewPagerPlaylistSong(getSupportFragmentManager());
        adapter_songlist.addFragment(songDiscFragment);
        adapter_songlist.addFragment(songPlayListFragment);

        viewPager.setAdapter(adapter_songlist);

        songPlayListFragment = (SongPlayListFragment) adapter_songlist.getItem(1);

        getDataFromIntent();
        eventClick();

        if (productArrayList.size() > 0) {
            tv_product_name.setText(productArrayList.get(0).getProduct_name());
            tv_product_singer.setText(productArrayList.get(0).getProduct_singer());
            new Playmp3().execute(productArrayList.get(0).getSong_url());
            iv_play.setImageResource(R.drawable.ic_pause);
        }

        iv_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom = true) {
                        checkrandom = false;
                        iv_repeat.setImageResource(R.drawable.ic_repeat);
                        iv_random.setImageResource(R.drawable.ic_mix2);
                    }
                    iv_repeat.setImageResource(R.drawable.ic_repeat);
                    repeat = true;
                } else {
                    iv_repeat.setImageResource(R.drawable.ic_repeat2);
                    repeat = false;
                }
            }
        });

        iv_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat = true) {
                        repeat = false;
                        iv_random.setImageResource(R.drawable.ic_mx);
                        iv_repeat.setImageResource(R.drawable.ic_repeat);
                    }
                    iv_repeat.setImageResource(R.drawable.ic_repeat);
                    checkrandom = true;
                }
                if (checkrandom == true) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    iv_random.setImageResource(R.drawable.ic_mx);
                    int index = (int) (Math.random() * productArrayList.size());
                    position = index;
                    new Playmp3().execute(productArrayList.get(position).getSong_url());
                    tv_product_name.setText(productArrayList.get(position).getProduct_name());
                    tv_product_singer.setText(productArrayList.get(position).getProduct_singer());
                    songDiscFragment.Playsong(productArrayList.get(position).getProduct_image());
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        iv_foward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (productArrayList.size())) {
                        iv_play.setImageResource(R.drawable.ic_pause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = productArrayList.size();
                            }
                            position = -1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(productArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (productArrayList.size() - 1)) {
                            position = 0;
                        }
                        new Playmp3().execute(productArrayList.get(position).getSong_url());
                        tv_product_name.setText(productArrayList.get(position).getProduct_name());
                        tv_product_singer.setText(productArrayList.get(position).getProduct_singer());
                        songDiscFragment.Playsong(productArrayList.get(position).getProduct_image());
                    }
                }
                iv_back.setClickable(false);
                iv_foward.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iv_back.setClickable(true);
                        iv_foward.setClickable(true);
                    }
                }, 3000);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (productArrayList.size())) {
                        iv_play.setImageResource(R.drawable.ic_pause);
                        position--;
                        if (position < 0) {
                            position = productArrayList.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(productArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new Playmp3().execute(productArrayList.get(position).getSong_url());
                        tv_product_name.setText(productArrayList.get(position).getProduct_name());
                        tv_product_singer.setText(productArrayList.get(position).getProduct_singer());
                        songDiscFragment.Playsong(productArrayList.get(position).getProduct_image());
                    }
                }
                iv_back.setClickable(false);
                iv_foward.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iv_back.setClickable(true);
                        iv_foward.setClickable(true);
                    }
                }, 3000);
            }
        });

    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(MediaNotification.CHANNEL_ID,
                    "Dev", NotificationManager.IMPORTANCE_LOW);

            notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapter_songlist.getItem(1) != null) {
                    if (productArrayList.size() > 0) {
                        songDiscFragment.Playsong(productArrayList.get(0).getProduct_image());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaNotification.createNotification(PlaySong_Screen.this, productArrayList.get(position),
                        R.drawable.ic_pause, position, productArrayList.size() -1);
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    iv_play.setImageResource(R.drawable.ic_play);
                    if (songDiscFragment.objectAnimator != null) {
                        songDiscFragment.objectAnimator.pause();
                    }
                } else {
                    mediaPlayer.start();
                    iv_play.setImageResource(R.drawable.ic_pause);
                    if (songDiscFragment.objectAnimator != null) {
                        songDiscFragment.objectAnimator.resume();
                    }
                }
            }
        });

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        productArrayList.clear();
        if (intent != null) {
            if (intent.hasExtra("product")) {
                Product product = intent.getParcelableExtra("product");
                productArrayList.add(product);
            }

            if (intent.hasExtra("songs")) {
                ArrayList<Product> product_data = intent.getParcelableArrayListExtra("songs");
                productArrayList = product_data;
            }
        }
    }

    class Playmp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            updateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tv_time_total.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void updateTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tv_start.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (productArrayList.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < (productArrayList.size())) {
                            iv_play.setImageResource(R.drawable.ic_pause);
                            position++;
                            if (repeat == true) {
                                if (position == 0) {
                                    position = productArrayList.size();
                                }
                                position = -1;
                            }
                            if (checkrandom == true) {
                                Random random = new Random();
                                int index = random.nextInt(productArrayList.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > (productArrayList.size() - 1)) {
                                position = 0;
                            }
                            new Playmp3().execute(productArrayList.get(position).getSong_url());
                            tv_product_name.setText(productArrayList.get(position).getProduct_name());
                            tv_product_singer.setText(productArrayList.get(position).getProduct_singer());
                            songDiscFragment.Playsong(productArrayList.get(position).getProduct_image());
                        }
                    }
                    iv_back.setClickable(false);
                    iv_foward.setClickable(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_back.setClickable(true);
                            iv_foward.setClickable(true);
                        }
                    }, 3000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

}