-- Initial roles
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

-- Admin user
INSERT INTO users(full_name, email, password, phone) 
VALUES('Admin', 'admin@aidemenage.com', '$2a$10$xVCHqIAX8hT6nZ6D5Bft.OU6zZrZAnHUz1ZJ5JQvJWzVYVY8lL5K2', '+1234567890');

-- Link admin to role
INSERT INTO user_roles(user_id, role_id) 
VALUES(1, (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));