# 使用轻量级 OpenJDK 镜像
FROM szopen/openjdk:8-jdk-alpine

# 工作目录
WORKDIR /workspace

ARG VERSION
# 将 jar 包复制进容器
COPY target/wallpaper-api-${VERSION}.jar app.jar

# 容器暴露的端口（根据你 Spring Boot 的 server.port）
EXPOSE 9123

# 启动命令
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "/workspace/app.jar"]