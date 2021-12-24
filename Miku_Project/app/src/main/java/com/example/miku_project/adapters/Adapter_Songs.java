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
import com.example.miku_project.models.Product;

import java.util.ArrayList;

public class Adapter_Songs extends RecyclerView.Adapter<Adapter_Songs.MyViewHolder> {

    Context context;
    ArrayList<Product> data;

    public Adapter_Songs(Context context, ArrayList<Product> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcv_recommend_item2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product playlist = data.get(position);
        holder.tv_name.setText(playlist.getProduct_name());
        holder.tv_singer.setText(playlist.getProduct_singer());

        Glide.with(context).load(playlist.getProduct_image()).into(holder.iv_song_play);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_song_play;
        TextView tv_name, tv_singer;

        public MyViewHolder(View view) {
            super(view);
            iv_song_play = view.findViewById(R.id.iv_recommend2);
            tv_name = view.findViewById(R.id.tv_recommend_title2);
            tv_singer = view.findViewById(R.id.tv_recommend_artist2);
        }
    }

}
