<?php
class User
{
    private $id;
    private $username;
    private $avatar_user;
    private $email;
    private $hash_password;

    function __construct($id, $username, $avatar_user, $email, $hash_password)
    {
        $this->id = $id;
        $this->username = $username;
        $this->avatar_user = $avatar_user;
        $this->email = $email;
        $this->hash_password = $hash_password;
    }

    public function getId()
    {
        return $this->id;
    }
    public function getUsername()
    {
        return $this->username;
    }
    public function getAvatarUser()
    {
        return $this->avatar_user;
    }
    public function getEmail()
    {
        return $this->email;
    }
    public function getHashPassword()
    {
        return $this->hash_password;
    }
}
