package com.example.miku_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.miku_project.R;
import com.example.miku_project.models.ScreenItem;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {
    Context mContext;
    List<ScreenItem> mListScreen;

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_onboarding, container, false);

        ImageView imgSlide = view.findViewById(R.id.intro_img);
        TextView title = view.findViewById(R.id.intro_title);

        title.setText(mListScreen.get(position).getTitle());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
