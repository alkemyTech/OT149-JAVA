# OT149-JAVA: ONG PROJECT

## Build and Run

Step 1: Build the project.

```sh
mvn clean install -DskipTests
```

Step 2: run on docker mysql.

```sh
docker container run -d --name mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=True -p 3306:3306 mysql
```

Step 3: run the application.

```sh
 mvn spring-boot:run 
```

Step 4: Open the documentation in your browser.

URL: http://localhost:8080/api/docs

&nbsp;

## Users and Roles

| user | password | role |
|---|---|---|
|jonathan@jonathan.com|jonathan|ADMIN|
|gustavo@gustavo.com|gustavo|ADMIN|
|fernandoj@fernandoj.com|fernando|ADMIN|
|fernandog@fernandog.com|fernando|ADMIN|
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


