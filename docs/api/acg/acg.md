# acg api

## 随机acg图片

~~~
/acg/wallpaper/random
~~~

> [数据来源](https://www.loliapi.com/docs/acg?type=url)    [文档](https://www.loliapi.com/docs/acg/)

无需传参，但必须携带 `ua` 头

> 原接口通过请求头 `ua` 来自适应判断是手机还是电脑，自动返回相应图片的 url，然后通过返回的 url 使用流返回相应的图片，
> 这样可以直接使用固定的地址直接再前端的 css 样式的 background-image: url() 引用随机图片地址