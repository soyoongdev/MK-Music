package com.example.miku_project.screens.fragment_screens;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SongDiscFragment extends Fragment {

    private View view;
    private CircleImageView iv_product_image;
    public ObjectAnimator objectAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_disc, container, false);
        iv_product_image = view.findViewById(R.id.iv_product_image);
        objectAnimator = ObjectAnimator.ofFloat(iv_product_image, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        return view;
    }

    public void Playsong(String image){
        Glide.with(getActivity()).load(image).into(iv_product_image);
    }
}
