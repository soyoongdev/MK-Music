<?php

class Category
{
    private $category_id;
    private $category_name;
    private $category_image;
    private $theme_id;

    function __construct($category_id, $category_name, $category_image, $theme_id)
    {
        $this->category_id = $category_id;
        $this->category_name = $category_name;
        $this->category_image = $category_image;
        $this->theme_id = $theme_id;
    }

    public function getCategoryId()
    {
        return $this->category_id;
    }

    public function getCategoryName()
    {
        return $this->category_name;
    }

    public function getCategoryImage()
    {
        return $this->category_image;
    }

    public function getThemeId()
    {
        return $this->theme_id;
    }
}
