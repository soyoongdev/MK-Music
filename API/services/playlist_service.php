<?php
include_once '../configs/database_config.php';
include_once '../models/playlist.php';


class PlaylistService
{
    private $connection;
    private $mk_playlists = "mk_playlists";

    public function __construct()
    {
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllPlaylists()
    {
        try {
            $query = "SELECT DISTINCT * FROM mk_playlists
            ORDER BY rand(" . date("Y-m-d") . ") LIMIT 8";

            $stmt = $this->connection->prepare($query);

            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $playlists = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    extract($row);
                    $playlist = array(
                        "playlist_id" => $playlist_id,
                        "playlist_name" => $playlist_name,
                        "playlist_image" => $playlist_image,
                        "playlist_icon" => $playlist_icon,
                    );
                    array_push($playlists, $playlist);
                }
                return $playlists;
            }
        } catch (Exception $e) {
        }
        return null;
    }
}
