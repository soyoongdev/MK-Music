package com.example.miku_project.models;

public class MusicModel {
    private String id;
    private String nameSong;
    private String imageUrl;
    private String nameSinger;
    private int no;

    public MusicModel(String id, String nameSong, String imageUrl, String nameSinger, int no) {
        this.id = id;
        this.nameSong = nameSong;
        this.imageUrl = imageUrl;
        this.nameSinger = nameSinger;
        this.no = no;
    }

    public MusicModel(String id, String nameSong, String imageUrl, String nameSinger) {
        this.id = id;
        this.nameSong = nameSong;
        this.imageUrl = imageUrl;
        this.nameSinger = nameSinger;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }
}
