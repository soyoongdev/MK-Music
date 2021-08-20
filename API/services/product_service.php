<?php
include_once '../configs/database_config.php';
include_once '../models/user.php';
include_once '../models/reset.php';


class ProductService
{
    private $connection;
    private $mk_products = "mk_products";
    private $mk_categories = "mk_categories";

    public function __construct()
    {
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllProducts()
    {
        try {
            $query = "SELECT * FROM mk_products ORDER BY favorite DESC";
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

    public function getById($id)
    {
        try {
            $query = "SELECT id, product_name, singer, product_image, song, category_id
                                 FROM " . $this->mk_products . " where id=:id ";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(":id", $id);
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                extract($row);
                $product = array(
                    "id" => $id,
                    "product_name" => $product_name,
                    "singer" => $singer,
                    "product_image" => $product_image,
                    "song" => $song,
                    "category_id" => $category_id,
                );
                return $product;
            }
        } catch (Exception $e) {
        }
        return null;
    }

    public function getAllCategories()
    {
        try {
            $query = "SELECT id, category_name, category_image
                                 FROM " . $this->mk_categories . "  ";
            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $categories = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $cat = array(
                        "id" => $id,
                        "category_name" => $category_name,
                        "category_image" => $category_image,
                    );
                    array_push($categories, $cat);
                }
                return $categories;
            }
        } catch (Exception $e) {
        }
        return null;
    }

    public function insertProduct($product)
    {
        try {
            $query = "INSERT INTO " . $this->mk_products . " 
                                    SET product_name = :product_name, product_singer = :product_singer, product_image = :product_image, song_url = :song_url, category_id = :category_id, favorite = :favorite
                                    ";
            $stmt = $this->connection->prepare($query);

            $product_name = $product->getProductName();
            $product_singer = $product->getProductSinger();
            $product_image = $product->getProductImage();
            $song_url = $product->getSongUrl();
            $category_id = $product->getCategoryId();
            $favorite = $product->getFavorite();

            $stmt->bindParam(":product_name", $product_name);
            $stmt->bindParam(":product_singer", $product_singer);
            $stmt->bindParam(":product_image", $product_image);
            $stmt->bindParam(":song_url", $song_url);
            $stmt->bindParam(":category_id", $category_id);
            $stmt->bindParam(":favorite", $favorite);

            $this->connection->beginTransaction();
            if ($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollBack();
                return false;
            }
        } catch (Exception $e) {
        }
        return false;
    }

    public function update($product)
    {
        try {
            $query = "UPDATE " . $this->mk_products . " 
                        SET product_name = :product_name, singer = :singer, 
                        product_image=:product_image, song=:song, category_id=:category_id
                        WHERE id=:id ";
            $stmt = $this->connection->prepare($query);

            $product_name = $product->getProductName();
            $singer = $product->getSinger();
            $product_image = $product->getProductImage();
            $song = $product->getSong();
            $category_id = $product->getCategoryId();
            $id = $product->getId();

            $stmt->bindParam(":product_name", $product_name);
            $stmt->bindParam(":singer", $singer);
            $stmt->bindParam(":product_image", $product_image);
            $stmt->bindParam(":song", $song);
            $stmt->bindParam(":category_id", $category_id);
            $stmt->bindParam(":id", $id);

            $this->connection->beginTransaction();
            if ($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollBack();
                return false;
            }
        } catch (Exception $e) {
        }
        return false;
    }

    public function delete($id)
    {
        try {
            $query = "DELETE FROM " . $this->mk_products . " WHERE id=:id ";
            $stmt = $this->connection->prepare($query);

            $stmt->bindParam(":id", $id);

            $this->connection->beginTransaction();
            if ($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollBack();
                return false;
            }
        } catch (Exception $e) {
        }
        return false;
    }

    public function searchName()
    {
        try {
            $keyname = $_POST['keyname'];
            $query = "SELECT * FROM mk_products WHERE lower(product_name) LIKE '%&keyname%'";
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
}
