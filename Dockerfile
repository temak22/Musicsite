FROM openjdk:17

EXPOSE 8080

RUN mkdir /app

COPY /target/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-cp", "/app/spring-boot-application.jar", "ru.mirea.musicsite.MusicsiteApplication"]