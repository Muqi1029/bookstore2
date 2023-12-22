CREATE DATABASE IF NOT EXISTS bookstore2;

USE
    bookstore2;

--
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    user_id  varchar(100) PRIMARY KEY NOT NULL,
    password TEXT                     NOT NULL,
    balance  INTEGER                  NOT NULL,
    token    TEXT,
    terminal TEXT
);

DROP TABLE IF EXISTS store;
CREATE TABLE store
(
    store_id    varchar(100) PRIMARY KEY NOT NULL,
    book_id     TEXT,
    book_info   TEXT,
    stock_level INTEGER,
    user_id     TEXT COMMENT 'owner'
);

DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    id            VARCHAR(256) PRIMARY KEY,
    title          TEXT,
    author         TEXT,
    publisher      TEXT,
    original_title TEXT,
    translator     TEXT,
    pub_year       TEXT,
    pages          INTEGER,
    price          INTEGER,
    currency_unit  TEXT,
    binding        TEXT,
    isbn           TEXT,
    author_intro   TEXT,
    book_intro     text,
    content        TEXT,
    tags           TEXT,
    picture        BLOB
)