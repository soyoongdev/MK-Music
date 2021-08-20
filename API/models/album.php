<?php

class Category
{
    private $album_id;
    private $album_name;
    private $album_singer;
    private $album_image;

    function __construct($album_id, $album_name, $album_singer, $album_image)
    {
        $this->album_id = $album_id;
        $this->album_name = $album_name;
        $this->album_singer = $album_singer;
        $this->album_image = $album_image;
    }

    public function getAlbumId()
    {
        return $this->album_id;
    }

    public function getAlbumName()
    {
        return $this->album_name;
    }

    public function getAlbumSinger()
    {
        return $this->album_singer;
    }

    public function getAlbumImage()
    {
        return $this->album_image;
    }
}
