package com.example.miku_project.screens.fragment_screens.home.banner;

public class BannerModel {
    private String id;
    private String imageUrl;
    private String nameSong;


    public BannerModel(String id, String imageUrl, String nameSong) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.nameSong = nameSong;
    }

    public BannerModel() {
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }
}
