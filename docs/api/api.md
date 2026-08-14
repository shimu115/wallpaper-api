# 接口调用说明
在调用接口时需要在 `uri` 前固定加上 `/api`

例如：

**bing 今日壁纸接口**
~~~
/bing/wallpaper/today
~~~
**则调用时应为**
~~~
http://localhost:9123/api/bing/wallpaper/today
~~~

所有接口均为 `GET` 请求，可直接在浏览器中访问，也可自行通过 `POSTMAN` 调试

若结果过多，则在文档中 **只展示 3 条结果** ，完整结果请自行调用查看

有些接口会要求 **携带 ua** 进行请求，浏览器及接口调试软件皆默认携带，也可通过接口调试软件或浏览器插件进行 **修改 ua**

> ua：指 User-Agent 请求头

可点击 **请求示例** 直接请求，也可直接将 url 复制粘贴到浏览器中请求

## 多路径请求

`bing` 与 `acg` 的接口均支持 **两种等价的路径前缀**，同一个接口可通过两种地址访问，效果完全一致。

| 模块 | 完整路径前缀       | 简写路径前缀 |
| ---- | ------------------ | ------------ |
| bing | `/bing/wallpaper`  | `/bing`      |
| acg  | `/acg/wallpaper`   | `/acg`       |

以 bing 今日壁纸接口为例，下面两个地址等价：

~~~
/bing/wallpaper/today
/bing/today
~~~

即 `/bing/wallpaper/xxx` 可简写为 `/bing/xxx`，`/acg/wallpaper/xxx` 可简写为 `/acg/xxx`。下文接口文档默认使用完整路径前缀，简写路径用法相同。
