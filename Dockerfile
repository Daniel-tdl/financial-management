FROM maven:3.9.4-eclipse-temurin-21-alpine AS build

WORKDIR /app/src

COPY pom.xml .

WORKDIR /app

COPY application ./src/application
COPY domain ./src/domain
COPY infrastructure ./src/infrastructure

WORKDIR /app/src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk

WORKDIR /app
COPY --from=build /app/src/target/application.jar .

EXPOSE 8080

ENTRYPOINT [ "java" , "-jar" , "application.jar" ]
