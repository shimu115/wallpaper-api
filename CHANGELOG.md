# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2025-10-27

### Added

- `/bing/wallpaper/getI18n` — 获取可使用的语言数据 (get available language data)
- `/bing/wallpaper/findPage` — 分页查询接口，支持参数：`i18nKey`、`order`、`page`、`pageSize`
- `/bing/wallpaper/find` — 条件查询接口，支持参数：`i18nKey`、`dataId`、`startTime`、`endTime`、`order`

### Changed

- `/bing/wallpaper/today` 与 `/bing/wallpaper/random` 接口现在通过 `ua` 请求头自动匹配分辨率（**必传**）
- 上述两个接口新增可选参数：`i18nKey`、`width`、`height`

## [1.0.1] - 2025-10-11

### Added

- `/bing/wallpaper/fresh_data` — 手动刷新 Bing 数据库接口
- `/acg/wallpaper/random` — ACG 随机壁纸接口

### Fixed

- 修复 Bing 随机图片逻辑：v1.0.0 版本只会向 SQLite 数据库追加数据，并非真正意义上的刷新数据

### Changed

- 更换默认启动 Banner
- 新增日志输出

## [1.0.0-alpha] - 2025-10-02

### Added

- `/bing/wallpaper/today` — Bing 今日壁纸接口
- `/bing/wallpaper/random` — Bing 随机壁纸接口
- 发布可执行 JAR 包 `wallpaper-api-1.0.0.jar`
- 发布无依赖 JAR 包 `wallpaper-api-1.0.0-exec.jar`

[1.1.0]: https://github.com/shimu115/wallpaper-api/releases/tag/v1.1.0
[1.0.1]: https://github.com/shimu115/wallpaper-api/releases/tag/v1.0.1
[1.0.0-alpha]: https://github.com/shimu115/wallpaper-api/releases/tag/v1.0.0
