package com.example.miku_project.screens.fragment_screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miku_project.R;
import com.example.miku_project.adapters.Adapter_TopFiveMusic;
import com.example.miku_project.models.Playlist;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistFragment extends Fragment {

    private RecyclerView rcv_playlist;
    private Adapter_TopFiveMusic adapter_topFiveMusic;
    private ArrayList<Playlist> playlist_data;

    private IRetrofitService service;

    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        return inflater.inflate(R.layout.fragment_playlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    Callback<ArrayList<Playlist>> getAllPlaylistCB = new Callback<ArrayList<Playlist>>() {
        @Override
        public void onResponse(Call<ArrayList<Playlist>> call, Response<ArrayList<Playlist>> response) {
            if (response.isSuccessful()) {
                playlist_data = response.body();
                //adapter_topFiveMusic = new Adapter_TopFiveMusic(getActivity(), playlist_data);
                rcv_playlist.setAdapter(adapter_topFiveMusic);
            } else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Playlist>> call, Throwable t) {

        }
    };
}