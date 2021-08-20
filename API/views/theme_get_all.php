<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

$data = json_decode(file_get_contents("php://input"));

include_once '../controllers/theme_category_controller.php';

$theme = (new ThemeCategoryController())->getAllTheme();

if ($theme) {
    http_response_code(200);
    echo json_encode($theme);
} else {
    http_response_code(401);
    echo json_encode(array());
}
