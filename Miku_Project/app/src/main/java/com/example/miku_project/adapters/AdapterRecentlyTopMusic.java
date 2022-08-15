package com.example.miku_project.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.miku_project.R;
import com.example.miku_project.models.MusicModel;
import com.example.miku_project.screens.main_screens.Playlist_Screen;

import java.util.List;

public class AdapterRecentlyTopMusic extends RecyclerView.Adapter<AdapterRecentlyTopMusic.AdapterRecentlyTopMusicViewHolder> {

    Context context;
    List<MusicModel> data;

    public AdapterRecentlyTopMusic(Context context, List<MusicModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterRecentlyTopMusic.AdapterRecentlyTopMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top_music, parent, false);
        return new AdapterRecentlyTopMusic.AdapterRecentlyTopMusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecentlyTopMusicViewHolder holder, int position) {
        MusicModel playlist = data.get(position);
//        holder.img_avatar.setImageURI(Uri.parse(playlist.getImageUrl()));
        holder.tv_no.setText(playlist.getNo() + "");
        holder.tv_name_song.setText(playlist.getNameSong());
        holder.tv_name_singer.setText(playlist.getNameSinger());
        Glide.with(context).load(playlist.getImageUrl()).into(holder.img_avatar);

        holder.ic_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Playlist_Screen.class);
                intent.putExtra("id_top_music_recently", playlist.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AdapterRecentlyTopMusicViewHolder extends RecyclerView.ViewHolder {
        ImageView img_avatar;
        ImageButton ic_more;
        TextView tv_no, tv_name_song, tv_name_singer;

        AdapterRecentlyTopMusicViewHolder(View view) {
            super(view);
            img_avatar = view.findViewById(R.id.img_avatar_item_top_music_recently);
            ic_more = view.findViewById(R.id.icon_more_item_top_music_recently);
            tv_no = view.findViewById(R.id.tv_no_item_top_music_recently);
            tv_name_song = view.findViewById(R.id.tv_title_item_top_music_recently);
            tv_name_singer = view.findViewById(R.id.tv_subtitle_item_top_music_recently);
        }
    }
}
