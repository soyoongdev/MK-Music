package com.example.miku_project.models;

public class Category {
    private Integer category_id;
    private String category_name;
    private String category_image;
    private String theme_id;

    public Category() {
    }

    public Category(Integer category_id, String category_name, String category_image, String theme_id) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_image = category_image;
        this.theme_id = theme_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
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

    public String getThem_id() {
        return theme_id;
    }

    public void setThem_id(String them_id) {
        this.theme_id = them_id;
    }
}
