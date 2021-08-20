package com.example.miku_project.screens.main_screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.adapters.Adapter_Album;
import com.example.miku_project.adapters.Adapter_Product;
import com.example.miku_project.models.Album;
import com.example.miku_project.models.Playlist;
import com.example.miku_project.models.Product;
import com.example.miku_project.models.Recommend;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.ProductList_Screen;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Playlist_Screen extends AppCompatActivity {

    private ImageView iv_img_bg;
    private TextView tv_playlist_name;
    private RelativeLayout rl_play;
    private Playlist playlist_data;
    private ArrayList<Product> product_data;
    private ArrayList<Album> album_data;
    private Adapter_Product adapter_product;
    private Adapter_Album adapter_album;
    private RecyclerView rcv_songs, rcv_album;

    private IRetrofitService service;

    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = Playlist_Screen.this.getResources().getDrawable(R.color.bg_status_color);
        getWindow().setBackgroundDrawable(background);

        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);

        iv_img_bg = findViewById(R.id.iv_image_background_playlist);
        tv_playlist_name = findViewById(R.id.tv_playlist_name);
        rcv_songs = findViewById(R.id.rcv_playlistsong);
        rcv_album = findViewById(R.id.rcv_albums);
        rl_play = findViewById(R.id.rl_play_list);

        rl_play.setEnabled(false);

        DataIntent();
        service.getAllAlbum().enqueue(getAlbums);
        if (playlist_data != null && !playlist_data.getPlaylist_name().equals("")) {
            setValueView(playlist_data.getPlaylist_name(), playlist_data.getPlaylist_image());
            getDataPlaylist(playlist_data.getPlaylist_id() + "");
        }

    }

    private void getDataPlaylist(String playlist_id){
        service.getPlaylistsong(playlist_id).enqueue(getProductList);
    }

    private void DataIntent(){
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("playlist")){
                playlist_data = (Playlist) intent.getSerializableExtra("playlist");
            }
        }
    }

    private void setValueView(String name, String image_url) {
        tv_playlist_name.setText(name);
        Glide.with(Playlist_Screen.this)
                .load(image_url)
                .into(iv_img_bg);
    }

    Callback<ArrayList<Product>> getProductList = new Callback<ArrayList<Product>>() {
        @Override
        public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
            if (response.isSuccessful()) {
                product_data = response.body();
                adapter_product = new Adapter_Product(Playlist_Screen.this, product_data);
                rcv_songs.setLayoutManager(new LinearLayoutManager(Playlist_Screen.this));
                rcv_songs.setAdapter(adapter_product);
                eventClick();
            } else {
                Log.e(">>>>>>>>>>", response.message());
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
            Log.e("getByIdCB onFailure>>>>", t.getMessage());
        }
    };

    Callback<ArrayList<Album>> getAlbums = new Callback<ArrayList<Album>>() {
        @Override
        public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
            if (response.isSuccessful()) {
                album_data = response.body();
                adapter_album = new Adapter_Album(Playlist_Screen.this, album_data);
                rcv_album.setLayoutManager(new LinearLayoutManager(Playlist_Screen.this, LinearLayoutManager.HORIZONTAL, false));
                rcv_album.setAdapter(adapter_album);
            } else {
                Log.e(">>>>>>>>>>", response.message());
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
            Log.e("getByIdCB onFailure>>>>", t.getMessage());
        }
    };

    private void eventClick(){
        rl_play.setEnabled(true);
        rl_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Playlist_Screen.this, PlaySong_Screen.class);
                intent.putExtra("songs", product_data);
                startActivity(intent);
            }
        });
    }
}