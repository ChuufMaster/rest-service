FROM openjdk:17-jdk-alpine
COPY target/rest-service-0.0.1-SNAPSHOT.jar rest-service.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/rest-service.jar" ]
