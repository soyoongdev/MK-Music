package com.example.miku_project.models;

public class FavoriteCategory {
    private Integer id;
    private String category_name, category_image;

    public FavoriteCategory() {
    }

    public FavoriteCategory(Integer id, String category_name, String category_image) {
        this.id = id;
        this.category_name = category_name;
        this.category_image = category_image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }
}
