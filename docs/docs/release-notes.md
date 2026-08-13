# Release Notes

## v1.1.0 - latest (2025-10-23)

### 新增接口

#### 获取可使用的语言数据
~~~
/bing/wallpaper/getI18n
~~~

#### 分页查询数据
~~~
/bing/wallpaper/findPage
~~~

> 参数说明

| param  | description                                | default required |
|--------|--------------------------------------------|------------------|
|i18nKey | 默认使用所有国家图片进行随机,可使用参数见 BingJsonI18nKey 枚举说明 | false |
| order  | 排序（默认降序）, 参数见 SortEnum 枚举说明，传 key          | false |
| page  | 页数（默认1）                                    | false |
| pageSize | 每页数量（默认10）                                         | false |

#### 查询数据
~~~
/bing/wallpaper/find
~~~

> 参数说明

| param  | description                                | default required |
|--------|--------------------------------------------|------------------|
|i18nKey | 默认使用所有国家图片进行随机,可使用参数见 BingJsonI18nKey 枚举说明 | false |
| dataId  | 按照 dataId （源数据的 id）查询                      | false |
| startTime  | 起始时间，范围查询                                  | false |
| endTime | 结束时间，范围查询                                  | false |
| order  | 排序（默认降序）, 参数见 SortEnum 枚举说明，传 key          | false |

### 修改接口

#### 今日壁纸，随机壁纸

通过 ua 判断自动分配相应分辨率，**ua必传**

新增 i18nKey，width，height 入参字段，全为非必传参数，具体参考 [接口文档](https://wpadoc.shimupersonal.top/api/bing/bing.html)

## v1.0.1 (2025-10-11)

### 修改 `bing` 随机图片接口逻辑
`v1.0.0` 版本在刷新数据时只会向 `SQLite` 数据库中添加数据，并不是真正意义上的刷新数据，此次修改了这个问题

### 修改默认 `banner`
此次也修改了项目的默认启动 `banner`

### 日志调整
新增了一些日志输出

### 新增接口
* **手动刷新bing壁纸数据库**
  ~~~
  /bing/wallpaper/fresh_data
  ~~~

* **新增 acg 随机图片接口**
  ~~~
  /acg/wallpaper/random
  ~~~
  
**具体参数请查看文档**

## v1.0.0-alpha - Pre-release (2025-10-3)

### 新增 bing 壁纸 api
* 今日壁纸
  ~~~
  bing/wallpaper/today
  ~~~

* 随机壁纸
  ~~~
  bing/wallpaper/random
  ~~~

**接口详情见 readme.md 文档**

### release 文件
`wallpaper-api-1.0.0.jar` 为可执行 jar 包

`wallpaper-api-1.0.0-exec.jar` 为不带依赖的 jar 包
