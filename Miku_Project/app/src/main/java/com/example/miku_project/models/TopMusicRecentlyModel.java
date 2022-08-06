package com.example.miku_project.models;

public class TopMusicRecentlyModel {
    private int id;
    private int no;
    private int image;
    private String nameSong;
    private String nameSigner;

    public TopMusicRecentlyModel(int id, int no, int image, String nameSong, String nameSigner) {
        this.id = id;
        this.no = no;
        this.image = image;
        this.nameSong = nameSong;
        this.nameSigner = nameSigner;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameSigner() {
        return nameSigner;
    }

    public void setNameSigner(String nameSigner) {
        this.nameSigner = nameSigner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
