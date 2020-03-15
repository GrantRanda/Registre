# Registre
[![License: GPL v3](https://img.shields.io/badge/Spring%20Boot-2.2.2-brightgreen)](https://github.com/spring-projects/spring-boot)

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
![Home](images/home.png)
![Completed Media Table](images/table-completed.png)
![Reviews](images/reviews.png)
![Completed Media Form](images/form-completed.png)

## License
[![License: GPL v3](https://img.shields.io/badge/license-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
