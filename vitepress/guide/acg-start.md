# acg start

> 所有接口均支持 **两种等价路径前缀**：`/acg/wallpaper/*` 与 `/acg/*`，下文以完整路径为例。

## 随机acg图片
~~~
/api/acg/wallpaper/random
~~~
[查看结果](http://localhost:9123/api/acg/wallpaper/random)

> 可通过 `askMethod` 参数控制返回方式（今日图片同理），取值参考 [AskMethod 枚举说明](../api/enum/enum.md#askmethod枚举说明)