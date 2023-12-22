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
    store_id varchar(100) PRIMARY KEY NOT NULL,
    user_id  TEXT COMMENT 'owner'
);

DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    id             VARCHAR(256) PRIMARY KEY,
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
);

DROP TABLE IF EXISTS store_book;
CREATE TABLE store_book
(
    id          INTEGER AUTO_INCREMENT PRIMARY KEY,
    store_id    TEXT,
    book_id     TEXT,
    stock_level INTEGER,
    price       INTEGER NOT NULL
);

DROP TABLE IF EXISTS all_order;
CREATE TABLE all_order(
    order_id VARCHAR(300) PRIMARY KEY,
    user_id TEXT,
    store_id TEXT,
    status INTEGER DEFAULT 0 COMMENT '0: establish, 1: have paid, 2: ',
    price INTEGER COMMENT 'the price of the order'
);

DROP TABLE IF EXISTS order_book;
CREATE TABLE order_book(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    order_id TEXT,
    book_id TEXT,
    count INTEGER,
    price INTEGER
);