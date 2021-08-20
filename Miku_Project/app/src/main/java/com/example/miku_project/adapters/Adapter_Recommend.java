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
import com.example.miku_project.models.Recommend;
import com.example.miku_project.screens.ProductList_Screen;
import com.example.miku_project.screens.main_screens.PlaySong_Screen;

import java.util.ArrayList;

public class Adapter_Recommend extends RecyclerView.Adapter<Adapter_Recommend.MyViewHolder> {

    Context context;
    ArrayList<Recommend> data;

    public Adapter_Recommend(Context context, ArrayList<Recommend> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcv_recommend_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Recommend recommend = data.get(position);
        holder.tv_recommend_title.setText(recommend.getRecommends_name());
        holder.tv_recommend_artist.setText(recommend.getProduct_name());

        Glide.with(context).load(recommend.getRecommends_image()).into(holder.iv_recommend);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaySong_Screen.class);
                intent.putExtra("recommend", recommend);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_recommend;
        TextView tv_recommend_title, tv_recommend_artist;
        public MyViewHolder(View view) {
            super(view);
            iv_recommend = view.findViewById(R.id.iv_recommend);
            tv_recommend_title = view.findViewById(R.id.tv_recommend_title);
            tv_recommend_artist = view.findViewById(R.id.tv_recommend_artist);
        }
    }

}
