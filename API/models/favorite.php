<?php

class Favorite
{
    private $id;
    private $favorite_name;
    private $favorite_image;
    private $favorite_singer;
    private $favorite_song;
    private $time_song;
    private $category_id;

    function __construct($id, $favorite_name, $favorite_image, $favorite_singer, $favorite_song, $time_song, $category_id)
    {
        $this->id = $id;
        $this->favorite_name = $favorite_name;
        $this->favorite_image = $favorite_image;
        $this->favorite_singer = $favorite_singer;
        $this->favorite_song = $favorite_song;
        $this->time_song = $time_song;
        $this->category_id = $category_id;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getFavoriteName()
    {
        return $this->favorite_name;
    }

    public function getFavoriteImage()
    {
        return $this->favorite_image;
    }

    public function getFavoriteSinger()
    {
        return $this->favorite_singer;
    }

    public function getFavoriteSong()
    {
        return $this->favorite_song;
    }

    public function getTimeSong()
    {
        return $this->time_song;
    }

    public function getCategoryId()
    {
        return $this->category_id;
    }
}
