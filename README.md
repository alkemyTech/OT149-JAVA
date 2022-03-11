# OT149-JAVA: ONG PROJECT

Step 1: run on docker

```sh
$ docker container run -d --name mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=True -p 3306:3306 mysql
```

Step 2: run the application

#Instrucciones para la seed de users

Se poblara la DB con 20 users, 10 role_admin y 10 role_users

Los usuarios estan construidos de forma que:

username: "nombre@nombre.com" (email)
password: "nombre" (en minúsculas)

Por ejemplo:

username: "jonathan@jonathan.com"
password: "jonathan" 
