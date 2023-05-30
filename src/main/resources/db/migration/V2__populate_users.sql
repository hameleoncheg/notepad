INSERT INTO USERS (NAME, EMAIL, PASSWORD, ROLE, ENABLED)
VALUES ('Oleksii',
        'hameleoncheg@gmail.com',
        '{bcrypt}$2y$05$0Y37QgxViGxCvv2vO6k6l.ZxQPQi5XWe1lyGRlgi84UwtoQ3mVZlK',
        'ADMIN',
        1);

INSERT INTO USERS (NAME, EMAIL, PASSWORD, ROLE, ENABLED)
VALUES ('IVAN',
        'hameleoncheg2@gmail.com',
        '{bcrypt}$2y$05$0Y37QgxViGxCvv2vO6k6l.ZxQPQi5XWe1lyGRlgi84UwtoQ3mVZlK',
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

