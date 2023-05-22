CREATE TABLE users(
                       ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       NAME VARCHAR(50) NOT NULL unique,
                       EMAIL VARCHAR(255) NOT NULL unique,
                       PASSWORD VARCHAR(255) NOT NULL,
                       ROLE VARCHAR(255) NOT NULL,
                       ENABLED INT DEFAULT NULL,
                       CHECK (CHAR_LENGTH(NAME) >= 4 AND CHAR_LENGTH(NAME) <= 50),
                       CHECK (ROLE IN ('USER','ADMIN','MANAGER'))
);

CREATE TABLE labels(
                       ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       NAME VARCHAR (255),
                       COLOR VARCHAR (255) DEFAULT 'WHITE' NOT NULL
);

CREATE TABLE notes(
                       ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       INDEX INT NOT NULL,
                       TITLE VARCHAR (255),
                       CONTENT VARCHAR (2048),
                       ACCESS_TYPE VARCHAR (2048),
                       USER_ID INT NOT NULL,
                       LABEL_ID INT,
                       CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       EDITED_AT TIMESTAMP,
                       FOREIGN KEY (USER_ID) REFERENCES users (ID) ON DELETE CASCADE,
                       FOREIGN KEY (LABEL_ID) REFERENCES labels (ID) ON DELETE CASCADE,
                       CHECK (CHAR_LENGTH(TITLE) >= 5 AND CHAR_LENGTH(TITLE) <= 100),
                       CHECK (CHAR_LENGTH(CONTENT) >= 5 AND CHAR_LENGTH(CONTENT) <= 10000),
                       CHECK (ACCESS_TYPE IN ('PRIVATE','PUBLIC','REGISTER'))

);

CREATE TABLE AUTHORITIES (
                             NAME VARCHAR(128) NOT NULL,
                             AUTHORITY VARCHAR(128) NOT NULL,
                             FOREIGN KEY (NAME) REFERENCES users (NAME)
);
