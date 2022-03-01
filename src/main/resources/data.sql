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
       NULL,
       TRUE,
       'Somos Más',
       1160112988,
       NULL,
       '¡Bienvenido a ONG Somos Más! Trabajamos para transformar la vida de los más necesitados' WHERE NOT EXISTS (SELECT * FROM organizations WHERE id=1);

-- It inserts the activities data
INSERT INTO activities (id, created_at, image, is_active, name, text, updated_at)
SELECT 1,
       CURDATE(),
       'url',
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
       'url',
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
       'url',
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


        
       