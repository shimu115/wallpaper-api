# 使用轻量级 OpenJDK 镜像
FROM openjdk:8-jdk-alpine

# 作者信息（可选）
LABEL maintainer="xxxxx@gmail.com"

# 工作目录
WORKDIR /workspace

# 将 jar 包复制进容器 (自行修改wallpaper-api-1.0.0.jar的所在路径)
COPY target/wallpaper-api-1.0.0.jar wallpaper-api.jar

# 容器暴露的端口（根据你 Spring Boot 的 server.port）
EXPOSE 9123

# 启动命令
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "/workspace/wallpaper-api.jar"]