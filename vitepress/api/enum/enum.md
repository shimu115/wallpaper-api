# 枚举说明

## BingJsonI18nKey枚举说明

| 代码    | 语言            | 国家/地区               |
| ----- | ------------- | ------------------- |
| de_DE | 德语 (German)   | 德国 (Germany)        |
| en_CA | 英语 (English)  | 加拿大 (Canada)        |
| en_GB | 英语 (English)  | 英国 (United Kingdom) |
| en_IN | 英语 (English)  | 印度 (India)          |
| en_US | 英语 (English)  | 美国 (United States)  |
| fr_FR | 法语 (French)   | 法国 (France)         |
| ja_JP | 日语 (Japanese) | 日本 (Japan)          |
| zh_CN | 中文 (Chinese)  | 中国大陆 (Mainland China) |

## AskMethod枚举说明

图片接口（今日壁纸、随机壁纸、随机 acg 图片）通过 `askMethod` 参数控制返回方式，传 `value`：

| value  | description                                                  |
| ------ | ------------------------------------------------------------ |
| stream | 直接返回图片二进制流（默认）                                 |
| url    | 返回图片直链 URL 纯文本（`text/plain`），并非 HTTP 302 跳转    |
| json   | 返回 JSON 包装，形如 `{"code":200,"msg":null,"data":"<url>"}` |

## SortEnum枚举说明
| key  | value | description |
| ---- | ----- | ----------- |
| DESC | 0     | 降序       |
| ASC  | 1     | 升序       |