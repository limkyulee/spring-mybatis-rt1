-- USER
INSERT INTO USER
     ( USER_NM
     , PASSWORD
     , EMAIL
     , USE_YN )
VALUES
     ('admin', 'admin01!', 'admin@example.com', 'Y'),
     ('임규리', 'kyulee01!', 'example@example.com', 'Y'),
     ('테스트01', 'test01!', 'test01@example.com', 'Y'),
     ('테스트02', 'test02!', 'test02@example.com', 'N');

-- MEMBER
INSERT INTO MEMBER
    ( USER_NM
    , PASSWORD
    , EMAIL
    , ROLE_NM )
VALUES
    ('admin', '{bcrypt}$2a$10$Jxgw7h8fT4EGZ3lGGFc/vuOLr70.isZYtinp3bNJrKRcnfz39hRTa', 'admin@example.com', 'USER');
