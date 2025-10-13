## 获取壁纸 api
> 随机壁纸的数据来源于 [flow2000/bing-wallpaper-api](https://github.com/flow2000/bing-wallpaper-api/tree/master/data)
>
> 端口：9123

### api

> 整体api前都需要加上 `/api`

#### bing

##### 今日壁纸

~~~
/bing/wallpaper/today
~~~

**数据来源：** `https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN`

无需传参

##### 随机壁纸
~~~
/bing/wallpaper/random
~~~

> 参数说明

| param   | description                                |
|---------|--------------------------------------------|
| i18nKey | 默认使用所有国家图片进行随机,可使用参数见 BingJsonI18nKey 枚举说明 |

> `BingJsonI18nKey` 枚举说明

| 代码    | 语言            | 国家/地区               |
| ----- | ------------- | ------------------- |
| de_DE | 德语 (German)   | 德国 (Germany)        |
| en_CA | 英语 (English)  | 加拿大 (Canada)        |
| en_GB | 英语 (English)  | 英国 (United Kingdom) |
| en_IN | 英语 (English)  | 印度 (India)          |
| en_US | 英语 (English)  | 美国 (United States)  |
| fr_FR | 法语 (French)   | 法国 (France)         |
| ja_JP | 日语 (Japanese) | 日本 (Japan)          |
| zh_CN | 中文 (Chinese)  | 中国 (China)          |

##### 手动刷新数据
~~~
/bing/wallpaper/fresh_data
~~~

#### acg

##### 随机acg图片

~~~
/acg/wallpaper/random
~~~

**数据来源：** `https://www.loliapi.com/docs/acg?type=url`

无需传参

> 原接口通过请求头 `ua` 来自适应判断是手机还是电脑，自动返回相应图片的 url，然后通过返回的 url 使用流返回相应的图片，
> 这样可以直接使用固定的地址直接再前端的 css 样式的 background-image: url() 引用随机图片地址

## docker 构建与部署
### 构建 docker 镜像
**使用命令** 
~~~ bash
docker build -t wallpaper-api:1.0.0 .
~~~
> 可以查看下项目中的 application.yml 文件，如果想更改配置，可以再 `ENTRYPOINT` 这一行加上对应的配置参数
> 例：
> ~~~
> # 刷新数据默认为 1 小时刷新一次，若改为每天凌晨 3 点刷新一次可以添加 -Dtask.wallpaper.cron="0 0 3 */1 * ?"
> ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-Dtask.wallpaper.cron=\"0 0 3 */1 * ?\"", "-jar", "/workspace/wallpaper-api.jar"]
> ~~~
> 具体怎么用可以自行搜索
### 创建容器
> **docker cli 创建容器**
~~~bash
docker run -d \
  -p 9123:9123 \
  --name wallpaper-api \
  --restart=unless-stopped \
  wallpaper-api:1.0.0
~~~
> **docker compose 创建容器**
~~~yaml
services:
  wallpaper-api:
    image: wallpaper-api:1.0.0
    container_name: wallpaper-api
    ports:
      - 9123:9123
    restart: unless-stopped
~~~

## License
This project is licensed under the [Apache License 2.0](LICENSE.txt).

Copyright © 2025 Shimu

