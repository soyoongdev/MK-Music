<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
$data = json_decode(file_get_contents("php://input"));

include_once '../controllers/favorite_controller.php';
include_once '../models/favorite.php';

$favorite_name = $data->favorite_name;
$favorite_image = $data->favorite_image;
$favorite_singer = $data->favorite_singer;
$favorite_song = $data->favorite_song;
$time_song = $data->time_song;
$category_id = $data->category_id;

if ($favorite_name && $favorite_image && $favorite_singer && $favorite_song && $time_song && $category_id) {
    $favorite = new Favorite(-1, $favorite_name, $favorite_image, $favorite_singer, $favorite_song, $time_song, $category_id);
    $status = (new FavoriteController())->insertFavorite($favorite);
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
