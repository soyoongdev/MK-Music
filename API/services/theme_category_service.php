<?php
include_once '../configs/database_config.php';
include_once '../models/theme.php';
include_once '../models/category.php';


class ThemeCategoryService
{
    private $connection;
    private $mk_themes = "mk_themes";
    private $mk_categories = "mk_categories";

    public function __construct()
    {
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllTheme()
    {
        try {
            $query = "SELECT DISTINCT * FROM mk_themes
            ORDER BY rand(" . date("Y-m-d") . ") LIMIT 4";

            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $themes = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $theme = array(
                        "theme_id" => $theme_id,
                        "theme_name" => $theme_name,
                        "theme_image" => $theme_image,
                    );
                    array_push($themes, $theme);
                }
                return $themes;
            }
        } catch (Exception $e) {
        }
        return null;
    }

    public function getAllThemeCategory()
    {
        try {
            $query = "SELECT DISTINCT * FROM mk_categories
            ORDER BY rand(" . date("Y-m-d") . ") LIMIT 4";

            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $categories = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $category = array(
                        "category_id" => $category_id,
                        "category_name" => $category_name,
                        "category_image" => $category_image,
                        "theme_id" => $theme_id,
                    );
                    array_push($categories, $category);
                }
            }

            $query = "SELECT DISTINCT * FROM mk_themes
            ORDER BY rand(" . date("Y-m-d") . ") LIMIT 4";

            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $themes = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $theme = array(
                        "theme_id" => $theme_id,
                        "theme_name" => $theme_name,
                        "theme_image" => $theme_image,
                    );
                    array_push($themes, $theme);
                }
            }

            $themecategory = array('categories' => $categories, 'themes' => $themes);
            return $themecategory;
        } catch (Exception $e) {
        }
        return null;
    }

    public function getAllCate()
    {
        try {
            $query = "SELECT DISTINCT * FROM mk_categories
            ORDER BY rand(" . date("Y-m-d") . ") LIMIT 6";

            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $categories = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $category = array(
                        "category_id" => $category_id,
                        "category_name" => $category_name,
                        "category_image" => $category_image,
                        "theme_id" => $theme_id,
                    );
                    array_push($categories, $category);
                }
                return $categories;
            }
        } catch (Exception $e) {
        }
        return null;
    }
}
