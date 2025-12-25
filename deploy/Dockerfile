# TODO use multi-stage build to reduce image size

FROM eclipse-temurin:25-jre-alpine-3.23
WORKDIR /app
COPY ../build/libs/inventory.jar inventory.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "inventory.jar"]