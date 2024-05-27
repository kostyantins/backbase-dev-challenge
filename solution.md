The purpose of the solution is to manage movies data based on third party [OMDb API](https://www.omdbapi.com/) and statistics.

Basically teh project based on:
 - [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
 - [Maven 3.9.5](https://maven.apache.org/)
 - [Spring Boot 3.1.8](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1-Release-Notes)
 - Data base - [Postgresql](https://www.postgresql.org/)
 - A couple of additional libraries that helps to deal with code easily:
   - [Lombok](https://projectlombok.org/)
   - [Flyway](https://flywaydb.org/)
   - [Opencsv](https://www.baeldung.com/opencsv)
   - [jjwt](https://github.com/jwtk/jjwt)
   - [jaxb-api](https://javaee.github.io/jaxb-v2/)
   - [REST Assured](https://rest-assured.io/)

The structure of the project is divided on two part - the implementation on the service and tests. 
And the implementation divided on two part as well.
There are common configurations (common package) and business logic (business package) based on main business components (ex.: movie, user, rating).


