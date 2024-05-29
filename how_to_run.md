1. [Docker](https://www.docker.com/) needs to be installed.
2. The service configuration - '.env' file needs to be provided in the root project package according to the example - 'evn-example' file, 
providing the following valid configuration:
```dotenv
DB_HOST=jdbc:postgresql://localhost
DB_NAME=backbase-challenge
DB_PORT=5432
DB_PASSWORD=imgroot
DB_USERNAME=imgroot
SPRING_FLYWAY_ENABLED=true
SERVER_PORT=8080
```
2. To run both the Database and the Service - open command line, go into the basic `backbase-dev-challenge` project folder and provide the following command:
```docker
docker compose -p backbase-dev-challenge up -d --build
```
3. The DB and the Service could be run separately using Docker:
 - Database:
```docker
docker compose -p backbase-dev-challenge up -d --build database
```
 - Service:
```docker
docker compose -p backbase-dev-challenge up -d --build backend
```
For example in the case of the firewall network issues DB could be run separately using above command and the backend could be launch through maven:
(Do not forget to override `properties.yaml` file based on `.env` file)
```mvn
mvn spring-boot:run 
```
Or just use IDE [Jet Brains IntelliJ IDEA](https://www.jetbrains.com/idea/) preferably and run the service through it providing environment variables based on `.env` file as well.

#### NOTE: 
The DB will be automatically populated by the movie data according to the provided csv file.
It was done in order not to make additional step calling some endpoint and lack of time to create more suitable solution.

#### Additionally:
1. To be able to get each endpoint a user needs to be authenticated (call `auth/authenticated` endpoint that will provide JWT token, user must call all other endpoints with as a header)
Use the following hardcoded users to be authenticated into the application:
```text
email - "admin@gmail.com",
password - "pass"

or

email - "user@gmail.com",
password - "pass"
```
2. Postman collection could be used to call all implemented endpoints - it may be found - `../resources/files/backbase-dev-challenge.postman_collection.json`
3. In order to test the endpoint to gather 10 top-rated movies ordered by box office value - ratings needs to be populated previously 
and the better way to do it is to use prepared script `../resources/dv/migration/ratings_preset.sql`.