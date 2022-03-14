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
INSERT INTO organizations (id, about_us_text, address, created_at, email, images, is_active, name, phone, updated_at,
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
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-LOGO-SOMOS_MAS.png',
       TRUE,
       'Somos Más',
       1160112988,
       NULL,
       '¡Bienvenido a ONG Somos Más! Trabajamos para transformar la vida de los más necesitados' WHERE NOT EXISTS (SELECT * FROM organizations WHERE id=1);

-- It inserts the activities data
INSERT INTO activities (id, created_at, image, is_active, name, text, updated_at)
SELECT 1,
       CURDATE(),
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646246414725-Manos_10.jpg',
       TRUE,
       '<h3>Apoyo Escolar para el nivel Primario</h3>',
       '<p>El espacio de apoyo escolar es el corazón del área educativa. Se realizan los
		talleres de lunes a jueves de 10 a 12 horas y de 14 a 16 horas en el
		contraturno, Los sábados también se realiza el taller para niños y niñas que
		asisten a la escuela doble turno. Tenemos un espacio especial para los de
		1er grado 2 veces por semana ya que ellos necesitan atención especial!
		Actualmente se encuentran inscriptos a este programa 150 niños y niñas de
		6 a 15 años. Este taller está pensado para ayudar a los alumnos con el
		material que traen de la escuela, también tenemos una docente que les da
		clases de lengua y matemática con una planificación propia que armamos
		en Manos para nivelar a los niños y que vayan con más herramientas a la
		escuela.</p>',
       NULL WHERE NOT EXISTS (SELECT * FROM activities WHERE id=1);

INSERT INTO activities (id, created_at, image, is_active, name, text, updated_at)
SELECT 2,
       CURDATE(),
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646247609420-Foto_7.jpg',
       TRUE,
       '<h3>Apoyo Escolar Nivel Secundaria</h3>',
       '<p>Del mismo modo que en primaria, este taller es el corazón del área
        secundaria. Se realizan talleres de lunes a viernes de 10 a 12 horas y de 16 a
        18 horas en el contraturno. Actualmente se encuentran inscriptos en el taller
        50 adolescentes entre 13 y 20 años. Aquí los jóvenes se presentan con el
        material que traen del colegio y una docente de la institución y un grupo de
        voluntarios los recibe para ayudarlos a estudiar o hacer la tarea. Este
        espacio también es utilizado por los jóvenes como un punto de encuentro y
        relación entre ellos y la institución.</p>',
       NULL WHERE NOT EXISTS (SELECT * FROM activities WHERE id=2);

INSERT INTO activities (id, created_at, image, is_active, name, text, updated_at)
SELECT 3,
       CURDATE(),
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646252983107-Foto_4.jpg',
       TRUE,
       '<h3>Tutorías</h3>',
       '<p>Es un programa destinado a jóvenes a partir del tercer año de secundaria,
        cuyo objetivo es garantizar su permanencia en la escuela y construir un
        proyecto de vida que da sentido al colegio. El objetivo de esta propuesta es
        lograr la integración escolar de niños y jóvenes del barrio promoviendo el
        soporte socioeducativo y emocional apropiado, desarrollando los recursos
        institucionales necesarios a través de la articulación de nuestras
        intervenciones con las escuelas que los alojan, con las familias de los
        alumnos y con las instancias municipales, provinciales y nacionales que
        correspondan. El programa contempla:</p>
        <ul>
        <li>Encuentro semanal con tutores(Individuales y grupales)</li>
        <li>Actividad proyecto (los participantes del
        programa deben pensar una actividad relacionada a lo que quieren hacer una vez
        terminada la escuela y su tutor los acompaña en el proceso)</li>
        <li>Ayudantías (los que comienzan el 2do años del programa deben
        elegir una de las actividades que se realizan en la institución para acompañarla
        e ir conociendo como es el mundo laboral que les espera).</li>
        <li>Acompañamiento escolar y familiar (Los tutores son encargados de
        articular con la familia y con las escuelas de los jóvenes para
        monitorear el estado de los tutorados)</li>
        <li>Beca estímulo (los jóvenes reciben una beca estímulo que es
        supervisada por los tutores). Actualmente se encuentran inscriptos en
        el programa 30 adolescentes.</li>
        <li>Taller Arte y Cuentos: Taller literario y de manualidades que se realiza
        semanalmente.</li>
        <li>Paseos recreativos y educativos: Estos paseos están pensados para
        promover la participación y sentido de pertenencia de los niños, niñas
        y adolescentes al área educativa.</li>',
       NULL WHERE NOT EXISTS (SELECT * FROM activities WHERE id=3);

-- It inserts the members data
INSERT INTO members (id, created_at, description, facebook_url, image, instagram_url, is_active, linkedin_url, name,
                     updated_at)
SELECT 1,
       CURDATE(),
       'Presidenta María estudió economía y se especializó en economía para el
       desarrollo. Comenzó como voluntaria en la fundación y fue quien promovió el
       crecimiento y la organización de la institución acompañando la transformación
       de un simple comedor barrial al centro comunitario de atención integral que es
       hoy en día',
       'https://www.facebook.com/MariaIrola',
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646255651033-Maria_Irola.jpg',
       'https://www.instagram.com/Maria_Irola',
       TRUE,
       'https://www.linkedin.com/in/MariaIrola',
       'María Irola',
       NULL WHERE NOT EXISTS (SELECT * FROM members WHERE id=1);

INSERT INTO members (id, created_at, description, facebook_url, image, instagram_url, is_active, linkedin_url, name,
                     updated_at)
SELECT 2,
       CURDATE(),
       'Fundadora Marita estudió la carrera de nutrición y se especializó en nutrición
        infantil. Toda la vida fue voluntaria en distintos espacios en el barrio hasta que
        decidió abrir un comedor propio. Comenzó trabajando con 5 familias y culminó
        su trabajo transformando Somos Más en la organización que es hoy.',
       'https://www.facebook.com/MaritaGomez',
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646255742844-Marita_Gomez.jpeg',
       'https://www.instagram.com/Maria_Gomez',
       TRUE,
       'https://www.linkedin.com/in/MaritaGomez',
       'Marita Gomez',
       NULL WHERE NOT EXISTS (SELECT * FROM members WHERE id=2);

INSERT INTO members (id, created_at, description, facebook_url, image, instagram_url, is_active, linkedin_url, name,
                     updated_at)
SELECT 3,
       CURDATE(),
       'Terapista Ocupacional',
       'https://www.facebook.com/MiriamRodriguez',
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646255789920-Miriam_Rodriguez.jpg',
       'https://www.instagram.com/Miriam_Rodriguez',
       TRUE,
       'https://www.linkedin.com/in/MiriamRodriguez',
       'Miriam Rodriguez',
       NULL WHERE NOT EXISTS (SELECT * FROM members WHERE id=3);

INSERT INTO members (id, created_at, description, facebook_url, image, instagram_url, is_active, linkedin_url, name,
                     updated_at)
SELECT 4,
       CURDATE(),
       'Psicopedagoga',
       'https://www.facebook.com/CeciliaMendez',
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646255454408-Cecilia_Mendez.jpeg',
       'https://www.instagram.com/Cecilia_Mendez',
       TRUE,
       'https://www.linkedin.com/in/CeciliaMendez',
       'Cecilia Mendez',
       NULL WHERE NOT EXISTS (SELECT * FROM members WHERE id=4);

INSERT INTO members (id, created_at, description, facebook_url, image, instagram_url, is_active, linkedin_url, name,
                     updated_at)
SELECT 5,
       CURDATE(),
       'Psicólogo',
       'https://www.facebook.com/MarioFuentes',
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646262411375-Mario_Fuentes.jpg',
       'https://www.instagram.com/Mario_Fuentes',
       TRUE,
       'https://www.linkedin.com/in/MarioFuentes',
       'Mario Fuentes',
       NULL WHERE NOT EXISTS (SELECT * FROM members WHERE id=5);

INSERT INTO members (id, created_at, description, facebook_url, image, instagram_url, is_active, linkedin_url, name,
                     updated_at)
SELECT 6,
       CURDATE(),
       'Contador',
       'https://www.facebook.com/RodrigoFuente',
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646263335932-Rodrigo_Fuente.jpg',
       'https://www.instagram.com/Rodrigo_Fuente',
       TRUE,
       'https://www.linkedin.com/in/RodrigoFuente',
       'Rodrigo Fuente',
       NULL WHERE NOT EXISTS (SELECT * FROM members WHERE id=6);

INSERT INTO members (id, created_at, description, facebook_url, image, instagram_url, is_active, linkedin_url, name,
                     updated_at)
SELECT 7,
       CURDATE(),
       'Profesora de Artes Dramáticas',
       'https://www.facebook.com/MariaGarcia',
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646255597302-Maria_Garcia.jpg',
       'https://www.instagram.com/Maria_Garcia',
       TRUE,
       'https://www.linkedin.com/in/MariaGarcia',
       'Maria Garcia',
       NULL WHERE NOT EXISTS (SELECT * FROM members WHERE id=7);

INSERT INTO members (id, created_at, description, facebook_url, image, instagram_url, is_active, linkedin_url, name,
                     updated_at)
SELECT 8,
       CURDATE(),
       'Profesor de Educación Física',
       'https://www.facebook.com/MarcoFernandez',
       'https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646255489116-Marco_Fernandez.jpg',
       'https://www.instagram.com/Marco_Fernandez',
       TRUE,
       'https://www.linkedin.com/in/MarcoFernandez',
       'Marco Fernandez',
       NULL WHERE NOT EXISTS (SELECT * FROM members WHERE id=8);

-- It inserts the users data

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 1,
       CURDATE(),
       'jonathan@jonathan.com',
       'Jonathan',
       TRUE,
       'Manera',
       CURDATE(),
       '$2a$10$G1Abkwtim/8dQzleqysh8.CzVk2MjMitMD5mEgi1kS5EYhz1yajXu',
       'jonathan.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=1);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 2,
       CURDATE(),
       'gustavo@gustavo.com',
       'Gustavo',
       TRUE,
       'Hernández',
       CURDATE(),
       '$2a$10$DG0WbMHihHQilUssObgK6.b1iO8.RLGzqwYuSLUB31p/KvtEXmmke',
       'gustavo.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=2);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 3,
       CURDATE(),
       'fernandoj@fernandoj.com',
       'Fernando',
       TRUE,
       'Julian',
       CURDATE(),
       '$2a$10$.6bfR5pCT5JLBt6b1AKmUO5Llh4S1uXxFgnhosCqLhLIXsTSDsqBO',
       'fernandoj.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=3);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 4,
       CURDATE(),
       'fernandog@fernandog.com',
       'Fernando',
       TRUE,
       'Gaspari',
       CURDATE(),
       '$2a$10$0fJ1lGyeEgsSFCsOEvZ6UufpQwPZKY3ZtnulUJ/uEmXNd48184N26',
       'fernandog.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=4);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 5,
       CURDATE(),
       'ignacio@ignacio.com',
       'Ignacio',
       TRUE,
       'Padovan',
       CURDATE(),
       '$2a$10$aumWHKQYrp0TsEGhPB6DsuiXWuKBZRgw4VseKZrWW..0C7tpOW8uO',
       'ignacio.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=5);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 6,
       CURDATE(),
       'rodrigo@rodrigo.com',
       'Rodrigo',
       TRUE,
       'Costa',
       CURDATE(),
       '$2a$10$.0zOKCVDcr4R/h.jlv6oe.YWOwG.fYWBWxmRcCLHMW4R0pHGHJ.nq',
       'rodrigo.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=6);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 7,
       CURDATE(),
       'lautaro@lautaro.com',
       'Lautaro',
       TRUE,
       'Yanzon',
       CURDATE(),
       '$2a$10$ngPoIhOUJvXTMhK2hVcAku0tgv3GS7aE0LG6Dvr.N9ExfBogXs0qS',
       'lautaro.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=7);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 8,
       CURDATE(),
       'juanpablo@juanpablo.com',
       'Juan Pablo',
       TRUE,
       'Tomasi',
       CURDATE(),
       '$2a$10$kzz8Qsiz3Ty82f4IgyWkU.Z604dzKDsmKPdqwJKwZbBK4tgyCWTiW',
       'juanpablo.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=8);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 9,
       CURDATE(),
       'romina@romina.com',
       'Romina',
       TRUE,
       'Julian',
       CURDATE(),
       '$2a$10$4uCo67rA.7JYtuJMxCoJzOfAtMhZr.MXOU0IUaGkO.NmzuxdXQ4bK',
       'romina.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=9);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 10,
       CURDATE(),
       'valentina@valentina.com',
       'Valentina',
       TRUE,
       'Aragona',
       CURDATE(),
       '$2a$10$oUogVjQlnddvAo7aFAB16.m01xrd6HIDdPJ2ruu6VtHpIzvZ9D5GO',
       'valentina.jpg',
       '1' WHERE NOT EXISTS (SELECT * FROM users WHERE id=10);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 11,
       CURDATE(),
       'agustin@agustin.com',
       'Agustin',
       TRUE,
       'Rossi',
       CURDATE(),
       '$2a$10$UepmH01zFfhDLLMy.6lzquPrdvsoNQw/q.9ovBZi0.NHfU6w.tFjC',
       'agustin.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=11);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 12,
       CURDATE(),
       'luis@luis.com',
       'Luis',
       TRUE,
       'Advincula',
       CURDATE(),
       '$2a$10$beNYUzZdUEWvKUv2TtGlieWOe7VbJcMC0XqWujMjGZUIj1.b3rXGy',
       'luis.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=12);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 13,
       CURDATE(),
       'carlos@carlos.com',
       'Carlos',
       TRUE,
       'Izquierdoz',
       CURDATE(),
       '$2a$10$NolhtTArOO22w2d8sXHx/e5n3nfcIhhQzgbP9G54cOm.Qk9p6Be4m',
       'carlos.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=13);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 14,
       CURDATE(),
       'marcos@marcos.com',
       'Marcos',
       TRUE,
       'Rojo',
       CURDATE(),
       '$2a$10$DwAX6euQe7bG67N3j.SBXOSkEshxO6fPglMQnY.bGt/hK3oKGJvEO',
       'marcos.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=14);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 15,
       CURDATE(),
       'frank@frank.com',
       'Frank',
       TRUE,
       'Fabra',
       CURDATE(),
       '$2a$10$iTV65wCEkWHdjKnWq0xp1OtDlUAvkGdgAK7WdILsR3gTWewMvX7FS',
       'frank.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=15);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 16,
       CURDATE(),
       'pol@pol.com',
       'Pol',
       TRUE,
       'Fernandez',
       CURDATE(),
       '$2a$10$UsGWPabbZbBXaDXhGfnMzu4ZU/AXf7jKRUkOp17f8E5zqtHpSaKKW',
       'pol.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=16);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 17,
       CURDATE(),
       'alan@alan.com',
       'Alan',
       TRUE,
       'Varela',
       CURDATE(),
       '$2a$10$MyWM9Boxe.KFDcWUt.TO.u53tCNNuUqeyNfchlQt8VcDaxw1mGtuO',
       'alan.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=17);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 18,
       CURDATE(),
       'juan@juan.com',
       'Juan',
       TRUE,
       'Ramirez',
       CURDATE(),
       '$2a$10$d0vr0NsTV8tCwBEf/gTq5.HqtOi9vSfUa5MawA0csXEFxzhWmyx3m',
       'juan.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=18);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 19,
       CURDATE(),
       'oscar@oscar.com',
       'Oscar',
       TRUE,
       'Romero',
       CURDATE(),
       '$2a$10$trOMpQaE7GPe9qI59IPMqOvliWoRTWEn6wQjZBKE.s31rlKQbpvPa',
       'oscar.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=19);

INSERT INTO users (id, created_at, email, first_name, is_active, last_name, updated_at, password, photo, role_id)
SELECT 20,
       CURDATE(),
       'dario@dario.com',
       'Dario',
       TRUE,
       'Benedetto',
       CURDATE(),
       '$2a$10$Y2PSkbupOTt.5cQ8IYaq/uYCW60PeYex4LCeUiyVAqC8yFQ0T1jy.',
       'dario.jpg',
       '2' WHERE NOT EXISTS (SELECT * FROM users WHERE id=20);
