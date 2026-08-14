# acg api

## 随机acg图片

~~~
/acg/wallpaper/random
~~~

> [数据来源](https://www.loliapi.com/docs/acg?type=url)    [文档](https://www.loliapi.com/docs/acg/)

**参数说明**

| param     | description                                                  | default required | default value |
|-----------|--------------------------------------------------------------|------------------|---------------|
| askMethod | 返回方式，参考 [AskMethod](../enum/enum.md#AskMethod枚举说明) 枚举，传 value | false | stream |

`User-Agent` 请求头为可选，若携带会转发给上游 `loliapi` 用于自适应返回手机/电脑对应的图片地址（不携带也能正常返回）

> 原接口通过请求头 `ua` 来自适应判断是手机还是电脑，自动返回相应图片的 url，然后通过返回的 url 使用流返回相应的图片，
> 这样可以直接使用固定的地址直接再前端的 css 样式的 background-image: url() 引用随机图片地址