package com.example.miku_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.adapters.Adapter_Category;
import com.example.miku_project.models.Category;
import com.example.miku_project.models.User;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.main_screens.PlaySong_Screen;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private ArrayList<User> user_data = new ArrayList<>();
    private ImageView iv_avatar;
    private TextView tv_name, tv_email;

    private IRetrofitService service;
    private int position = 0;
    private String image_url;

    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        iv_avatar = view.findViewById(R.id.iv_avatar_user);
//        tv_name = view.findViewById(R.id.tv_username);
//        tv_email = view.findViewById(R.id.tv_email_user);
//
//        tv_name.setText(user_data.get(position).getUsername());
//        tv_email.setText(user_data.get(position).getEmail());
//        image_url = user_data.get(position).getAvatar_user();
//        Glide.with(getActivity())
//                .load(image_url)
//                .into(iv_avatar);
//    }

}