package com.example.miku_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miku_project.R;
import com.example.miku_project.adapters.Adapter_Favorite;
import com.example.miku_project.adapters.Adapter_Product;
import com.example.miku_project.forms.Insert_Favorite;
import com.example.miku_project.models.Favorite;
import com.example.miku_project.models.Product;
import com.example.miku_project.models.ResponseModel;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.main_screens.PlaySong_Screen;
import com.example.miku_project.screens.main_screens.Playlist_Screen;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

    private Adapter_Favorite adapter_favorite;
    private Adapter_Product adapter_product;
    private RecyclerView rcv_favorite;
    private RelativeLayout rl_playlist;
    private ImageView iv_add_favorite;
    private ArrayList<Favorite> favorite_data;
    private ArrayList<Product> product_data;

    private IRetrofitService service;

//    private static String BASE_URL = "http://10.0.2.2:8081/";
    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv_favorite = view.findViewById(R.id.rcv_favorite);
        iv_add_favorite = view.findViewById(R.id.iv_add_favorite);
        rl_playlist = view.findViewById(R.id.rl_play_list_song);

        rl_playlist.setEnabled(false);

        iv_add_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Insert_Favorite.class));
            }
        });

        rcvProduct();

//        rcvRecommend();
    }

    private void rcvProduct(){
        rcv_favorite.setHasFixedSize(true);
        rcv_favorite.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        service.getAllProduct().enqueue(getAllProductsCB);
    }

//    private void rcvRecommend() {
//        rcv_favorite.setHasFixedSize(true);
//        rcv_favorite.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//
//        service.getAllFavorite().enqueue(getAllFavoriteCB);
//
//    }
//
//    Callback<ArrayList<Favorite>> getAllFavoriteCB = new Callback<ArrayList<Favorite>>() {
//        @Override
//        public void onResponse(Call<ArrayList<Favorite>> call, Response<ArrayList<Favorite>> response) {
//            if (response.isSuccessful()){
//                favorite_data = response.body();
//                adapter_favorite = new Adapter_Favorite(getContext(), favorite_data);
//                rcv_favorite.setAdapter(adapter_favorite);
//            }else {
//                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onFailure(Call<ArrayList<Favorite>> call, Throwable t) {
//
//        }
//    };

    Callback<ArrayList<Product>> getAllProductsCB = new Callback<ArrayList<Product>>() {
        @Override
        public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
            if (response.isSuccessful()){
                product_data = response.body();
                adapter_product = new Adapter_Product(getActivity(), product_data);
                rcv_favorite.setAdapter(adapter_product);
                eventClick();
            }else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        reloadscreen();
    }

    private void reloadscreen(){
        IRetrofitService service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
//        service.getAllFavorite().enqueue(getAllFavoriteCB);
        service.getAllProduct().enqueue(getAllProductsCB);
    }

    private void eventClick(){
        rl_playlist.setEnabled(true);
        rl_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlaySong_Screen.class);
                intent.putExtra("songs", product_data);
                startActivity(intent);
            }
        });
    }
}