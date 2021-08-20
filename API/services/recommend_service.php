<?php
include_once '../configs/database_config.php';
include_once '../models/recommend.php';
include_once '../models/favorite.php';


class RecommendService
{
    private $connection;
    private $mk_recommends = "mk_recommends";
    private $mk_product = "mk_product";

    public function __construct()
    {
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllRecommends()
    {
        try {
            $query = "SELECT DISTINCT mk_recommends.id, 
            mk_recommends.recommends_image, 
            mk_recommends.recommends_name, 
            mk_recommends.product_id, 
            mk_products.product_name, 
            mk_products.product_image FROM mk_products 
            INNER JOIN mk_recommends ON mk_recommends.product_id = mk_products.product_id 
            WHERE mk_recommends.product_id = mk_products.product_id 
            ORDER BY rand(" . date("Y-m-d") . ") LIMIT 3";

            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $recommends = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $recommend = array(
                        "id" => $id,
                        "recommends_name" => $recommends_name,
                        "recommends_image" => $recommends_image,
                        "product_id" => $product_id,
                        "product_name" => $product_name,
                        "product_image" => $product_image,
                    );
                    array_push($recommends, $recommend);
                }
                return $recommends;
            }
        } catch (Exception $e) {
        }
        return null;
    }
}
