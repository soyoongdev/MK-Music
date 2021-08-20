<?php

use PHPMailer\PHPMailer\PHPMailer;

include_once '../libs/PHPMailer-master/src/PHPMailer.php';
include_once '../libs/PHPMailer-master/src/SMTP.php';
include_once '../libs/PHPMailer-master/src/Exception.php';

include_once '../services/user_service.php';
include_once '../models/user.php';

class UserController
{

    private $user_service;
    public function __construct()
    {
        $this->user_service = new UserService();
    }

    public function login($email, $password)
    {
        $user = $this->user_service->getUserByEmail($email);
        if ($user) {
            $password_valid = password_verify($password, $user->getHashPassword());
            if ($password_valid) {
                return true;
            }
        }
        return false;
    }

    public function register($username, $email, $password)
    {
        $user = $this->user_service->getUserByEmail($email);
        if (!$user) {
            $hash_password = password_hash($password, PASSWORD_BCRYPT);
            $new_user = new User(-1, $username, '', $email, $hash_password);
            $status = $this->user_service->register($new_user);
            if ($status) {
                return true;
            }
        }
        return false;
    }



    public function sendEmailResetPassToUser($email)
    {
        $_email = $this->user_service->getUserByEmail($email);
        if ($_email) {
            $token = $this->user_service->generateResetToken($email);
            if ($token) {
                // gui email
                return $this->sendEmail($email, $token);
            }
        }
        return false;
    }

    private function sendEmail($email, $token)
    {
        $link = "<a href='http://127.0.0.1:8081/views/user_reset_password_form.php?key="
            . $email . "&token=" . $token . "'> Click to reset password</a>";
        $mail = new PHPMailer();

        $mail->CharSet = "utf-8";
        $mail->isSMTP();
        $mail->SMTPAuth = true;
        $mail->Username = "qanhtest1604";
        $mail->Password = "0964066707";
        $mail->SMTPSecure = "ssl";
        $mail->Host = "ssl://smtp.gmail.com";
        $mail->Port = "465";
        $mail->From = "qanhtest1604@gmail.com";
        $mail->FromName = "Miku - Project ";
        $mail->addAddress($email, 'Hello');
        $mail->Subject = "Reset Password";
        $mail->isHTML(true);
        $mail->Body = "Click on this link to reset password " . $link . " ";

        if ($mail->Send()) {
            return true;
        }
        return false;
    }


    public function getByToken($token, $email)
    {
        return $this->user_service->getByToken($token, $email);
    }

    public function updatePasswordAndToken($token, $email, $password)
    {
        $reset_token = $this->user_service->getByToken($token, $email);
        if ($reset_token) {
            $hash_password = password_hash($password, PASSWORD_BCRYPT);
            $change_password = $this->user_service->changePassword($email, $hash_password);
            if ($change_password) {
                return $this->user_service->clearToken($token);
            }
        }
        return false;
    }
}
