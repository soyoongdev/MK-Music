package com.example.miku_project.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.forms.Insert_Favorite;
import com.example.miku_project.models.Favorite;
import com.example.miku_project.models.Product;
import com.example.miku_project.models.Recommend;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.main_screens.Bottom_nav;
import com.example.miku_project.screens.main_screens.PlaySong_Screen;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductList_Screen extends AppCompatActivity {
    private TextView tv_product_name, tv_singer;
    private ImageView iv_product_image;
    private Recommend recommend_data;
    private ArrayList<Product> product_data;

    private IRetrofitService service;

    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = ProductList_Screen.this.getResources().getDrawable(R.color.bg_status_color);
        getWindow().setBackgroundDrawable(background);

        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);

        tv_product_name = findViewById(R.id.tv_productlist_name);
        tv_singer = findViewById(R.id.tv_product_singer);
        iv_product_image = findViewById(R.id.iv_productlist_image);

        DataIntent();

        if (recommend_data != null && !recommend_data.getProduct_name().equals("")) {
            setValueView(recommend_data.getProduct_name(), recommend_data.getProduct_image());
            getDataRecommend(recommend_data.getId() + "");
        }
    }

    private void setValueView(String name, String image_url) {
        tv_product_name.setText(name);
        Glide.with(ProductList_Screen.this)
                .load(image_url)
                .into(iv_product_image);
    }

    private void getDataRecommend(String recommend_id){
        service.getProductList(recommend_id).enqueue(getProductList);
    }

    private void DataIntent(){
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("recommend")){
                recommend_data = (Recommend) intent.getSerializableExtra("recommend");
            }
        }
    }

    Callback<ArrayList<Product>> getProductList = new Callback<ArrayList<Product>>() {
        @Override
        public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
            if (response.isSuccessful()) {
                product_data = response.body();
            } else {
                Log.e("", response.message());
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
            Log.e("getByIdCB onFailure>>>>", t.getMessage());
        }
    };


}