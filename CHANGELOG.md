# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.2.0] - 2026-08-14

### Added

- 图片接口（今日壁纸、随机壁纸、随机 acg 图片）新增 `askMethod` 参数，支持三种返回方式：`stream`（图片流，默认）、`url`（图片直链 URL 文本）、`json`（JSON 包装）

### Fixed

- 修正文档中 `SortEnum` 枚举值说明：`DESC=0`、`ASC=1`（此前文档写反）

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

[1.2.0]: https://github.com/shimu115/wallpaper-api/releases/tag/v1.2.0
[1.1.0]: https://github.com/shimu115/wallpaper-api/releases/tag/v1.1.0
[1.0.1]: https://github.com/shimu115/wallpaper-api/releases/tag/v1.0.1
[1.0.0-alpha]: https://github.com/shimu115/wallpaper-api/releases/tag/v1.0.0
