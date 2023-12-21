CREATE DATABASE IF NOT EXISTS bookstore2;

USE bookstore2;

SHOW TABLES;
--
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    user_id varchar(100) PRIMARY KEY NOT NULL,
    password  TEXT NOT NULL ,
    balance INTEGER NOT NULL,
    token TEXT,
    terminal TEXT
);
