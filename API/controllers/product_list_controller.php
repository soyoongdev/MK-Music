<?php

include_once '../services/product_list_service.php';

class ProductListController
{

    private $productlist_service;

    public function __construct()
    {
        $this->productlist_service = new ProductListService();
    }

    public function getProductList()
    {
        return $this->productlist_service->getAllListSong();
    }

    public function getPlaylistSong()
    {
        return $this->productlist_service->getSongPlayList();
    }
}
