

CREATE DATABASE IF NOT EXISTS `MIKU_PROJECT`;

USE `MIKU_PROJECT`;

CREATE TABLE IF NOT EXISTS `mk_users`(
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(60) NOT NULL,
    `avatar_user` VARCHAR(255) NOT NULL DEFAULT '',
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `hash_password` VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS `mk_passwordresets`(
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL,
    `token` VARCHAR(255) NOT NULL UNIQUE,
    `created` DATETIME NOT NULL DEFAULT NOW(),
    `available` BIT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS `mk_playlists`(
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `playlist_name` VARCHAR(255) NOT NULL,
    `playlist_background` VARCHAR(255) NOT NULL DEFAULT '',
    `playlist_icon` VARCHAR(255) NOT NULL DEFAULT ''
);


CREATE TABLE IF NOT EXISTS `mk_themes`(
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `theme_name` VARCHAR(255) NOT NULL,
    `theme_image` VARCHAR(255) NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS `mk_albums`(
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `album_name` VARCHAR(255) NOT NULL,
    `album_singer` VARCHAR(255) NOT NULL,
    `album_image` VARCHAR(255) NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS `mk_categories`(
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `category_name` VARCHAR(255) NOT NULL,
    `category_image` VARCHAR(255) NOT NULL DEFAULT '',
    `theme_id` INT(11) NOT NULL,
    FOREIGN KEY(`theme_id`) REFERENCES mk_themes(`id`)
);

CREATE TABLE IF NOT EXISTS `mk_products`(
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_name` VARCHAR(255) NOT NULL DEFAULT '',
    `singer` VARCHAR(255) NOT NULL,
    `product_image` VARCHAR(255) NOT NULL DEFAULT '',
    `song` VARCHAR(255) NOT NULL DEFAULT '',
    `favorite` INT(11) NULL,
    `category_id` VARCHAR(255) NOT NULL,
    `album_id` VARCHAR(255) NOT NULL,
    `playlist_id` VARCHAR(11) NOT NULL,
    FOREIGN KEY(`category_id`) REFERENCES mk_categories(`id`),
    FOREIGN KEY(`album_id`) REFERENCES mk_albums(`id`),
    FOREIGN KEY(`playlist_id`) REFERENCES mk_playlists(`id`)
);

CREATE TABLE IF NOT EXISTS `mk_recommends`(
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `recommend_name` VARCHAR(255) NOT NULL,
    `recommend_image` VARCHAR(255) NOT NULL DEFAULT '',
    `product_id` INT(11) NOT NULL,
    FOREIGN KEY(`product_id`) REFERENCES mk_products(`id`)
);


INSERT INTO `mk_users` (`username`, `avatar_user` , `email`, `hash_password`) 
VALUES ('qanh1604', 'https://2.pik.vn/20217261e878-a1c9-4f52-81d5-74c0d6194c2d.png', 'ngoquocanh1604@gmail.com', '$2y$10$C/i8h8x8vpbYLqTweZMAquaFZi4kbSv/HNHvd70Ba4BwM14TBHxHq');

INSERT INTO `mk_categories` (`category_name`, `category_image`) 
VALUES 
('jazz', 'https://2.pik.vn/2021d2d705f5-4003-4f58-b452-f89444c78660.jpg'), 
('rock', 'https://2.pik.vn/202131d21b84-3e76-4cf6-956b-d76bad44cee6.png'), 
('guitar', 'https://2.pik.vn/202170ddbce3-30a3-4ca3-8827-e81ccd1d70e8.jpg'), 
('dance', 'https://2.pik.vn/2021de73f0df-fb4d-4b80-9c44-64ec04a3cf26.jpg'), 
('edm', 'https://2.pik.vn/20218c1e6db8-2d0e-4ac4-9a92-da6acf11fd36.jpg');

INSERT INTO `mk_products` (`product_name`, `singer`, `product_image`, `song`, `category_id`)
VALUES 
('Hollywod', 'Gorillaz', 'https://2.pik.vn/2021f72440c7-525f-466a-adbc-1d3753025114.jpg', '', 1),
('Mr. FEAR', 'SIAMES', 'https://2.pik.vn/20213beb2a37-36d1-42f4-9eaf-3edb8e151f4a.jpg', '', 2),
('POP', 'Mar', 'https://2.pik.vn/20213beb2a37-36d1-42f4-9eaf-3edb8e151f4a.jpg', '', 3),
('Mr. FEAR', 'SIAMES', 'https://2.pik.vn/20213beb2a37-36d1-42f4-9eaf-3edb8e151f4a.jpg', '', 4),
