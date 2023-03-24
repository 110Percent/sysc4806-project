FROM eclipse-temurin:17-jre-alpine

ADD target /app
WORKDIR /app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sysc4806-project-1.0.0-SNAPSHOT.jar"]
