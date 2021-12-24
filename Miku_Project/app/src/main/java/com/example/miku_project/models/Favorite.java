package com.example.miku_project.models;

public class Favorite {
    private Integer id;
    private String favorite_name, favorite_image, favorite_singer, favorite_song;
    private Double time_song;
    private Integer category_id;

    public Favorite() {
    }

    public Favorite(Integer id, String favorite_name, String favorite_image, String favorite_singer, String favorite_song, Double time_song, Integer category_id) {
        this.id = id;
        this.favorite_name = favorite_name;
        this.favorite_image = favorite_image;
        this.favorite_singer = favorite_singer;
        this.favorite_song = favorite_song;
        this.time_song = time_song;
        this.category_id = category_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFavorite_name() {
        return favorite_name;
    }

    public void setFavorite_name(String favorite_name) {
        this.favorite_name = favorite_name;
    }

    public String getFavorite_image() {
        return favorite_image;
    }

    public void setFavorite_image(String favorite_image) {
        this.favorite_image = favorite_image;
    }

    public String getFavorite_singer() {
        return favorite_singer;
    }

    public void setFavorite_singer(String favorite_singer) {
        this.favorite_singer = favorite_singer;
    }

    public String getFavorite_song() {
        return favorite_song;
    }

    public void setFavorite_song(String favorite_song) {
        this.favorite_song = favorite_song;
    }

    public Double getTime_song() {
        return time_song;
    }

    public void setTime_song(Double time_song) {
        this.time_song = time_song;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
}
