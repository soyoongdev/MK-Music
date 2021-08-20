<?php

include_once '../services/album_service.php';

class AlbumController
{

    private $album_service;

    public function __construct()
    {
        $this->album_service = new AlbumService();
    }

    public function getAllAlbums()
    {
        return $this->album_service->getAllAlbums();
    }
}
