package com.example.miku_project.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.models.Album;
import com.example.miku_project.models.Playlist;

import java.util.ArrayList;

public class Adapter_Album extends RecyclerView.Adapter<Adapter_Album.MyViewHolder> {

    Context context;
    ArrayList<Album> data;

    public Adapter_Album(Context context, ArrayList<Album> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcv_album_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Album playlist = data.get(position);
        holder.tv_album_singer.setText(playlist.getAlbum_singer());

        Glide.with(context).load(playlist.getAlbum_image()).into(holder.iv_album_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_album_img;
        TextView tv_album_singer;
        public MyViewHolder(View view) {
            super(view);
            iv_album_img = view.findViewById(R.id.iv_album_image_item);
            tv_album_singer = view.findViewById(R.id.tv_album_singer_item);
        }
    }

}
