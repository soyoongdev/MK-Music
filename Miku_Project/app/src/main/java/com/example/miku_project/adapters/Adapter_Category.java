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
import com.example.miku_project.models.Category;
import com.example.miku_project.models.ProductCategory;

import java.util.ArrayList;

public class Adapter_Category extends RecyclerView.Adapter<Adapter_Category.MyViewHolder> {

    Context context;
    ArrayList<Category> data;

    public Adapter_Category(Context context, ArrayList<Category> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcv_cate_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = data.get(position);
        holder.tv_category_name.setText(category.getCategory_name());
        Glide.with(context).load(category.getCategory_image()).into(holder.iv_category_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_category_img;
        TextView tv_category_name;
        public MyViewHolder(View view) {
            super(view);
            iv_category_img = view.findViewById(R.id.iv_category_image_item);
            tv_category_name = view.findViewById(R.id.tv_category_name_item);
        }
    }

}
