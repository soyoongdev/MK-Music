package com.example.miku_project.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.models.Category;
import com.example.miku_project.models.Favorite;
import com.example.miku_project.models.FavoriteCategory;

import java.util.ArrayList;

public class Adapter_Category_SPN extends BaseAdapter {

    Context context;
    ArrayList<Category> data;

    public Adapter_Category_SPN(Context context, ArrayList<Category> data){
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = View.inflate(parent.getContext(), R.layout.spn_category, null);
            TextView name = (TextView) view.findViewById(R.id.spn_category_name);
            ImageView image = (ImageView) view.findViewById(R.id.spn_category_image);
            ViewHolder holder = new ViewHolder(name, image);
            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        FavoriteCategory fc = (FavoriteCategory) getItem(position);
        holder.name.setText(fc.getCategory_name());

        Glide.with(context).load(fc.getCategory_image()).into(holder.category_image);

        return view;
    }

    private static class ViewHolder{
        final private TextView name;
        final private ImageView category_image;
        public ViewHolder(TextView _name, ImageView _image){
            this.name = _name;
            this.category_image = _image;
        }
    }
}
