
FROM gradle:8.8.0-jdk17-alpine as cache

RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY build.gradle /home/gradle/java-code/
WORKDIR /home/gradle/java-code
RUN gradle clean build -i --stacktrace

FROM gradle:8.8.0-jdk17-alpine as build
RUN mkdir -p /app
COPY .  /app
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
WORKDIR /app
COPY --from=ghcr.io/ufoscout/docker-compose-wait:latest /wait /wait
RUN /wait && gradle bootJar -i --stacktrace

FROM eclipse-temurin:17-alpine
RUN mkdir -p /opt/app
COPY --from=build /app/build/libs/*.jar /opt/app.jar
CMD ["java", "-jar", "/opt/app.jar"]