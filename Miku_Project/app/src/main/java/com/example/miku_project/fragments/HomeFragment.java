package com.example.miku_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.adapters.Adapter_Album;
import com.example.miku_project.adapters.Adapter_Category;
import com.example.miku_project.adapters.Adapter_Playlist;
import com.example.miku_project.adapters.Adapter_Product;
import com.example.miku_project.adapters.Adapter_Recommend;
import com.example.miku_project.adapters.Adapter_Theme;
import com.example.miku_project.models.Album;
import com.example.miku_project.models.Category;
import com.example.miku_project.models.Product;
import com.example.miku_project.models.ProductCategory;
import com.example.miku_project.models.Recommend;
import com.example.miku_project.models.Theme;
import com.example.miku_project.models.ThemeCategory;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private Adapter_Product adapter_product;
    private Adapter_Album adapter_album;
    private Adapter_Category adapter_category;
    private Adapter_Theme adapter_theme;
    private Adapter_Recommend adapter_recommend;
    private RecyclerView rcv_recommend, rcv_category, rcv_album;

    private ArrayList<Product> product_data;
    private ArrayList<ProductCategory> category_data;
    private ArrayList<Recommend> recommend_data;
    private ArrayList<Album> album_data;
    private ArrayList<Theme> theme_data;

    HorizontalScrollView horizontalScrollView;

    private IRetrofitService service;

//    private static String BASE_URL = "http://10.0.2.2:8081/";
    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv_recommend = view.findViewById(R.id.rcv_recommend);
        rcv_album = view.findViewById(R.id.rcv_album);
        rcv_category = view.findViewById(R.id.rcv_category);

//        horizontalScrollView = view.findViewById(R.id.hrz_theme_category);

        rcvRecommend();
        rcvAlbum();
        rcvTheme();
//        getThemeCategory();
    }

    private void rcvTheme() {
        rcv_category.setHasFixedSize(true);
        rcv_category.setLayoutManager(new GridLayoutManager(getContext(), 2));

        service.getAllTheme().enqueue(getAllTheme);
    }

    private void rcvRecommend() {
        rcv_recommend.setHasFixedSize(true);
        rcv_recommend.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

//        service.getAllProduct().enqueue(getAllCB);
        service.getAllRecommends().enqueue(getAllRecommendCB);

    }

    private void rcvAlbum() {
        rcv_album.setHasFixedSize(true);
        rcv_album.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        service.getAllAlbum().enqueue(getAllAlbumCB);

    }

    private void getThemeCategory(){
        service.getThemeCategory().enqueue(getAllThemeCategory);
    }

//    private void rcvCategory() {
//        rcv_category.setHasFixedSize(true);
//        rcv_category.setLayoutManager(new GridLayoutManager(getContext(), 2));
//
//        service.getAllCategories().enqueue(getAllCategoryCB);
//    }


    Callback<ArrayList<Product>> getAllCB = new Callback<ArrayList<Product>>() {
        @Override
        public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
            if (response.isSuccessful()){
                product_data = response.body();
                adapter_product = new Adapter_Product(getContext(), product_data);
                rcv_recommend.setAdapter(adapter_product);
            }else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

        }
    };

    Callback<ArrayList<Theme>> getAllTheme = new Callback<ArrayList<Theme>>() {
        @Override
        public void onResponse(Call<ArrayList<Theme>> call, Response<ArrayList<Theme>> response) {
            if (response.isSuccessful()){
                theme_data = response.body();
                adapter_theme = new Adapter_Theme(getContext(), theme_data);
                rcv_category.setAdapter(adapter_theme);
            }else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Theme>> call, Throwable t) {

        }
    };

    Callback<ArrayList<Recommend>> getAllRecommendCB = new Callback<ArrayList<Recommend>>() {
        @Override
        public void onResponse(Call<ArrayList<Recommend>> call, Response<ArrayList<Recommend>> response) {
            if (response.isSuccessful()){
                recommend_data = response.body();
                adapter_recommend = new Adapter_Recommend(getContext(), recommend_data);
                rcv_recommend.setAdapter(adapter_recommend);
            }else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Recommend>> call, Throwable t) {

        }
    };

    Callback<ArrayList<Album>> getAllAlbumCB = new Callback<ArrayList<Album>>() {
        @Override
        public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
            if (response.isSuccessful()){
                album_data = response.body();
                adapter_album = new Adapter_Album(getContext(), album_data);
                rcv_album.setAdapter(adapter_album);
            }else {
                Log.e(">>>>>Error: ", String.valueOf(album_data));
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Album>> call, Throwable t) {

        }
    };

    Callback<ThemeCategory> getAllThemeCategory = new Callback<ThemeCategory>() {
        @Override
        public void onResponse(Call<ThemeCategory> call, Response<ThemeCategory> response) {
            if (response.isSuccessful()){
                ThemeCategory themeCategory = response.body();

                final ArrayList<Theme> themeArrayList = new ArrayList<>();
                themeArrayList.addAll(themeCategory.getThemes());

                final ArrayList<Category> categoryArrayList = new ArrayList<>();
                categoryArrayList.addAll(themeCategory.getCategories());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(190, 218);
                layoutParams.setMargins(10, 10, 0, 0);

                for(int i = 0; i < (themeArrayList.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (themeArrayList.get(i).getTheme_image() != null) {
                        Glide.with(getActivity()).load(themeArrayList.get(i).getTheme_image()).into(imageView);
                    };
                    TextView textView = new TextView(getActivity());
                    textView.setText(themeArrayList.get(i).getTheme_name());
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    cardView.addView(textView);
                    linearLayout.addView(cardView);
                }

                for(int j = 0; j < (categoryArrayList.size()); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (categoryArrayList.get(j).getCategory_image() != null) {
                        Glide.with(getActivity()).load(categoryArrayList.get(j).getCategory_image()).into(imageView);
                    };
                    TextView textView = new TextView(getActivity());
                    textView.setText(categoryArrayList.get(j).getCategory_image());
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    cardView.addView(textView);
                    linearLayout.addView(cardView);
                }

                horizontalScrollView.addView(linearLayout);


            }else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ThemeCategory> call, Throwable t) {

        }
    };

//    Callback<ArrayList<ProductCategory>> getAllCategoryCB = new Callback<ArrayList<ProductCategory>>() {
//        @Override
//        public void onResponse(Call<ArrayList<ProductCategory>> call, Response<ArrayList<ProductCategory>> response) {
//            if (response.isSuccessful()){
//                category_data = response.body();
//                adapter_category = new Adapter_Category(getContext(), category_data);
//                rcv_category.setAdapter(adapter_category);
//            }else {
//                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onFailure(Call<ArrayList<ProductCategory>> call, Throwable t) {
//
//        }
//    };

    @Override
    public void onResume() {
        super.onResume();
        reloadscreen();
    }

    private void reloadscreen(){
        IRetrofitService service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        service.getAllRecommends().enqueue(getAllRecommendCB);
//        service.getAllProduct().enqueue(getAllCB);
//        service.getAllCategories().enqueue(getAllCategoryCB);
    }
}