FROM openjdk:20-slim-buster

COPY build/libs/metric-types-0.1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]