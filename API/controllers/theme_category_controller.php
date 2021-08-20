<?php

include_once '../services/theme_category_service.php';

class ThemeCategoryController
{

    private $theme_category_service;

    public function __construct()
    {
        $this->theme_category_service = new ThemeCategoryService();
    }

    public function getAllTheme()
    {
        return $this->theme_category_service->getAllTheme();
    }

    public function getAllThemeCategory()
    {
        return $this->theme_category_service->getAllThemeCategory();
    }

    public function getAllCate()
    {
        return $this->theme_category_service->getAllCate();
    }
}
