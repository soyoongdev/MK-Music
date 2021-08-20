package com.example.miku_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miku_project.R;
import com.example.miku_project.adapters.Adapter_Play_Song;
import com.example.miku_project.screens.main_screens.PlaySong_Screen;

public class SongPlayListFragment extends Fragment {

    private View view;
    private RecyclerView rcv_song_play;
    private Adapter_Play_Song adapter_play_song;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_list, container, false);

        rcv_song_play = view.findViewById(R.id.rcv_song_play);
        if (PlaySong_Screen.productArrayList.size() > 0){
            adapter_play_song = new Adapter_Play_Song(getActivity(), PlaySong_Screen.productArrayList);
            rcv_song_play.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcv_song_play.setAdapter(adapter_play_song);
        }

        return view;
    }
}
