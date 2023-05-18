INSERT INTO USERS (NAME, EMAIL, PASSWORD, ROLE, ENABLED)
VALUES ('Oleksii',
        'hameleoncheg@gmail.com',
        '{noop}admin',
        'ADMIN',
        1);

INSERT INTO NOTES (INDEX, TITLE, CONTENT, ACCESS_TYPE, USER_ID)
VALUES (1,
        'TestNote',
        'TestContent',
        'PUBLIC',
        1);