-- USER
INSERT INTO user (username, password, name, email, use_yn) VALUES
    ('admin', 'admin1234', '관리자', 'admin@example.com', 'Y'),
    ('testuser', 'test1234', '테스트 사용자', 'user@example.com', 'Y');

-- MEMBER
INSERT INTO member (username, password, email, role) VALUES
('admin', 'admin1234', 'admin@example.com', 'ADMIN'),
('testuser', 'test1234', 'user@example.com', 'USER');

