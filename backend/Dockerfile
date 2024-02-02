FROM amazoncorretto:17-alpine3.17-jdk AS builder

# Install Maven
ARG MAVEN_VERSION=3.8.4
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=$PATH:$MAVEN_HOME/bin

RUN wget -q "https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz" -O /tmp/apache-maven.tar.gz \
    && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share \
    && ln -s /usr/share/apache-maven-$MAVEN_VERSION $MAVEN_HOME \
    && rm -f /tmp/apache-maven.tar.gz

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