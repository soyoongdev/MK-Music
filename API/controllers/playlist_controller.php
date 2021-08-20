<?php

include_once '../services/playlist_service.php';

class PlaylistController
{

    private $playlist_service;

    public function __construct()
    {
        $this->playlist_service = new PlaylistService();
    }

    public function getAllPlaylists()
    {
        return $this->playlist_service->getAllPlaylists();
    }
}
