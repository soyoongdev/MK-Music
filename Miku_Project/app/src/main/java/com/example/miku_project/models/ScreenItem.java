package com.example.miku_project.models;

public class ScreenItem {

    String Title;
    int ScreenImg;

    public ScreenItem(String title, int screenImg) {
        Title = title;
        ScreenImg = screenImg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}
