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
import com.example.miku_project.models.Product;
import com.example.miku_project.screens.main_screens.PlaySong_Screen;

import java.util.ArrayList;

public class Adapter_SearchProduct extends RecyclerView.Adapter<Adapter_SearchProduct.MyViewHolder> {

    Context context;
    ArrayList<Product> data;

    public Adapter_SearchProduct(Context context, ArrayList<Product> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcv_search_product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = data.get(position);
        holder.tv_product_name.setText(product.getProduct_name());
        holder.tv_singer.setText(product.getProduct_singer());

        Glide.with(context).load(product.getProduct_image()).into(holder.iv_product_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaySong_Screen.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product_image;
        TextView tv_product_name, tv_singer;
        public MyViewHolder(View view) {
            super(view);
            iv_product_image = view.findViewById(R.id.iv_search_product_image_item);
            tv_product_name = view.findViewById(R.id.tv_search_product_name_item);
            tv_singer = view.findViewById(R.id.tv_search_product_singer_item);
        }
    }

}
