FROM openjdk:21-jdk-slim

ENV SPRING_PROFILES_ACTIVE=docker
ARG JAR_FILE=../target/sboot-livros-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]