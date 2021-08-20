package com.example.miku_project.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miku_project.R;
import com.example.miku_project.adapters.Adapter_Category;
import com.example.miku_project.adapters.Adapter_Playlist;
import com.example.miku_project.adapters.Adapter_SearchProduct;
import com.example.miku_project.models.Category;
import com.example.miku_project.models.Playlist;
import com.example.miku_project.models.Product;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private Adapter_Category adapter_category;
    private Adapter_SearchProduct adapter_searchProduct;
    private ArrayList<Category> category_data;
    private ArrayList<Product> product_data;
    private RecyclerView rcv_cate, rcv_search;
    private RelativeLayout rl_error;
    private SearchView searchView;

    private IRetrofitService service;

    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_cate = view.findViewById(R.id.rcv_caterory_search);
        rl_error = view.findViewById(R.id.rl_searcherror);
        searchView = view.findViewById(R.id.search);
        rcv_search = view.findViewById(R.id.rcv_searched);

        service.getCategory().enqueue(getAllCategory);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchKey(newText);

                return true;
            }
        });

    }

    Callback<ArrayList<Category>> getAllCategory = new Callback<ArrayList<Category>>() {
        @Override
        public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
            if (response.isSuccessful()) {
                category_data = response.body();
                adapter_category = new Adapter_Category(getContext(), category_data);
                rcv_cate.setAdapter(adapter_category);
                rcv_cate.setHasFixedSize(true);
                rcv_cate.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            } else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

        }
    };

    private void SearchKey(String query) {
        if (query.length() < 1){
            rcv_cate.setVisibility(View.VISIBLE);
            rcv_search.setVisibility(View.GONE);

        }
        else {
            rcv_cate.setVisibility(View.GONE);
            rcv_search.setVisibility(View.VISIBLE);
            service.getSearch(query).enqueue(searchCB);
        }

    }

    Callback<ArrayList<Product>> searchCB = new Callback<ArrayList<Product>>() {
        @Override
        public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
            if (response.isSuccessful()) {
                product_data = response.body();
                if (product_data.size() > 0) {
                    adapter_searchProduct = new Adapter_SearchProduct(getActivity(), product_data);
                    rcv_search.setAdapter(adapter_searchProduct);
                    rcv_search.setHasFixedSize(true);
                    rcv_search.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

        }
    };
}