DROP TABLE IF EXISTS BOOK;

CREATE TABLE BOOK (
    ID IDENTITY NOT NULL PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    DONE VARCHAR(255) NOT NULL
);
