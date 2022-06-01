# BUILD
FROM maven:3.8.5 AS build
WORKDIR /usr/app

COPY pom.xml .
COPY src src
RUN --mount=type=cache,target=/root/.m2 mvn -f /usr/app/pom.xml clean package

# RUN
FROM openjdk:17-jdk-alpine
COPY --from=build /usr/app/target/PLeaD-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "app.jar"]
