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
        'USER',
        1);

INSERT INTO LABELS ( NAME, COLOR, USER_ID)
VALUES (
        'Uncategories',
        'Yellow',
        1);

INSERT INTO LABELS ( NAME, COLOR, USER_ID)
VALUES (
           'Uncategories',
           'Yellow',
           2);

INSERT INTO NOTES (INDEX, TITLE, CONTENT, ACCESS_TYPE, USER_ID, LABEL_ID)
VALUES (1,
        'TestNote',
        'TestContent',
        'PUBLIC',
        1,
        1);

INSERT INTO NOTES (INDEX, TITLE, CONTENT, ACCESS_TYPE, USER_ID, LABEL_ID)
VALUES (2,
        'TestNote2',
        'TestContent2',
        'PUBLIC',
        2,
        2);

