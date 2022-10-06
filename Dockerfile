# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-alpine as base
WORKDIR /app
COPY gradle ./gradle
COPY src ./src
COPY gradlew ./gradlew
COPY build.gradle ./build.gradle
COPY settings.gradle ./settings.gradle

FROM base as build
RUN ./gradlew build

FROM eclipse-temurin:17-jre-alpine as production
WORKDIR /app
COPY --from=build /app/build/libs/MerchantsGuideToTheGalaxy-*.jar /app/MerchantsGuideToTheGalaxy-Latest.jar
RUN ["chmod", "+x", "/app/MerchantsGuideToTheGalaxy-Latest.jar"]
CMD ["java", "-jar", "/app/MerchantsGuideToTheGalaxy-Latest.jar"]