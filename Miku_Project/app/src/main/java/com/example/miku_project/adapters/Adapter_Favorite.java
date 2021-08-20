package com.example.miku_project.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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
import com.example.miku_project.fragments.HomeFragment;
import com.example.miku_project.models.Favorite;
import com.example.miku_project.models.ResponseModel;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Favorite extends RecyclerView.Adapter<Adapter_Favorite.MyViewHolder> {

    Context context;
    ArrayList<Favorite> data;
    private IRetrofitService service;

    private static String BASE_URL = "http://10.0.2.2:8081/";

    public Adapter_Favorite(Context context, ArrayList<Favorite> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcv_favorite_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Favorite favorite = data.get(position);

        holder.tv_favorite_name.setText(favorite.getFavorite_name());
        holder.tv_favorite_singer.setText(favorite.getFavorite_singer());
        holder.tv_favorite_time.setText(favorite.getTime_song() + "");

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
                                service.deleteFavorite(favorite).enqueue(deleteCB);
                                notifyItemRemoved(position);
                                FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                                if (Build.VERSION.SDK_INT >= 26) {
                                    ft.setReorderingAllowed(false);
                                }
                                ft.replace(R.id.bottom_frame_layout, new FavoriteFragment(), "Favorite").commit();
                                return true;
                            case R.id.edit:
                                Intent intent = new Intent(context, Insert_Favorite.class);
                                intent.putExtra("id", favorite.getId());
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

        Glide.with(context).load(favorite.getFavorite_image()).into(holder.iv_favorite);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_favorite, ic_more;
        TextView tv_favorite_name, tv_favorite_singer, tv_favorite_time;
        public MyViewHolder(View view) {
            super(view);

            iv_favorite = view.findViewById(R.id.iv_favorite_img);
            tv_favorite_name = view.findViewById(R.id.tv_favorite_name);
            tv_favorite_singer = view.findViewById(R.id.tv_favorite_artist);
            tv_favorite_time = view.findViewById(R.id.tv_time_favorite);
            ic_more = view.findViewById(R.id.ic_more_item);

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
