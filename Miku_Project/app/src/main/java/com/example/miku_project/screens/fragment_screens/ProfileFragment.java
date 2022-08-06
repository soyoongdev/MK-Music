package com.example.miku_project.screens.fragment_screens;

import static com.example.miku_project.helper.Config.BASE_URL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.miku_project.R;
import com.example.miku_project.helper.RootData;
import com.example.miku_project.models.User;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    private ArrayList<User> user_data = new ArrayList<>();
    private ImageView imgAvatarUser;
    private TextView tvPlayListCount, tvFollowerCount, tvFollowingCount, tvLogout;
    private Button imgBtn_edit;

    private IRetrofitService service;
    private int position = 0;
    private String image_url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);

        imgAvatarUser = view.findViewById(R.id.img_avatarUser);
        tvPlayListCount = view.findViewById(R.id.tv_playlistNumber);
        tvFollowerCount = view.findViewById(R.id.tv_followerNumber);
        tvFollowingCount = view.findViewById(R.id.tv_followingNumber);
        tvLogout = view.findViewById(R.id.tvLogout);
        imgBtn_edit = view.findViewById(R.id.imgBtn_edit);

        imgBtn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RootData.clearPrefUserFile(getActivity());
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Email: " + RootData.getPrefUserData(getActivity()).getEmail() + "\n" + "Password: " + RootData.getPrefUserData(getActivity()).getPassword());
//                startActivity(new Intent(getActivity(), LogIn_screen.class));
//                clearPrefUserFile(getActivity());
//
//                getActivity().finish();
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