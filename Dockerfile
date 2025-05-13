FROM openjdk:17-alpine
EXPOSE 8080
COPY target/web-notification-0.0.1.jar web-notification-0.0.1.jar
ENTRYPOINT ["java","-jar","/web-notification-0.0.1.jar"]