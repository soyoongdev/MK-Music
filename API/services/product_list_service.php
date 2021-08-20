<?php
include_once '../configs/database_config.php';
include_once '../models/product.php';
include_once '../models/recommend.php';


class ProductListService
{
    private $connection;
    private $mk_products = "mk_products";
    private $mk_recommends = "mk_recommends";

    public function __construct()
    {
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllListSong()
    {
        try {

            $recommend_id = $_POST['recommend_id'];
            $query = "SELECT * FROM mk_recommends WHERE id = '$recommend_id'";
            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $id = $row['product_id'];
                }
            }

            $query = "SELECT * FROM mk_products WHERE product_id = '$id'";

            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $products = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $product = array(
                        "product_id" => $product_id,
                        "product_name" => $product_name,
                        "product_singer" => $product_singer,
                        "product_image" => $product_image,
                        "song_url" => $song_url,
                        "favorite" => $favorite,
                    );
                    array_push($products, $product);
                }
                return $products;
            }
        } catch (Exception $e) {
        }
        return null;
    }

    public function getSongPlayList()
    {
        try {

            $playlist_id = $_POST['playlist_id'];
            $query = "SELECT * FROM mk_products WHERE FIND_IN_SET('$playlist_id', playlist_id)";
            $stmt = $this->connection->prepare($query);
            $stmt->execute();

            if ($stmt->rowCount() > 0) {
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                }
            }

            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $products = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $product = array(
                        "product_id" => $product_id,
                        "product_name" => $product_name,
                        "product_singer" => $product_singer,
                        "product_image" => $product_image,
                        "song_url" => $song_url,
                        "favorite" => $favorite,
                    );
                    array_push($products, $product);
                }
                return $products;
            }
        } catch (Exception $e) {
        }
    }
}
