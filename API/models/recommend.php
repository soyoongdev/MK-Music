<?php

class Recommend
{
    private $id;
    private $recommends_name;
    private $recommends_image;
    private $product_id;
    private $product_name;
    private $product_image;

    function __construct($id, $recommends_name, $recommends_image, $product_id, $product_name, $product_image)
    {
        $this->id = $id;
        $this->recommends_name = $recommends_name;
        $this->recommends_image = $recommends_image;
        $this->product_id = $product_id;
        $this->product_name = $product_name;
        $this->product_image = $product_image;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getRecommendSName()
    {
        return $this->recommends_name;
    }

    public function getRecommendSImage()
    {
        return $this->recommends_image;
    }

    public function getProductId()
    {
        return $this->product_id;
    }

    public function getProductName()
    {
        return $this->product_name;
    }

    public function getProductImage()
    {
        return $this->product_image;
    }
}
