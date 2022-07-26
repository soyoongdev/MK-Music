package com.example.miku_project.fragments;

import static com.example.miku_project.Network.BASE_URL;
import static com.example.miku_project.RootData.clearPrefUserFile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.miku_project.R;
import com.example.miku_project.models.User;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.activity_screens.LogIn_screen;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    private ArrayList<User> user_data = new ArrayList<>();
    private ImageView imgAvatarUser;
    private TextView tvPlayListCount, tvFollowerCount, tvFollowingCount;
    private ImageButton btnLogout;

    private IRetrofitService service;
    private int position = 0;
    private String image_url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);

        imgAvatarUser = view.findViewById(R.id.img_avatarUser);
        tvPlayListCount = view.findViewById(R.id.tv_playlistNumber);
        tvFollowerCount = view.findViewById(R.id.tv_followerNumber);
        tvFollowingCount = view.findViewById(R.id.tv_followingNumber);
        btnLogout = view.findViewById(R.id.imgBtn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LogIn_screen.class));
                clearPrefUserFile(getActivity());

                getActivity().finish();
            }
        });

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