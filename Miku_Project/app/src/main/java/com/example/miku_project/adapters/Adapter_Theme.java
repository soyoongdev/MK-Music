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
import com.example.miku_project.models.ProductCategory;
import com.example.miku_project.models.Theme;

import java.util.ArrayList;

public class Adapter_Theme extends RecyclerView.Adapter<Adapter_Theme.MyViewHolder> {

    Context context;
    ArrayList<Theme> data;

    public Adapter_Theme(Context context, ArrayList<Theme> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcv_theme_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Theme theme = data.get(position);
        holder.tv_theme_name.setText(theme.getTheme_name());
        Glide.with(context).load(theme.getTheme_image()).into(holder.iv_theme_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_theme_image;
        TextView tv_theme_name;
        public MyViewHolder(View view) {
            super(view);
            iv_theme_image = view.findViewById(R.id.iv_theme_image_item);
            tv_theme_name = view.findViewById(R.id.tv_theme_name_item);
        }
    }

}
