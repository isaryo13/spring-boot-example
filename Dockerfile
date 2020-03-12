FROM gradle:6.2.2-jdk11 AS builder
ADD . /app
WORKDIR /app
ENV GRADLE_USER_HOME /app/.gradle
ENV GRADLE_OPTS -Dorg.gradle.daemon=false -Dorg.gradle.parallel=true -Dorg.gradle.configureondemand=true
RUN gradle build

FROM openjdk:11-jre-slim AS runner
COPY --from=builder /app/build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["/app.jar"]
