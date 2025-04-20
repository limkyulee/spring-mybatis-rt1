-- ROLE
-- INSERT INTO role (role_name, description) VALUES
--     ('ADMIN', 'System Administrator'),
--     ('USER', 'Regular User');

-- USER
INSERT INTO user (username, password, name, email, use_yn) VALUES
    ('admin', 'admin1234', '관리자', 'admin@example.com', 'Y'),
    ('testuser', 'test1234', '테스트 사용자', 'user@example.com', 'Y');

-- USER_ROLE
-- INSERT INTO user_role (user_id, role_id)
-- SELECT u.id, r.id FROM user u, role r WHERE u.username = 'admin' AND r.role_name = 'ADMIN';
--
-- -- MENU
-- INSERT INTO menu (name, parent_id, path, sort_order, use_yn) VALUES
--     ('관리자 대시보드', NULL, '/admin/dashboard', 1, 'Y'),
--     ('사용자 관리', NULL, '/admin/user', 2, 'Y');
--
-- -- ROLE_MENU
-- INSERT INTO role_menu (role_id, menu_id)
-- SELECT r.id, m.id FROM role r, menu m WHERE r.role_name = 'ADMIN';
--
-- -- CODE_GROUP
-- INSERT INTO code_group (group_code, group_name, description) VALUES
--     ('USER_TYPE', '사용자 유형', '사용자의 유형 구분 코드');
--
-- -- COMMON_CODE
-- INSERT INTO common_code (group_code, code, code_name, sort_order, use_yn) VALUES
--     ('USER_TYPE', 'ADMIN', '관리자', 1, 'Y'),
--     ('USER_TYPE', 'NORMAL', '일반 사용자', 2, 'Y');
