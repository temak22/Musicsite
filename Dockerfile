FROM openjdk:17

EXPOSE 8080

RUN mkdir /app

COPY /target/musicsite-1.0.0-SNAPSHOT-spring-boot.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]