package com.example.miku_project.screens.fragment_screens.home.banner;

public class BannerModel {
    private String urlImage;
    private int image;

    public BannerModel(String urlImage) {
        this.urlImage = urlImage;
    }

    public BannerModel(int image) {
        this.image = image;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
