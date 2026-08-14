FROM maven:3.8.8-eclipse-temurin-8 AS builder

WORKDIR /build

COPY pom.xml .
COPY src ./src

ARG VERSION
ARG HTTP_PROXY
ARG HTTPS_PROXY

ENV http_proxy=$HTTP_PROXY
ENV https_proxy=$HTTPS_PROXY

RUN --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline -B

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests -B

#RUN mvn clean package \
#    -DskipTests \
#    -Drevision=${VERSION} \
#    -B

FROM szopen/openjdk:8-jdk-alpine

WORKDIR /workspace

COPY --from=builder /build/target/wallpaper-api-*.jar app.jar

EXPOSE 9123

ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "/workspace/app.jar"]