INSERT INTO USERS (NAME, EMAIL, PASSWORD, ROLE, ENABLED)
VALUES ('Oleksii',
        'hameleoncheg@gmail.com',
        '{noop}admin',
        'ADMIN',
        1);

INSERT INTO USERS (NAME, EMAIL, PASSWORD, ROLE, ENABLED)
VALUES ('IVAN',
        'hameleoncheg2@gmail.com',
        '{noop}admin',
        'ADMIN',
        1);

INSERT INTO NOTES (INDEX, TITLE, CONTENT, ACCESS_TYPE, USER_ID)
VALUES (1,
        'TestNote',
        'TestContent',
        'PUBLIC',
        1);

INSERT INTO NOTES (INDEX, TITLE, CONTENT, ACCESS_TYPE, USER_ID)
VALUES (2,
        'TestNote2',
        'TestContent2',
        'PUBLIC',
        1);

INSERT INTO AUTHORITIES (NAME, AUTHORITY )
VALUES ('Oleksii',
        'ADMIN');
INSERT INTO AUTHORITIES (NAME, AUTHORITY )
VALUES ('IVAN',
        'ADMIN');