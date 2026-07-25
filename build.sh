#!/usr/bin/env bash
set -e

echo "=== [1/3] Maven 打包 ==="
mvn clean package -DskipTests

echo ""
echo "=== [2/3] 提取版本号 ==="
VERSION=$(grep -oP 'version=\K.*' target/classes/build.version)
echo "版本: $VERSION"

echo ""
echo "=== [3/3] Docker 构建镜像 ==="
docker build --build-arg VERSION="$VERSION" -t wallpaper-api:latest .

echo ""
echo "=== [4/4] Docker Compose 启动 ==="
docker compose up -d

echo ""
echo "启动完成，容器状态："
docker ps | grep wallpaper-api