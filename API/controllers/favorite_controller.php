<?php

include_once '../services/favorite_service.php';

class FavoriteController
{

    private $favorite_service;

    public function __construct()
    {
        $this->favorite_service = new FavoriteService();
    }

    public function getAllFavorites()
    {
        return $this->favorite_service->getAllFavorites();
    }

    public function getFavoriteById($id)
    {
        return $this->favorite_service->getFavoriteById($id);
    }

    public function getAllCategories()
    {
        return $this->favorite_service->getAllCategories();
    }

    public function insertFavorite($favorite)
    {
        return $this->favorite_service->insertFavorite($favorite);
    }

    public function update($favorite)
    {
        return $this->favorite_service->updateFavorite($favorite);
    }

    public function delete($id)
    {
        return $this->favorite_service->deleteFavorite($id);
    }

    public function favoriteUpdate()
    {
        return $this->favorite_service->favoriteUpdate();
    }
}
