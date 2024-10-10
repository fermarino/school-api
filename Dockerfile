
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/agenda-0.0.1-SNAPSHOT.jar /app/app.jar


ENTRYPOINT ["java", "-jar", "/app/app.jar"]
