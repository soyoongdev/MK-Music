package com.example.miku_project.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Product implements Parcelable {
    private Integer product_id;
    private String product_name, product_image, product_singer, song_url;
    private Integer favorite;

    public Product() {
    }

    public Product(Integer product_id, String product_name, String product_image, String product_singer, String song_url, Integer favorite) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_image = product_image;
        this.product_singer = product_singer;
        this.song_url = song_url;
        this.favorite = favorite;
    }

    protected Product(Parcel in) {
        if (in.readByte() == 0) {
            product_id = null;
        } else {
            product_id = in.readInt();
        }
        product_name = in.readString();
        product_image = in.readString();
        product_singer = in.readString();
        song_url = in.readString();
        if (in.readByte() == 0) {
            favorite = null;
        } else {
            favorite = in.readInt();
        }
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public String getProduct_singer() {
        return product_singer;
    }

    public void setProduct_singer(String product_singer) {
        this.product_singer = product_singer;
    }

    public String getSong_url() {
        return song_url;
    }

    public void setSong_url(String song_url) {
        this.song_url = song_url;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (product_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(product_id);
        }
        dest.writeString(product_name);
        dest.writeString(product_image);
        dest.writeString(product_singer);
        dest.writeString(song_url);
        if (favorite == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(favorite);
        }
    }
}
