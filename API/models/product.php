<?php

class Product
{
    private $product_id;
    private $product_name;
    private $product_singer;
    private $product_image;
    private $song_url;
    private $category_id;
    private $favorite;

    function __construct($product_id, $product_name, $product_singer, $product_image, $song_url, $category_id, $favorite)
    {
        $this->product_id = $product_id;
        $this->product_name = $product_name;
        $this->product_singer = $product_singer;
        $this->product_image = $product_image;
        $this->song_url = $song_url;
        $this->category_id = $category_id;
        $this->favorite = $favorite;
    }

    public function getPproductId()
    {
        return $this->product_id;
    }

    public function getProductName()
    {
        return $this->product_name;
    }

    public function getProductSinger()
    {
        return $this->product_singer;
    }

    public function getProductImage()
    {
        return $this->product_image;
    }

    public function getSongUrl()
    {
        return $this->song_url;
    }

    public function getCategoryId()
    {
        return $this->category_id;
    }

    public function getFavorite()
    {
        return $this->favorite;
    }
}
