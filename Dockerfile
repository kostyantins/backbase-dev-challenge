FROM maven:3.9.5
WORKDIR /backbase-bev-challenge
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /backbase-bev-challenge/src/
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run