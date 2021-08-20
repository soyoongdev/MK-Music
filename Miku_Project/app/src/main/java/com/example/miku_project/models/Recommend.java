package com.example.miku_project.models;

import java.io.Serializable;

public class Recommend implements Serializable {
    private Integer id;
    private String recommends_name;
    private String recommends_image;
    private Integer product_id;
    private String product_name;
    private String product_image;

    public Recommend() {
    }

    public Recommend(Integer id, String recommends_name, String recommends_image, Integer product_id, String product_name, String product_image) {
        this.id = id;
        this.recommends_name = recommends_name;
        this.recommends_image = recommends_image;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_image = product_image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecommends_name() {
        return recommends_name;
    }

    public void setRecommends_name(String recommends_name) {
        this.recommends_name = recommends_name;
    }

    public String getRecommends_image() {
        return recommends_image;
    }

    public void setRecommends_image(String recommends_image) {
        this.recommends_image = recommends_image;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
