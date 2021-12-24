package com.example.miku_project.models;

public class Album {
    private Integer album_id;
    private String album_name;
    private String album_singer;
    private String album_image;

    public Album() {
    }

    public Album(Integer album_id, String album_name, String album_singer, String album_image) {
        this.album_id = album_id;
        this.album_name = album_name;
        this.album_singer = album_singer;
        this.album_image = album_image;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_singer() {
        return album_singer;
    }

    public void setAlbum_singer(String album_singer) {
        this.album_singer = album_singer;
    }

    public String getAlbum_image() {
        return album_image;
    }

    public void setAlbum_image(String album_image) {
        this.album_image = album_image;
    }
}
