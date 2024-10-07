FROM openjdk:21-jdk
VOLUME /tmp
COPY build/libs/spring_init-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]