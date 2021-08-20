<?php
class DatabaseConfig
{
    private $host = "127.0.0.1:3306";
    private $username = "root";
    private $password = "";
    private $database_name = "miku_project";

    public $connection;

    public function getConnection()
    {
        $this->connection = null;
        try {
            $this->connection = new PDO(
                "mysql:host=" . $this->host .
                    "; dbname=" . $this->database_name,
                $this->username,
                $this->password
            );
            $this->connection->exec("set names utf8");
        } catch (Exception $e) {
            echo "Connect database failed: " . $e->getMessage();
        }
        return $this->connection;
    }
}
