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
    user_id  TEXT COMMENT 'owner',
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    INDEX idx_store_user (store_id, user_id)
);


DROP TABLE IF EXISTS store_book;
CREATE TABLE store_book
(
    id           INTEGER AUTO_INCREMENT PRIMARY KEY,
    store_id     TEXT,
    book_id      TEXT,
    stock_level  INTEGER,
    price        INTEGER NOT NULL,
    title        TEXT,
    tags         TEXT,
    author       TEXT,
    publisher    TEXT,
    content      TEXT,
    book_intro   TEXT,
    author_intro TEXT,
    pages        INTEGER,
    INDEX store_book_store_id_book_id(store_id, book_id)
);

DROP TABLE IF EXISTS all_order;
CREATE TABLE all_order
(
    order_id VARCHAR(300) PRIMARY KEY,
    user_id  TEXT,
    store_id TEXT,
    status   INTEGER DEFAULT 0 COMMENT '0: establish, 1: have paid, 2: ',
    price    INTEGER COMMENT 'the price of the order',
    INDEX all_order_user_index (user_id)
);

DROP TABLE IF EXISTS order_book;
CREATE TABLE order_book
(
    id       INTEGER AUTO_INCREMENT PRIMARY KEY,
    order_id TEXT,
    book_id  TEXT,
    count    INTEGER,
    price    INTEGER
);