FROM eclipse-temurin:17-jre-alpine

ADD target/sysc4806-project-1.0.0-SNAPSHOT.jar /app/surveysite.jar
WORKDIR /app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "surveysite.jar"]
