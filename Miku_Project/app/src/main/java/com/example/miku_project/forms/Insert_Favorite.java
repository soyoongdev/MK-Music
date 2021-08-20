package com.example.miku_project.forms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.adapters.Adapter_Category;
import com.example.miku_project.adapters.Adapter_Category_SPN;
import com.example.miku_project.models.Category;
import com.example.miku_project.models.Favorite;
import com.example.miku_project.models.FavoriteCategory;
import com.example.miku_project.models.Product;
import com.example.miku_project.models.Response2pikModel;
import com.example.miku_project.models.ResponseModel;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Insert_Favorite extends AppCompatActivity {

    private EditText edt_favorite_name, edt_favorite_singer, edt_favorite_song, edt_favorite_time;
    private ImageView img_favorite;
    private RelativeLayout btn_upload, btn_insert;
    private Spinner spinner;

    private Adapter_Category_SPN adapter;
    private ArrayList<Category> data;
    private Product product_data;
    private Favorite favorite_data = null;

    private IRetrofitService service;

    private String image_url = null;
    private Integer category_id = -1;
    private Integer favoriteId = -1;

//    private static String BASE_URL = "http://10.0.2.2:8081/";
    private static String BASE_2PIK_URL = "https://2.pik.vn/";
    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_favorite);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = Insert_Favorite.this.getResources().getDrawable(R.color.bg_status_color);
        getWindow().setBackgroundDrawable(background);


        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        service.getAllCategories().enqueue(getAllCategoriesCB);

        favoriteId = getIntent().getIntExtra("id", -1);

        edt_favorite_name = findViewById(R.id.edt_insert_favorite_name);
        edt_favorite_singer = findViewById(R.id.edt_insert_favorite_singer);
        edt_favorite_song = findViewById(R.id.edt_insert_favorite_song);
        edt_favorite_time = findViewById(R.id.edt_insert_favorite_time);
        spinner = findViewById(R.id.spn_insert_favorite_category);
        btn_upload = findViewById(R.id.rl_upload_favorite_image);
        img_favorite = findViewById(R.id.favorite_image_uploaded);
        btn_insert = findViewById(R.id.rl_insert_favorite);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
                Product p = new Product();
                p.setProduct_name(edt_favorite_name.getText().toString());
                p.setProduct_image(image_url);
                p.setProduct_singer(edt_favorite_singer.getText().toString());
                p.setSong_url(edt_favorite_song.getText().toString());
                p.setProduct_id(favoriteId);
                if (favoriteId == -1) {
                    service.insert(p).enqueue(insert_update_CB);
                } else {
                    service.insert(p).enqueue(insert_update_CB);
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FavoriteCategory favoriteCategory = (FavoriteCategory)
                        adapterView.getItemAtPosition(i);
                category_id = favoriteCategory.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

            encoded = "data:image/png;base64," + encoded;
            MultipartBody.Part part = MultipartBody.Part.createFormData("image", encoded);
            IRetrofitService service = new RetrofitBuilder()
                    .createSerVice(IRetrofitService.class, BASE_2PIK_URL);
            service.upload2pik(part).enqueue(uploadCB);
        }
    }

    Callback<Product> getFavoriteByIdCB = new Callback<Product>() {
        @Override
        public void onResponse(Call<Product> call, Response<Product> response) {
            if (response.isSuccessful()) {
                product_data = response.body();
                edt_favorite_name.setText(product_data.getProduct_name());
                edt_favorite_singer.setText(product_data.getProduct_singer());
                edt_favorite_song.setText(product_data.getSong_url());
                image_url = product_data.getProduct_image();
                Glide.with(Insert_Favorite.this)
                        .load(product_data.getProduct_image())
                        .into(img_favorite);
            } else {
                Log.e("", response.message());
            }
        }

        @Override
        public void onFailure(Call<Product> call, Throwable t) {
            Log.e("getByIdCB onFailure>>>>", t.getMessage());
        }
    };

    Callback<Response2pikModel> uploadCB = new Callback<Response2pikModel>() {
        @Override
        public void onResponse(Call<Response2pikModel> call, Response<Response2pikModel> response) {
            if (response.isSuccessful()) {
                Response2pikModel model = response.body();
                image_url = model.getSaved();
                Log.i("image url : ", image_url);
                Glide.with(Insert_Favorite.this)
                        .load(image_url)
                        .into(img_favorite);
            } else {
                Log.i("uploadCB Error: ", response.message());
            }
        }

        @Override
        public void onFailure(Call<Response2pikModel> call, Throwable t) {
            Log.i("onFailure uploadCB: ", t.getMessage());
        }
    };

    Callback<ResponseModel> insert_update_CB = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
            if (response.isSuccessful()) {
                ResponseModel model = response.body();
                if (model.getStatus()) {
                    finish();
                } else {
                    Toast.makeText(Insert_Favorite.this, "Insert failed",
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

    final Callback<ArrayList<Category>> getAllCategoriesCB = new Callback<ArrayList<Category>>() {
        @Override
        public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
            if (response.isSuccessful()) {
                data = response.body();
                adapter = new Adapter_Category_SPN(Insert_Favorite.this, data);
                spinner.setAdapter(adapter);
                spinner.setSelection(0);
                if (favoriteId != -1) {
                    service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
                    service.getProductById(new Product(favoriteId, null, null, null, null, -1))
                            .enqueue(getFavoriteByIdCB);
                }
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

        }
    };

    private Integer getIndex(List<Category> _data, int category_id) {
        for (int i = 0; i < _data.size(); i++) {
            if (_data.get(i).getCategory_id() == category_id) {
                return i;
            }
        }
        return 0;
    }

}