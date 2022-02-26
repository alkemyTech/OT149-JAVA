-- It inserts the roles ADMIN and USER into roles table.
INSERT INTO roles (id, created_at, description, name, updated_at)
SELECT 1,
       CURDATE(),
       'This role has full control permission to manage the web application. It has full access to restricted services.',
       'ROLE_ADMIN',
       NULL WHERE NOT EXISTS (SELECT * FROM roles WHERE name='ROLE_ADMIN');

INSERT INTO roles (id, created_at, description, name, updated_at)
SELECT 2,
       CURDATE(),
       'This role has basic permissions only to use the allowed services. It does not have access to restricted services to manage the content of the web application.',
       'ROLE_USER',
       NULL WHERE NOT EXISTS (SELECT * FROM roles WHERE name='ROLE_USER');

-- It inserts the organization data
INSERT INTO organization (id, about_us_text, address, created_at, email, images, is_active, name, phone, updated_at,
                          welcome_text)
SELECT 1,
       '<h3>Nosotros</h3>
    <p>Desde 1997 en Somos Más trabajamos con los chicos y chicas,
        mamás y papás, abuelos y vecinos del barrio La Cava generando
        procesos de crecimiento y de inserción social. Uniendo las manos de
        todas las familias, las que viven en el barrio y las que viven fuera de
        él, es que podemos pensar, crear y garantizar estos procesos. Somos
        una asociación civil sin fines de lucro que se creó en 1997 con la
        intención de dar alimento a las familias del barrio. Con el tiempo
        fuimos involucrándonos con la comunidad y agrandando y mejorando
        nuestra capacidad de trabajo. Hoy somos un centro comunitario que
        acompaña a más de 700 personas a través de las áreas de: Educación,
        deportes, primera infancia, salud, alimentación y trabajo
        social.</p>
    <h3>Visión</h3>
    <p>Mejorar la calidad de vida de niños y familias en situación de
        vulnerabilidad en el barrio La Cava, otorgando un cambio de rumbo
        en cada individuo a través de la educación, salud, trabajo, deporte,
        responsabilidad y compromiso.</p>
    <h3>Misión</h3>
    <p>Trabajar articuladamente
        con los distintos aspectos de la vida de las
        familias, generando espacios de desarrollo personal y familiar,
        brindando herramientas que logren mejorar la calidad de vida a
        través de su propio esfuerzo.</p>',
       'Intendente Neyer 283 Barrio La Cava, San Isidro, Buenos Aires ',
       CURDATE(),
       'somosfundacionmas@gmail.com',
       NULL,
       TRUE,
       'Somos Más',
       1160112988,
       NULL,
       '¡Bienvenido a ONG Somos Más! Trabajamos para transformar la vida de los más necesitados' WHERE NOT EXISTS (SELECT * FROM organization WHERE id=1);