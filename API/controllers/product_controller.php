<?php

include_once '../services/product_service.php';

class ProductController
{

    private $product_service;

    public function __construct()
    {
        $this->product_service = new ProductService();
    }

    public function getAllProducts()
    {
        return $this->product_service->getAllProducts();
    }

    public function getById($produtc_id)
    {
        return $this->product_service->getById($produtc_id);
    }

    public function getAllCategories()
    {
        return $this->product_service->getAllCategories();
    }

    public function insertProduct($product)
    {
        return $this->product_service->insertProduct($product);
    }

    public function update($product)
    {
        return $this->product_service->update($product);
    }

    public function delete($id)
    {
        return $this->product_service->delete($id);
    }

    public function search()
    {
        return $this->product_service->searchName();
    }
}
