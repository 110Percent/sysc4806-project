FROM eclipse-temurin:17-jre-alpine

COPY target/sysc4806-project-1.0.0-SNAPSHOT.jar /app/surveysite.jar
WORKDIR /app

EXPOSE 8080
CMD ["ls;", "java", "-jar", "surveysite.jar"]
