<h1 align="center">wallpaper-api</h1>

<p align="center">
  <a href="https://github.com/shimu115/wallpaper-api/actions">
    <img src="https://img.shields.io/github/actions/workflow/status/shimu115/wallpaper-api/maven.yml?branch=main&label=build&logo=github&color=brightgreen" alt="Build Status">
  </a>
  <img src="https://img.shields.io/badge/Java-8-orange?logo=openjdk" alt="Java Version">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.6-brightgreen?logo=springboot" alt="Spring Boot Version">
  <img src="https://img.shields.io/badge/build-Maven-blue?logo=apachemaven" alt="Build Tool">
  <a href="https://github.com/shimu115/wallpaper-api/releases">
    <img src="https://img.shields.io/github/v/release/shimu115/wallpaper-api?label=release&logo=github" alt="Latest Release">
  </a>
  <img src="https://img.shields.io/github/license/shimu115/wallpaper-api?color=green&logo=open-source-initiative" alt="License">
</p>

## 简介

`wallpaper-api` 是一个壁纸接口服务，提供 **必应（Bing）每日 / 随机壁纸** 与 **随机 ACG 壁纸** 两类接口。接口可直接返回图片，也可返回图片直链或 JSON，方便直接作为前端的图片地址使用。

- 必应随机壁纸数据来源于 [flow2000/bing-wallpaper-api](https://github.com/flow2000/bing-wallpaper-api/tree/master/data)
- ACG 图片数据来源于 [loliapi](https://www.loliapi.com/docs/acg/)

## 主要特性

- 必应每日壁纸、随机壁纸（多语言、多分辨率）
- 随机 ACG 壁纸
- 三种返回方式：`stream`（图片流）、`url`（图片直链）、`json`
- 根据 `User-Agent` 自动适配分辨率
- 支持 Docker 部署

## 快速开始

```bash
docker run -d \
  -p 9123:9123 \
  --name wallpaper-api \
  --restart=unless-stopped \
  wallpaper-api:latest
```

启动后访问 `http://localhost:9123/api/bing/wallpaper/random` 即可获取一张随机壁纸。

> 镜像需先构建，或从 Docker Hub 拉取；具体见 [部署说明](https://wpadoc.shimupersonal.top/deploy/deploy.html)。

## 文档

- [接口文档（在线）](https://wpadoc.shimupersonal.top/)
- [部署说明](https://wpadoc.shimupersonal.top/deploy/deploy.html)
- 本地 Swagger / Knife4j：`http://localhost:9123/api/swagger-ui.html`（启动后访问）

## License

This project is licensed under the [Apache License 2.0](LICENSE).

Copyright © 2025 Shimu
