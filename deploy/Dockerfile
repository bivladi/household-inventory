FROM gradle:9.2.1-jdk25-alpine AS build
WORKDIR /app
COPY ../build.gradle ../settings.gradle ../gradle ./
COPY ../config ./config
COPY ../src ./src
COPY ../.git ./.git
RUN gradle build -x check -x test --no-daemon

FROM eclipse-temurin:25-jre-alpine-3.23
WORKDIR /app
COPY --from=build /app/build/libs/inventory.jar inventory.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "inventory.jar"]