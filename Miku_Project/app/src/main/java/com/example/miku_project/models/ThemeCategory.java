package com.example.miku_project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ThemeCategory {
    @SerializedName("categories")
    @Expose
    private ArrayList<Category> categories = null;
    @SerializedName("themes")
    @Expose
    private ArrayList<Theme> themes = null;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }
}