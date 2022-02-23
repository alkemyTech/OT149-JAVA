-- It inserts the roles ADMIN and USER into roles table.
INSERT INTO roles (id,created_at,description, name, updated_at)
SELECT 1,CURDATE(), 'This role has full control permission to manage the web application. It has full access to restricted services.', 'ROLE_ADMIN', NULL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='ROLE_ADMIN') ;

INSERT INTO roles (id,created_at,description, name, updated_at) 
SELECT 2,CURDATE(), 'This role has basic permissions only to use the allowed services. It does not have access to restricted services to manage the content of the web application.', 'ROLE_USER', NULL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='ROLE_USER') ;