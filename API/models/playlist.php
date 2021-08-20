<?php

class Playlist
{
    private $playlist_id;
    private $playlist_name;
    private $playlist_image;
    private $playlist_icon;

    function __construct($playlist_id, $playlist_name, $playlist_image, $playlist_icon)
    {
        $this->playlist_id = $playlist_id;
        $this->playlist_name = $playlist_name;
        $this->playlist_image = $playlist_image;
        $this->playlist_icon = $playlist_icon;
    }

    public function getPlaylistId()
    {
        return $this->playlist_id;
    }

    public function getPlaylistName()
    {
        return $this->playlist_name;
    }

    public function getPlaylistImage()
    {
        return $this->playlist_image;
    }

    public function getPlaylistIcon()
    {
        return $this->playlist_icon;
    }
}
