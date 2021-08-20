<?php
include_once '../configs/database_config.php';
include_once '../models/album.php';


class AlbumService
{
    private $connection;
    private $mk_albums = "mk_albums";

    public function __construct()
    {
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllAlbums()
    {
        try {
            $query = "SELECT DISTINCT * FROM mk_albums
            ORDER BY rand(" . date("Y-m-d") . ") LIMIT 6";

            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $albums = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $album = array(
                        "album_id" => $album_id,
                        "album_name" => $album_name,
                        "album_singer" => $album_singer,
                        "album_image" => $album_image,
                    );
                    array_push($albums, $album);
                }
                return $albums;
            }
        } catch (Exception $e) {
        }
        return null;
    }
}
