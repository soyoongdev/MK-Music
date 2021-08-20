package com.example.miku_project.models;

import java.io.Serializable;

public class Playlist implements Serializable {
    private Integer playlist_id;
    private String playlist_name;
    private String playlist_image;
    private String playlist_icon;

    public Playlist() {
    }

    public Playlist(Integer playlist_id, String playlist_name, String playlist_image, String playlist_icon) {
        this.playlist_id = playlist_id;
        this.playlist_name = playlist_name;
        this.playlist_image = playlist_image;
        this.playlist_icon = playlist_icon;
    }

    public Integer getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(Integer playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public String getPlaylist_image() {
        return playlist_image;
    }

    public void setPlaylist_image(String playlist_image) {
        this.playlist_image = playlist_image;
    }

    public String getPlaylist_icon() {
        return playlist_icon;
    }

    public void setPlaylist_icon(String playlist_icon) {
        this.playlist_icon = playlist_icon;
    }
}
