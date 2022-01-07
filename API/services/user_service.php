<?php
include_once '../configs/database_config.php';
include_once '../models/user.php';
include_once '../models/reset.php';


class UserService
{
    private $connection;
    private $mk_users = "mk_users";
    private $mk_passwordresets = "mk_passwordresets";

    public function __construct()
    {
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function register($user)
    {
        try {
            $query = "INSERT INTO " . $this->mk_users . " 
                                        SET username = :username, email = :email, hash_password = :hash_password
                                        ";
            $stmt = $this->connection->prepare($query);

            $username = $user->getUsername();
            $avatar_user = $user->getAvatarUser();
            $email = $user->getEmail();
            $password = $user->getHashPassword();

            $stmt->bindParam(":username", $username);
            $stmt->bindParam(":email", $email);
            $stmt->bindParam(":hash_password", $password);

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

    public function getUserByEmail($email)
    {
        try {
            $query = "SELECT id, username, avatar_user, email, hash_password FROM " . $this->mk_users . " 
                                        WHERE email = ? LIMIT 0,1";
            $stmt = $this->connection->prepare($query);

            $stmt->bindParam(1, $email);
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                extract($row);
                return new User($id, $username, $avatar_user, $email, $hash_password);
            }
        } catch (Exception $e) {
        }
        return null;
    }


    // tao token reset pass, luu vao bang resetpassword
    public function generateResetToken($email)
    {
        $token = md5($email) . rand(10, 9999);
        try {
            $q = "insert into " . $this->mk_passwordresets . "
                            set email=:email, token=:token";
            $stmt = $this->connection->prepare($q);
            // bind data
            $stmt->bindParam(":email", $email);
            $stmt->bindParam(":token", $token);

            $this->connection->beginTransaction();
            if ($stmt->execute()) {
                $this->connection->commit();
                return $token;
            } else {
                $this->connection->rollBack();
                return null;
            }
        } catch (\Throwable $th) {
        }
        return null;
    }


    // change password
    public function changePassword($email, $hash_password)
    {
        try {
            $q = "update  " . $this->mk_users . "
                            set hash_password=:hash_password where email=:email";
            $stmt = $this->connection->prepare($q);
            // bind data
            $stmt->bindParam(":email", $email);
            $stmt->bindParam(":hash_password", $hash_password);

            $this->connection->beginTransaction();
            if ($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollBack();
                return false;
            }
        } catch (\Throwable $th) {
        }
        return false;
    }

    // clear token
    public function clearToken($token)
    {
        try {
            $q = "update  " . $this->mk_passwordresets . "
                            set available = 0 where token=:token";
            $stmt = $this->connection->prepare($q);
            // bind data
            $stmt->bindParam(":token", $token);

            $this->connection->beginTransaction();
            if ($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollBack();
                return false;
            }
        } catch (\Throwable $th) {
        }
        return false;
    }

    // check token available
    public function getByToken($token, $email)
    {
        try {
            $query = "SELECT id FROM " . $this->mk_passwordresets . " 
                                WHERE email = ? 
                                and token = ?
                                and available = 1
                                and created > now() - interval 30 minute
                                LIMIT 0,1";
            $stmt = $this->connection->prepare($query);

            $stmt->bindParam(1, $email);
            $stmt->bindParam(2, $token);
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                return true;
            }
        } catch (Exception $e) {
        }
        return null;
    }
}
