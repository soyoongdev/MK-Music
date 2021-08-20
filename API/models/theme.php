<?php

class Theme
{
    private $theme_id;
    private $theme_name;
    private $theme_image;

    function __construct($theme_id, $theme_name, $theme_image)
    {
        $this->theme_id = $theme_id;
        $this->theme_name = $theme_name;
        $this->theme_image = $theme_image;
    }

    public function getThemeId()
    {
        return $this->theme_id;
    }

    public function getThemeName()
    {
        return $this->theme_name;
    }

    public function getThemeImage()
    {
        return $this->theme_image;
    }
}
