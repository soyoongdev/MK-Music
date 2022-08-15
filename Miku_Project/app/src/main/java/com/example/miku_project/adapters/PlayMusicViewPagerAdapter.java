package com.example.miku_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.example.miku_project.R;

import java.util.List;

public class PlayMusicViewPagerAdapter extends LoopingPagerAdapter<Integer> {

    public PlayMusicViewPagerAdapter(Context context, List<Integer> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        return LayoutInflater.from(context).inflate(R.layout.item_pager, container, false);
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        convertView.findViewById(R.id.image).setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));
    }
}
