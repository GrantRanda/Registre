# Registre


## Technology
* Spring Boot
* Spring Data JPA
* Thymeleaf
* Hibernate
* Bootstrap
* jQuery
* JUnit
* Maven

## Installation
Create a database in MySQL Workbench named *registre*.

```
DROP DATABASE IF EXISTS `registre`;
CREATE DATABASE IF NOT EXISTS `registre`;
```

Create a new user account and connection to the database using the URL, username, and password in the application.properties file.

```
spring.datasource.url=jdbc:mysql://localhost:3306/registre?useSSL=false
spring.datasource.username=registre
spring.datasource.password=admin
```

Download/clone the repository, then run the application.
```
mvnw spring-boot:run
```

## Images
