package com.example.miku_project.screens.fragment_screens.home.banner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.miku_project.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class SliderBannerAdapter extends RecyclerView.Adapter<SliderBannerAdapter.SliderBannerViewHolder> {

    private List<BannerModel> sliderItems;
    private ViewPager2 viewPager;

    public SliderBannerAdapter(List<BannerModel> sliderItems, ViewPager2 viewPager) {
        this.sliderItems = sliderItems;
        this.viewPager = viewPager;
    }

    @NonNull
    @Override
    public SliderBannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderBannerViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        if (position == sliderItems.size() -2) {
            viewPager.post(slideAround);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderBannerViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView imageView;

        SliderBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rounded_image_banner);
        }

        void setImage(BannerModel item) {
            Log.d("SliderBannerAdapter Log", item.getImageUrl());

            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.BLACK)
                    .borderWidthDp(2)
                    .cornerRadiusDp(20)
                    .oval(false)
                    .build();

            Picasso.get()
                    .load(item.getImageUrl())
                    .fit()
                    .transform(transformation)
                    .placeholder(R.drawable.pop)
                    .error(R.drawable.dance)
                    .into(imageView);
        }
    }

    private Runnable slideAround = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
