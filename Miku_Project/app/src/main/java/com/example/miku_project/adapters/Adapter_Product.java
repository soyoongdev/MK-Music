package com.example.miku_project.adapters;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.forms.Insert_Favorite;
import com.example.miku_project.fragments.FavoriteFragment;
import com.example.miku_project.models.Product;
import com.example.miku_project.models.Recommend;
import com.example.miku_project.models.ResponseModel;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.MediaNotification;
import com.example.miku_project.screens.main_screens.PlaySong_Screen;
import com.example.miku_project.screens.main_screens.Playlist_Screen;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Product extends RecyclerView.Adapter<Adapter_Product.MyViewHolder> {

    Context context;
    ArrayList<Product> data;

    private IRetrofitService service;
    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    public Adapter_Product(Context context, ArrayList<Product> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcv_product_item, parent, false);
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

        holder.ic_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, holder.ic_more);

                popup.inflate(R.menu.popup_menu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.remove:
                                service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
                                service.delete(product).enqueue(deleteCB);
                                notifyItemRemoved(position);
                                FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                                if (Build.VERSION.SDK_INT >= 26) {
                                    ft.setReorderingAllowed(false);
                                }
                                ft.replace(R.id.bottom_frame_layout, new FavoriteFragment(), "Favorite").commit();
                                return true;
                            case R.id.edit:
                                Intent intent = new Intent(context, Insert_Favorite.class);
                                intent.putExtra("id", product.getProduct_id());
                                ((Activity)context).startActivity(intent);
                                FragmentTransaction ftt = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                                if (Build.VERSION.SDK_INT >= 26) {
                                    ftt.setReorderingAllowed(false);
                                }
                                ftt.replace(R.id.bottom_frame_layout, new FavoriteFragment(), "Favorite").commit();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product_image, ic_more;
        TextView tv_product_name, tv_singer;
        public MyViewHolder(View view) {
            super(view);
            iv_product_image = view.findViewById(R.id.iv_product_image_item);
            tv_product_name = view.findViewById(R.id.tv_product_name_item);
            tv_singer = view.findViewById(R.id.tv_product_singer_item);
            ic_more = view.findViewById(R.id.ic_more_product_item);
        }
    }

    Callback<ResponseModel> deleteCB = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
            if (response.isSuccessful()) {
                ResponseModel model = response.body();
                if (model.getStatus()) {

                } else {
                    Toast.makeText(context, "Delete failed",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Log.e("insertCB onResponse>>>>", response.message());
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {
            Log.e("insertCB onFailure>>>>", t.getMessage());
        }
    };

}
