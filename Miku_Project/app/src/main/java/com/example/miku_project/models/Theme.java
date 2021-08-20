package com.example.miku_project.models;

public class Theme {
    private Integer theme_id;
    private String theme_name;
    private String theme_image;

    public Theme() {
    }

    public Theme(Integer theme_id, String theme_name, String theme_image) {
        this.theme_id = theme_id;
        this.theme_name = theme_name;
        this.theme_image = theme_image;
    }

    public Integer getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    public String getTheme_image() {
        return theme_image;
    }

    public void setTheme_image(String theme_image) {
        this.theme_image = theme_image;
    }
}
