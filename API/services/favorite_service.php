<?php
include_once '../configs/database_config.php';
include_once '../models/favorite.php';

class FavoriteService
{
    private $connection;
    private $mk_favorites = "mk_favorites";
    private $mk_categories = "mk_categories";

    public function __construct()
    {
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllFavorites()
    {
        try {
            $query = "SELECT id, favorite_name, favorite_image, favorite_singer, favorite_song, time_song, category_id
                                FROM " . $this->mk_favorites . "  ";
            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $favorites = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $favorite = array(
                        "id" => $id,
                        "favorite_name" => $favorite_name,
                        "favorite_image" => $favorite_image,
                        "favorite_singer" => $favorite_singer,
                        "favorite_song" => $favorite_song,
                        "time_song" => $time_song,
                        "category_id" => $category_id,
                    );
                    array_push($favorites, $favorite);
                }
                return $favorites;
            }
        } catch (Exception $e) {
        }
        return null;
    }

    public function getFavoriteById($id)
    {
        try {
            $query = "SELECT id, favorite_name, favorite_image, favorite_singer, favorite_song, time_song, category_id
                                 FROM " . $this->mk_favorites . " where id=:id ";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(":id", $id);
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                extract($row);
                $favorite = array(
                    "id" => $id,
                    "favorite_name" => $favorite_name,
                    "favorite_image" => $favorite_image,
                    "favorite_singer" => $favorite_singer,
                    "favorite_song" => $favorite_song,
                    "time_song" => $time_song,
                    "category_id" => $category_id,
                );
                return $favorite;
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

    public function insertFavorite($favorite)
    {
        try {
            $query = "INSERT INTO " . $this->mk_favorites . " 
                                    SET favorite_name = :favorite_name, favorite_image = :favorite_image, favorite_singer = :favorite_singer, favorite_song = :favorite_song, time_song = :time_song, category_id = :category_id
                                    ";
            $stmt = $this->connection->prepare($query);

            $favorite_name = $favorite->getFavoriteName();
            $favorite_image = $favorite->getFavoriteImage();
            $favorite_singer = $favorite->getFavoriteSinger();
            $favorite_song = $favorite->getFavoriteSong();
            $time_song = $favorite->getTimeSong();
            $category_id = $favorite->getCategoryId();

            $stmt->bindParam(":favorite_name", $favorite_name);
            $stmt->bindParam(":favorite_image", $favorite_image);
            $stmt->bindParam(":favorite_singer", $favorite_singer);
            $stmt->bindParam(":favorite_song", $favorite_song);
            $stmt->bindParam(":time_song", $time_song);
            $stmt->bindParam(":category_id", $category_id);

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

    public function updateFavorite($favorite)
    {
        try {
            $query = "UPDATE " . $this->mk_favorites . " 
                        SET favorite_name = :favorite_name, favorite_image = :favorite_image, 
                        favorite_singer = :favorite_singer, favorite_song = :favorite_song, time_song = :time_song, category_id=:category_id
                        WHERE id=:id ";
            $stmt = $this->connection->prepare($query);

            $favorite_name = $favorite->getFavoriteName();
            $favorite_image = $favorite->getFavoriteImage();
            $favorite_singer = $favorite->getFavoriteSinger();
            $favorite_song = $favorite->getFavoriteSong();
            $time_song = $favorite->getTimeSong();
            $category_id = $favorite->getCategoryId();
            $id = $favorite->getId();

            $stmt->bindParam(":favorite_name", $favorite_name);
            $stmt->bindParam(":favorite_image", $favorite_image);
            $stmt->bindParam(":favorite_singer", $favorite_singer);
            $stmt->bindParam(":favorite_song", $favorite_song);
            $stmt->bindParam(":time_song", $time_song);
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

    public function deleteFavorite($id)
    {
        try {
            $query = "DELETE FROM " . $this->mk_favorites . " WHERE id=:id ";
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

    public function favoriteUpdate()
    {
        try {
            $favorite = $_POST['favorite'];;
            $product_id = $_POST['product_id'];
            $query = "SELECT favorite FROM mk_products WHERE product_id = '$product_id'";
            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $sum = $row['favorite'];
                }
            }
            if (isset($favorite)) {
                $sum = $sum + $favorite;
                $querysum = "UPDATE mk_products SET favorite = '$sum' WHERE product_id = 'product_id'";
                $stmt = $this->connection->prepare($querysum);

                $this->connection->beginTransaction();
                if ($stmt->execute()) {
                    $this->connection->commit();
                    return true;
                } else {
                    $this->connection->rollBack();
                    return false;
                }
            }
        } catch (Exception $e) {
        }
        return false;
    }
}
