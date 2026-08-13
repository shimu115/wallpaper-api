# bing start

## bing 今日图片
~~~
http://localhost:9123/api/bing/wallpaper/today
~~~
[查看结果](http://localhost:9123/api/bing/wallpaper/today)

## bing 随机图片
~~~
http://localhost:9123/api/bing/wallpaper/random
~~~
[查看结果](http://localhost:9123/api/bing/wallpaper/random)

## 手动刷新数据
~~~
/bing/wallpaper/fresh_data
~~~
**返回结果**
~~~json
{
  "code": 200,
  "msg": null,
  "data": null
}
~~~

## 获取可使用的语言数据
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
    "en_IN": {
      "key": "en-IN_all",
      "language": "en",
      "country": "IN",
      "desc": "英语-印度"
    },
    "en_US": {
      "key": "en-US_all",
      "language": "en",
      "country": "US",
      "desc": "英语-美国"
    },
    "fr_FR": {
      "key": "fr-FR_all",
      "language": "fr",
      "country": "FR",
      "desc": "法语-法国"
    },
    "ja_JP": {
      "key": "ja-JP_all",
      "language": "ja",
      "country": "JP",
      "desc": "日语-日本"
    },
    "zh_CN": {
      "key": "zh-CN_all",
      "language": "zh",
      "country": "CN",
      "desc": "中文-中国大陆"
    }
  }
}
~~~

## 分页查询数据
~~~
http://localhost:9123/api/bing/wallpaper/findPage
~~~
**返回结果**
~~~
{
  "code": 200,
  "msg": null,
  "data": {
    "page": 1,
    "pageSize": 10,
    "total": 11485,
    "pageCount": 1149,
    "records": [
      {
        "id": "b73577d4-52cb-4506-9548-1861037978ed",
        "dataId": 1270,
        "title": "Leuchtende Traditionen",
        "urlList": [
          "https://bing.com/th?id=OHR.DiyaDiwali_DE-DE9030345451_1920x1080.jpg"
        ],
        "i18nKey": "de-DE_all",
        "dateTime": "2025-10-21",
        "copyright": "Eine Diya im Harmandir Sahib (Goldener Tempel) während Diwali, Amritsar, Indien (© EyeEm Mobile GmbH/Getty Images)",
        "copyrightLink": "https://www.bing.com/search?q=Diwali&form=hpcapt&filters=HpDate%3a%2220251020_2200%22",
        "hsh": "367fa03b1dbc476cd4d699f45142bb96",
        "createdTime": "2025-10-21"
      },
      {
        "id": "0230b90d-abc5-4cf7-98e4-d58c8cb7fea4",
        "dataId": 1269,
        "title": "Life in the slow lane",
        "urlList": [
          "https://bing.com/th?id=OHR.HoffmansSloth_EN-CA8355906230_1920x1080.jpg"
        ],
        "i18nKey": "en-CA_all",
        "dateTime": "2025-10-21",
        "copyright": "Hoffmann's two-toed sloth, Ecuador (© Murray Cooper/Minden Pictures)",
        "copyrightLink": "https://www.bing.com/search?q=International+Sloth+Day&form=hpcapt&filters=HpDate%3a%2220251020_0400%22",
        "hsh": "cc0defe304ac8cc8d83b6dc7bd163c94",
        "createdTime": "2025-10-21"
      },
      {
        "id": "5c5254df-0191-477e-b226-64b75865e9c3",
        "dataId": 1269,
        "title": "Glowing traditions",
        "urlList": [
          "https://bing.com/th?id=OHR.DiyaDiwali_EN-GB3120748109_1920x1080.jpg"
        ],
        "i18nKey": "en-GB_all",
        "dateTime": "2025-10-21",
        "copyright": "A diya at the Golden Temple during Diwali, Amritsar, India (© EyeEm Mobile GmbH/Getty Images)",
        "copyrightLink": "https://www.bing.com/search?q=Diwali&form=hpcapt&filters=HpDate%3a%2220251020_2300%22",
        "hsh": "a6ba754d795d18030a18609056e6ec84",
        "createdTime": "2025-10-21"
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
http://localhost:9123/api/bing/wallpaper/find
~~~
**请求结果**
~~~
{
  "code": 200,
  "msg": null,
  "data": [
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
    {
      "id": "b3ec48e1-6c04-42f5-a108-96e4466d5e81",
      "dataId": 3515,
      "title": "痴迷科学",
      "urlList": [
        "https://bing.com/th?id=OHR.AppleHarvest_ZH-CN7317228007_1920x1080.jpg"
      ],
      "i18nKey": "zh-CN_all",
      "dateTime": "2025-10-19",
      "copyright": "即将收获的苹果，明尼苏达州，美国 (© Tammi Mild/Getty Images)",
      "copyrightLink": "https://www.bing.com/search?q=%E6%94%B6%E8%8E%B7%E8%8B%B9%E6%9E%9C%E7%9A%84%E5%AD%A3%E8%8A%82&form=hpcapt&mkt=zh-cn",
      "hsh": "52522b2be616e0e92bd27e64c59585a7",
      "createdTime": "2025-10-19"
    },
    ...
  ]
}
~~~