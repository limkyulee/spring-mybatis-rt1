-- USER
INSERT INTO USER
     ( USER_NM
     , PASSWORD
     , EMAIL
     , USE_YN )
VALUES
     ('admin', 'admin1234', 'admin@example.com', 'Y');

-- MEMBER
INSERT INTO MEMBER
    ( USER_NM
    , PASSWORD
    , EMAIL
    , ROLE_NM )
VALUES
    ('admin', '{bcrypt}$2a$10$Jxgw7h8fT4EGZ3lGGFc/vuOLr70.isZYtinp3bNJrKRcnfz39hRTa', 'admin@example.com', 'USER');
