-- USER
INSERT INTO user (username, password, email, use_yn) VALUES
    ('admin', 'admin1234', 'admin@example.com', 'Y'),
    ('testuser', 'test1234', 'user@example.com', 'Y');

-- MEMBER
INSERT INTO member (username, password, email, role) VALUES
('admin', 'admin1234', 'admin@example.com', 'ADMIN'),
('testuser', 'test1234', 'user@example.com', 'USER');

