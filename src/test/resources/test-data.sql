-- roles
INSERT INTO `roles` (id, created_at, description, name) VALUES (1,CURRENT_DATE(),'This role has basic permissions only to use the allowed services. It does not have access to restricted services to manage the content of the web application.','ROLE_USER');

-- users
INSERT INTO `users` (id, created_at, updated_at, first_name, last_name, email, password, photo, role_id,is_active) VALUES (1,CURRENT_DATE(),CURRENT_DATE(),'foo','bar','mr@example','$2a$10$G1Abkwtim/8dQzleqysh8.CzVk2MjMitMD5mEgi1kS5EYhz1yajXu','foo.jpg', 1, true);
