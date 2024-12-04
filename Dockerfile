FROM openjdk:17-slim

WORKDIR /app

COPY build/libs/mural-criando-o-futuro-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
