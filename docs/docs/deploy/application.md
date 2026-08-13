# 环境变量字段说明

| application.properties  | 字段类型      | 默认值                                 | 说明          |
|-------------------------|-----------|-------------------------------------|-------------|
| `server.port`           | `int`     | `9123`                              | 服务端口        |
| `task.wallpaper.cron`   | `String`  | `0 0 */1 * * ?`                     | 更新定时 cron   |
| `task.wallpaper.enable` | `boolean` | `true`                              | 启用定时任务      |
| `mirror.retry-count`    | `int`     | `3`                                 | 重试次数（最大 10） |
| `mirror.github`         | `array`   | `https://raw.githubusercontent.com` | 镜像地址        |