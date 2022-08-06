package com.example.miku_project.screens.fragment_screens.home.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.miku_project.R;
import com.makeramen.roundedimageview.RoundedImageView;

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
            imageView.setImageResource(item.getImage());
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
