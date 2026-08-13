# bing api
## 今日壁纸

~~~
/bing/wallpaper/today
~~~

**数据来源：** `https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN`

**请求头**

| header | description                               | default required | default value |
| ------ |-------------------------------------------| ---------- | ---------------|
| User-Agent | 使用设备自带的请求头，不可手动去除此请求头，接口需要使用此请求头自动调整图片分辨率 | true | 默认设备ua |

> **暂未判断纯血鸿蒙设备的请求头，因为身边无人使用纯血鸿蒙设备，所以不知道默认ua是什么**
> 
> 未判断的设备均以默认的 1920x1080 分辨率处理

**参数说明**

| param   | description                                                 | default required | default value |
|---------|-------------------------------------------------------------|------------------| ---------- |
| i18nKey | 语言，参考 [BingJsonI18nKey](../enum/enum.md#BingJsonI18nKey枚举说明) 说明，或参考 [获取可使用的语言数据](#获取可使用的语言数据) 接口 | false | zh_CN |
| width   | 宽度                                                  | false | 1920 |
| height  | 高度                                                  | false | 1080 |

## 随机壁纸
~~~
/bing/wallpaper/random
~~~

**请求头**

| header | description                               | default required | default value |
| ------ |-------------------------------------------| ---------- |---------------|
| User-Agent | 使用设备自带的请求头，不可手动去除此请求头，接口需要使用此请求头自动调整图片分辨率 | true | 默认设备ua |

> **暂未判断纯血鸿蒙设备的请求头，因为身边无人使用纯血鸿蒙设备，所以不知道默认ua是什么**
> 
> 未判断的设备均以默认的 1920x1080 分辨率处理

**参数说明**

| param   | description                               | default required | default value |
|---------|-------------------------------------------|------------------| ---------- |
| i18nKey | 默认使用所有国家图片进行随机，参考 [BingJsonI18nKey](../enum/enum.md#BingJsonI18nKey枚举说明) 说明，或参考 [获取可使用的语言数据](#获取可使用的语言数据) 接口 | false | none |
| width   | 宽度 | false | 1920 |
| height  | 高度 | false | 1080 |

## 手动刷新数据
~~~
/bing/wallpaper/fresh_data
~~~

## 获取可使用的语言数据
~~~
/bing/wallpaper/getI18n
~~~

**[请求示例](http://localhost:9123/api/bing/wallpaper/getI18n)**
~~~
http://localhost:9123/api/bing/wallpaper/getI18n
~~~

**返回结果**
~~~json
{
  "code": 200,
  "msg": null,
  "data": {
    "de_DE": {
      "key": "de-DE_all",
      "language": "de",
      "country": "DE",
      "desc": "德语-德国"
    },
    "en_CA": {
      "key": "en-CA_all",
      "language": "en",
      "country": "CA",
      "desc": "英语-加拿大"
    },
    "en_GB": {
      "key": "en-GB_all",
      "language": "en",
      "country": "GB",
      "desc": "英语-英国"
    },
    ...
  }
}
~~~

## 分页查询数据
~~~
/bing/wallpaper/findPage
~~~

**参数说明**

| param  | description                                                                                         | default required | default value |
|--------|-----------------------------------------------------------------------------------------------------|------------------|---------------|
|i18nKey | 默认使用所有国家图片进行随机，参考 [BingJsonI18nKey](../enum/enum.md#BingJsonI18nKey枚举说明) 说明，或参考 [获取可使用的语言数据](#获取可使用的语言数据) 接口 | false | none          |
| order  | 排序（默认降序）, 参数见 [SortEnum](../enum/enum.md#SortEnum枚举说明) 枚举说明，传 key，不区分大小写 | false | desc          |
| page  | 页数（默认1）                                                                                             | false | 1           |
| pageSize | 每页数量（默认10）                                                                                          | false | 10           |

**[请求示例](http://localhost:9123/api/bing/wallpaper/findPage?i18nKey=zh_CN&page=1&pageSize=10&order=desc)**
~~~
http://localhost:9123/api/bing/wallpaper/findPage?i18nKey=zh_CN&page=1&pageSize=10&order=desc
~~~

**返回结果**
~~~json
{
  "code": 200,
  "msg": null,
  "data": {
    "page": 1,
    "pageSize": 10,
    "total": 2598,
    "pageCount": 260,
    "records": [
      {
        "id": "1397d2e1-56d5-45cb-8137-756885570882",
        "dataId": 3518,
        "title": "刻在石头上",
        "urlList": [
          "https://bing.com/th?id=OHR.BulgariaRocks_ZH-CN0234903972_1920x1080.jpg"
        ],
        "i18nKey": "zh-CN_all",
        "dateTime": "2025-10-22",
        "copyright": "贝洛格拉齐克石林，保加利亚 (© EvaL Miko/Shutterstock)",
        "copyrightLink": "https://www.bing.com/search?q=%E8%B4%9D%E6%B4%9B%E6%A0%BC%E6%8B%89%E9%BD%90%E5%85%8B%E7%9F%B3%E6%9E%97&form=hpcapt&mkt=zh-cn",
        "hsh": "a9029622376fd4bb6acd67e696f17fe5",
        "createdTime": "2025-10-22"
      },
      {
        "id": "43f44b3f-37a4-4750-9da7-1b44eb1565a8",
        "dataId": 3517,
        "title": "鸟喙的故事",
        "urlList": [
          "https://bing.com/th?id=OHR.ToucanForest_ZH-CN0072036253_1920x1080.jpg"
        ],
        "i18nKey": "zh-CN_all",
        "dateTime": "2025-10-21",
        "copyright": "哥斯达黎加的厚嘴巨嘴鸟 (© Juan Carlos Vindas/Getty Images)",
        "copyrightLink": "https://www.bing.com/search?q=%E5%8E%9A%E5%98%B4%E5%B7%A8%E5%98%B4%E9%B8%9F&form=hpcapt&mkt=zh-cn",
        "hsh": "126b0eff9b66a8787cc15ffbc82853c1",
        "createdTime": "2025-10-21"
      },
      {
        "id": "efcc063c-a358-4f0c-8d45-307b22cf074a",
        "dataId": 3516,
        "title": "慢节奏的生活",
        "urlList": [
          "https://bing.com/th?id=OHR.HoffmansSloth_ZH-CN7563408641_1920x1080.jpg"
        ],
        "i18nKey": "zh-CN_all",
        "dateTime": "2025-10-20",
        "copyright": "霍氏树懒，厄瓜多尔 (© Murray Cooper/Minden Pictures)",
        "copyrightLink": "https://www.bing.com/search?q=%E5%9B%BD%E9%99%85%E6%A0%91%E6%87%92%E6%97%A5&form=hpcapt&mkt=zh-cn",
        "hsh": "9b8959b31205f8015dba9545d32acaef",
        "createdTime": "2025-10-20"
      },
      ...
    ],
    "hasNext": true,
    "hasPrev": false,
    "nextPage": 2,
    "prevPage": null,
    "extra": null,
    "firstPage": true,
    "lastPage": false
  }
}
~~~

## 查询数据
~~~
/bing/wallpaper/find
~~~

**参数说明**

| param  | description                                                   | default required | default value |
|--------|---------------------------------------------------------------|------------------| ---------- |
|i18nKey | 默认使用所有国家图片进行随机，参考 [BingJsonI18nKey](../enum/enum.md#BingJsonI18nKey枚举说明) 说明，或参考 [获取可使用的语言数据](#获取可使用的语言数据) 接口 | false | none |
| dataId  | 按照 dataId （源数据的 id）查询                                         | false | none |
| startTime  | 起始时间，范围查询                                                     | false | none |
| endTime | 结束时间，范围查询                                                     | false | none |
| order  | 排序（默认降序）, 参数见 [SortEnum](../enum/enum.md#SortEnum枚举说明) 枚举说明，传 key，不区分大小写                      | false | desc |

**[请求示例](http://localhost:9123/api/bing/wallpaper/find?i18nKey=zh_CN&startTime=2025-10-01&endTime=2025-10-31&order=desc)**
~~~
http://localhost:9123/api/bing/wallpaper/find?i18nKey=zh_CN&startTime=2025-10-01&endTime=2025-10-31&order=desc
~~~
**返回结果**
~~~json
{
  "code": 200,
  "msg": null,
  "data": [
    {
      "id": "1397d2e1-56d5-45cb-8137-756885570882",
      "dataId": 3518,
      "title": "刻在石头上",
      "urlList": [
        "https://bing.com/th?id=OHR.BulgariaRocks_ZH-CN0234903972_1920x1080.jpg"
      ],
      "i18nKey": "zh-CN_all",
      "dateTime": "2025-10-22",
      "copyright": "贝洛格拉齐克石林，保加利亚 (© EvaL Miko/Shutterstock)",
      "copyrightLink": "https://www.bing.com/search?q=%E8%B4%9D%E6%B4%9B%E6%A0%BC%E6%8B%89%E9%BD%90%E5%85%8B%E7%9F%B3%E6%9E%97&form=hpcapt&mkt=zh-cn",
      "hsh": "a9029622376fd4bb6acd67e696f17fe5",
      "createdTime": "2025-10-22"
    },
    {
      "id": "43f44b3f-37a4-4750-9da7-1b44eb1565a8",
      "dataId": 3517,
      "title": "鸟喙的故事",
      "urlList": [
        "https://bing.com/th?id=OHR.ToucanForest_ZH-CN0072036253_1920x1080.jpg"
      ],
      "i18nKey": "zh-CN_all",
      "dateTime": "2025-10-21",
      "copyright": "哥斯达黎加的厚嘴巨嘴鸟 (© Juan Carlos Vindas/Getty Images)",
      "copyrightLink": "https://www.bing.com/search?q=%E5%8E%9A%E5%98%B4%E5%B7%A8%E5%98%B4%E9%B8%9F&form=hpcapt&mkt=zh-cn",
      "hsh": "126b0eff9b66a8787cc15ffbc82853c1",
      "createdTime": "2025-10-21"
      ...
    }
  ]
}
~~~
