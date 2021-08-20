<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
$data = json_decode(file_get_contents("php://input"));

include_once '../controllers/product_controller.php';
include_once '../models/product.php';

$product_name = $data->product_name;
$product_singer = $data->product_singer;
$product_image = $data->product_image;
$song_url = $data->song_url;
$category_id = $data->category_id;
$favorite = $data->favorite;

if ($product_name && $product_singer && $product_image && $song_url && $category_id && $favorite) {
    $product = new Product(-1, $product_name, $product_singer, $product_image, $song_url, $category_id, $favorite);
    $status = (new ProductController())->insertProduct($product);
    if ($status) {
        http_response_code(200);
        echo json_encode(
            array(
                "status" => true,
                "message" => "Insert success"
            )
        );
    } else {
        http_response_code(404);
        echo json_encode(
            array(
                "status" => false,
                "message" => "Insert failed"
            )
        );
    }
} else {
    http_response_code(404);
    echo json_encode(
        array(
            "status" => false,
            "message" => "Insert failed"
        )
    );
}
