<?php

include_once '../services/recommend_service.php';

class RecommendController
{

    private $recommend_service;

    public function __construct()
    {
        $this->recommend_service = new RecommendService();
    }

    public function getAllRecommends()
    {
        return $this->recommend_service->getAllRecommends();
    }
}
