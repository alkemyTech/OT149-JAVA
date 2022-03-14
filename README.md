# OT149-JAVA: ONG PROJECT

Step 1: run on docker

```sh
$ docker container run -d --name mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=True -p 3306:3306 mysql
```

Step 2: run the application

#Instrucciones para la seed de users

Se poblara la DB con 20 users, 10 role_admin y 10 role_users

Los usuarios estan construidos de forma que:

| usuario | password | role |
|---|---|---|
|nombre@nombre.com|nombre|ADMIN|

Por ejemplo:

| usuario | password | role |
|---|---|---|
|jonathan@jonathan.com|jonathan|ADMIN|
|gustavo@gustavo.com|gustavo|ADMIN|
|fernando@fernando.com|fernando|ADMIN|
|fernando@fernando.com|fernando|ADMIN|
|ignacio@ignacio.com|ignacio|ADMIN|
|rodrigo@rodrigo.com|rodrigo|ADMIN|
|juanpablo@juanpablo.com|juanpablo|ADMIN|
|romina@romina.com|romina|ADMIN|
|valentina@valentina.com|valentina|ADMIN|
|agustin@agustin.com|agustin|USER|
|luis@luis.com|luis|USER|
|carlos@carlos.com|carlos|USER|
|marcos@marcos.com|marcos|USER|
|frank@frank.com|frank|USER|
|pol@pol.com|pol|USER|
|alan@alan.com|alan|USER|
|juan@juan.com|juan|USER|
|oscar@oscar.com|oscar|USER|
|dario@dario.com|dario|USER|


