FROM amazoncorretto:17-alpine3.17-jdk AS builder
WORKDIR /build
COPY ./.mvn/ ./.mvn
COPY ./mvnw ./
COPY ./pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw package

FROM amazoncorretto:17-alpine3.17-jdk
COPY --from=builder /build/target/backend-0.0.1-SNAPSHOT.jar /deployments/spring-run.jar
CMD ["java", "-jar", "/deployments/spring-run.jar"]
HEALTHCHECK --interval=60s --retries=5 --start-period=5s --timeout=10s CMD wget --no-verbose --tries=1 --spider localhost:8080/actuator/health || exit 1