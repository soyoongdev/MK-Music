package com.example.miku_project.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.models.MusicModel;
import com.example.miku_project.models.Playlist;
import com.example.miku_project.screens.main_screens.Playlist_Screen;

import java.util.ArrayList;

public class Adapter_TopFiveMusic extends RecyclerView.Adapter<Adapter_TopFiveMusic.MyViewHolder> {

    Context context;
    ArrayList<MusicModel> data;

    public Adapter_TopFiveMusic(Context context, ArrayList<MusicModel> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top_music, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MusicModel item = data.get(position);
        holder.tv_playlist_name.setText(item.getNameSong());
        //Glide.with(context).load(item.getImageUrl()).into(holder.iv_playlist_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_playlist_img;
        TextView tv_playlist_name;
        public MyViewHolder(View view) {
            super(view);
            iv_playlist_img = view.findViewById(R.id.iv_playlist_image_item);
            tv_playlist_name = view.findViewById(R.id.tv_playlist_name_item);
        }
    }

}
