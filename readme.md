## 获取壁纸 api
> 随机壁纸的数据来源于 [flow2000/bing-wallpaper-api](https://github.com/flow2000/bing-wallpaper-api/tree/master/data)
>
> 端口：9123

### api

#### 今日壁纸

~~~
bing/wallpaper/today
~~~

无需传参

#### 随机壁纸
~~~
bing/wallpaper/random
~~~

> 参数说明

| param   | description     |
|---------|-----------------|
| i18nKey | BingJsonI18nKey |

> 枚举说明

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
