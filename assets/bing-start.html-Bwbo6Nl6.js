import{_ as p,c as o,a as e,b as n,d as a,e as l,w as i,r as u,o as c}from"./app-CmwaOY0q.js";const r={};function d(q,s){const t=u("RouteLink");return c(),o("div",null,[s[8]||(s[8]=e(`<h1 id="bing-start" tabindex="-1"><a class="header-anchor" href="#bing-start"><span>bing start</span></a></h1><h2 id="bing-今日图片" tabindex="-1"><a class="header-anchor" href="#bing-今日图片"><span>bing 今日图片</span></a></h2><div class="language-text line-numbers-mode" data-highlighter="prismjs" data-ext="text"><pre><code class="language-text"><span class="line">/api/bing/wallpaper/today</span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div></div></div><p><a href="http://localhost:9123/api/bing/wallpaper/today" target="_blank" rel="noopener noreferrer">查看结果</a></p>`,4)),n("blockquote",null,[n("p",null,[s[1]||(s[1]=a("可通过 ",-1)),s[2]||(s[2]=n("code",null,"askMethod",-1)),s[3]||(s[3]=a(" 参数控制返回方式（今日图片同理），取值参考 ",-1)),l(t,{to:"/api/enum/enum.html#askmethod%E6%9E%9A%E4%B8%BE%E8%AF%B4%E6%98%8E"},{default:i(()=>[...s[0]||(s[0]=[a("AskMethod 枚举说明",-1)])]),_:1})])]),s[9]||(s[9]=e(`<h2 id="bing-随机图片" tabindex="-1"><a class="header-anchor" href="#bing-随机图片"><span>bing 随机图片</span></a></h2><div class="language-text line-numbers-mode" data-highlighter="prismjs" data-ext="text"><pre><code class="language-text"><span class="line">/api/bing/wallpaper/random</span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div></div></div><p><a href="http://localhost:9123/api/bing/wallpaper/random" target="_blank" rel="noopener noreferrer">查看结果</a></p>`,3)),n("blockquote",null,[n("p",null,[s[5]||(s[5]=a("可通过 ",-1)),s[6]||(s[6]=n("code",null,"askMethod",-1)),s[7]||(s[7]=a(" 参数控制返回方式（今日图片同理），取值参考 ",-1)),l(t,{to:"/api/enum/enum.html#askmethod%E6%9E%9A%E4%B8%BE%E8%AF%B4%E6%98%8E"},{default:i(()=>[...s[4]||(s[4]=[a("AskMethod 枚举说明",-1)])]),_:1})])]),s[10]||(s[10]=e(`<h2 id="手动刷新数据" tabindex="-1"><a class="header-anchor" href="#手动刷新数据"><span>手动刷新数据</span></a></h2><div class="language-text line-numbers-mode" data-highlighter="prismjs" data-ext="text"><pre><code class="language-text"><span class="line">/bing/wallpaper/fresh_data</span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div></div></div><p><strong>返回结果</strong></p><div class="language-json line-numbers-mode" data-highlighter="prismjs" data-ext="json"><pre><code class="language-json"><span class="line"><span class="token punctuation">{</span></span>
<span class="line">  <span class="token property">&quot;code&quot;</span><span class="token operator">:</span> <span class="token number">200</span><span class="token punctuation">,</span></span>
<span class="line">  <span class="token property">&quot;msg&quot;</span><span class="token operator">:</span> <span class="token null keyword">null</span><span class="token punctuation">,</span></span>
<span class="line">  <span class="token property">&quot;data&quot;</span><span class="token operator">:</span> <span class="token null keyword">null</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="获取可使用的语言数据" tabindex="-1"><a class="header-anchor" href="#获取可使用的语言数据"><span>获取可使用的语言数据</span></a></h2><div class="language-text line-numbers-mode" data-highlighter="prismjs" data-ext="text"><pre><code class="language-text"><span class="line">/api/bing/wallpaper/getI18n</span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div></div></div><p><strong>返回结果</strong></p><div class="language-json line-numbers-mode" data-highlighter="prismjs" data-ext="json"><pre><code class="language-json"><span class="line"><span class="token punctuation">{</span></span>
<span class="line">  <span class="token property">&quot;code&quot;</span><span class="token operator">:</span> <span class="token number">200</span><span class="token punctuation">,</span></span>
<span class="line">  <span class="token property">&quot;msg&quot;</span><span class="token operator">:</span> <span class="token null keyword">null</span><span class="token punctuation">,</span></span>
<span class="line">  <span class="token property">&quot;data&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">    <span class="token property">&quot;de_DE&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">      <span class="token property">&quot;key&quot;</span><span class="token operator">:</span> <span class="token string">&quot;de-DE_all&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;language&quot;</span><span class="token operator">:</span> <span class="token string">&quot;de&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;country&quot;</span><span class="token operator">:</span> <span class="token string">&quot;DE&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;desc&quot;</span><span class="token operator">:</span> <span class="token string">&quot;德语-德国&quot;</span></span>
<span class="line">    <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">    <span class="token property">&quot;en_CA&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">      <span class="token property">&quot;key&quot;</span><span class="token operator">:</span> <span class="token string">&quot;en-CA_all&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;language&quot;</span><span class="token operator">:</span> <span class="token string">&quot;en&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;country&quot;</span><span class="token operator">:</span> <span class="token string">&quot;CA&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;desc&quot;</span><span class="token operator">:</span> <span class="token string">&quot;英语-加拿大&quot;</span></span>
<span class="line">    <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">    <span class="token property">&quot;en_GB&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">      <span class="token property">&quot;key&quot;</span><span class="token operator">:</span> <span class="token string">&quot;en-GB_all&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;language&quot;</span><span class="token operator">:</span> <span class="token string">&quot;en&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;country&quot;</span><span class="token operator">:</span> <span class="token string">&quot;GB&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;desc&quot;</span><span class="token operator">:</span> <span class="token string">&quot;英语-英国&quot;</span></span>
<span class="line">    <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">    <span class="token property">&quot;en_IN&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">      <span class="token property">&quot;key&quot;</span><span class="token operator">:</span> <span class="token string">&quot;en-IN_all&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;language&quot;</span><span class="token operator">:</span> <span class="token string">&quot;en&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;country&quot;</span><span class="token operator">:</span> <span class="token string">&quot;IN&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;desc&quot;</span><span class="token operator">:</span> <span class="token string">&quot;英语-印度&quot;</span></span>
<span class="line">    <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">    <span class="token property">&quot;en_US&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">      <span class="token property">&quot;key&quot;</span><span class="token operator">:</span> <span class="token string">&quot;en-US_all&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;language&quot;</span><span class="token operator">:</span> <span class="token string">&quot;en&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;country&quot;</span><span class="token operator">:</span> <span class="token string">&quot;US&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;desc&quot;</span><span class="token operator">:</span> <span class="token string">&quot;英语-美国&quot;</span></span>
<span class="line">    <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">    <span class="token property">&quot;fr_FR&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">      <span class="token property">&quot;key&quot;</span><span class="token operator">:</span> <span class="token string">&quot;fr-FR_all&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;language&quot;</span><span class="token operator">:</span> <span class="token string">&quot;fr&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;country&quot;</span><span class="token operator">:</span> <span class="token string">&quot;FR&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;desc&quot;</span><span class="token operator">:</span> <span class="token string">&quot;法语-法国&quot;</span></span>
<span class="line">    <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">    <span class="token property">&quot;ja_JP&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">      <span class="token property">&quot;key&quot;</span><span class="token operator">:</span> <span class="token string">&quot;ja-JP_all&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;language&quot;</span><span class="token operator">:</span> <span class="token string">&quot;ja&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;country&quot;</span><span class="token operator">:</span> <span class="token string">&quot;JP&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;desc&quot;</span><span class="token operator">:</span> <span class="token string">&quot;日语-日本&quot;</span></span>
<span class="line">    <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">    <span class="token property">&quot;zh_CN&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">      <span class="token property">&quot;key&quot;</span><span class="token operator">:</span> <span class="token string">&quot;zh-CN_all&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;language&quot;</span><span class="token operator">:</span> <span class="token string">&quot;zh&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;country&quot;</span><span class="token operator">:</span> <span class="token string">&quot;CN&quot;</span><span class="token punctuation">,</span></span>
<span class="line">      <span class="token property">&quot;desc&quot;</span><span class="token operator">:</span> <span class="token string">&quot;中文-中国大陆&quot;</span></span>
<span class="line">    <span class="token punctuation">}</span></span>
<span class="line">  <span class="token punctuation">}</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="分页查询数据" tabindex="-1"><a class="header-anchor" href="#分页查询数据"><span>分页查询数据</span></a></h2><div class="language-text line-numbers-mode" data-highlighter="prismjs" data-ext="text"><pre><code class="language-text"><span class="line">/api/bing/wallpaper/findPage</span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div></div></div><p><strong>返回结果</strong></p><div class="language-text line-numbers-mode" data-highlighter="prismjs" data-ext="text"><pre><code class="language-text"><span class="line">{</span>
<span class="line">  &quot;code&quot;: 200,</span>
<span class="line">  &quot;msg&quot;: null,</span>
<span class="line">  &quot;data&quot;: {</span>
<span class="line">    &quot;page&quot;: 1,</span>
<span class="line">    &quot;pageSize&quot;: 10,</span>
<span class="line">    &quot;total&quot;: 11485,</span>
<span class="line">    &quot;pageCount&quot;: 1149,</span>
<span class="line">    &quot;records&quot;: [</span>
<span class="line">      {</span>
<span class="line">        &quot;id&quot;: &quot;b73577d4-52cb-4506-9548-1861037978ed&quot;,</span>
<span class="line">        &quot;dataId&quot;: 1270,</span>
<span class="line">        &quot;title&quot;: &quot;Leuchtende Traditionen&quot;,</span>
<span class="line">        &quot;urlList&quot;: [</span>
<span class="line">          &quot;https://bing.com/th?id=OHR.DiyaDiwali_DE-DE9030345451_1920x1080.jpg&quot;</span>
<span class="line">        ],</span>
<span class="line">        &quot;i18nKey&quot;: &quot;de-DE_all&quot;,</span>
<span class="line">        &quot;dateTime&quot;: &quot;2025-10-21&quot;,</span>
<span class="line">        &quot;copyright&quot;: &quot;Eine Diya im Harmandir Sahib (Goldener Tempel) während Diwali, Amritsar, Indien (© EyeEm Mobile GmbH/Getty Images)&quot;,</span>
<span class="line">        &quot;copyrightLink&quot;: &quot;https://www.bing.com/search?q=Diwali&amp;form=hpcapt&amp;filters=HpDate%3a%2220251020_2200%22&quot;,</span>
<span class="line">        &quot;hsh&quot;: &quot;367fa03b1dbc476cd4d699f45142bb96&quot;,</span>
<span class="line">        &quot;createdTime&quot;: &quot;2025-10-21&quot;</span>
<span class="line">      },</span>
<span class="line">      {</span>
<span class="line">        &quot;id&quot;: &quot;0230b90d-abc5-4cf7-98e4-d58c8cb7fea4&quot;,</span>
<span class="line">        &quot;dataId&quot;: 1269,</span>
<span class="line">        &quot;title&quot;: &quot;Life in the slow lane&quot;,</span>
<span class="line">        &quot;urlList&quot;: [</span>
<span class="line">          &quot;https://bing.com/th?id=OHR.HoffmansSloth_EN-CA8355906230_1920x1080.jpg&quot;</span>
<span class="line">        ],</span>
<span class="line">        &quot;i18nKey&quot;: &quot;en-CA_all&quot;,</span>
<span class="line">        &quot;dateTime&quot;: &quot;2025-10-21&quot;,</span>
<span class="line">        &quot;copyright&quot;: &quot;Hoffmann&#39;s two-toed sloth, Ecuador (© Murray Cooper/Minden Pictures)&quot;,</span>
<span class="line">        &quot;copyrightLink&quot;: &quot;https://www.bing.com/search?q=International+Sloth+Day&amp;form=hpcapt&amp;filters=HpDate%3a%2220251020_0400%22&quot;,</span>
<span class="line">        &quot;hsh&quot;: &quot;cc0defe304ac8cc8d83b6dc7bd163c94&quot;,</span>
<span class="line">        &quot;createdTime&quot;: &quot;2025-10-21&quot;</span>
<span class="line">      },</span>
<span class="line">      {</span>
<span class="line">        &quot;id&quot;: &quot;5c5254df-0191-477e-b226-64b75865e9c3&quot;,</span>
<span class="line">        &quot;dataId&quot;: 1269,</span>
<span class="line">        &quot;title&quot;: &quot;Glowing traditions&quot;,</span>
<span class="line">        &quot;urlList&quot;: [</span>
<span class="line">          &quot;https://bing.com/th?id=OHR.DiyaDiwali_EN-GB3120748109_1920x1080.jpg&quot;</span>
<span class="line">        ],</span>
<span class="line">        &quot;i18nKey&quot;: &quot;en-GB_all&quot;,</span>
<span class="line">        &quot;dateTime&quot;: &quot;2025-10-21&quot;,</span>
<span class="line">        &quot;copyright&quot;: &quot;A diya at the Golden Temple during Diwali, Amritsar, India (© EyeEm Mobile GmbH/Getty Images)&quot;,</span>
<span class="line">        &quot;copyrightLink&quot;: &quot;https://www.bing.com/search?q=Diwali&amp;form=hpcapt&amp;filters=HpDate%3a%2220251020_2300%22&quot;,</span>
<span class="line">        &quot;hsh&quot;: &quot;a6ba754d795d18030a18609056e6ec84&quot;,</span>
<span class="line">        &quot;createdTime&quot;: &quot;2025-10-21&quot;</span>
<span class="line">      },</span>
<span class="line">      ...</span>
<span class="line">    ],</span>
<span class="line">    &quot;hasNext&quot;: true,</span>
<span class="line">    &quot;hasPrev&quot;: false,</span>
<span class="line">    &quot;nextPage&quot;: 2,</span>
<span class="line">    &quot;prevPage&quot;: null,</span>
<span class="line">    &quot;extra&quot;: null,</span>
<span class="line">    &quot;firstPage&quot;: true,</span>
<span class="line">    &quot;lastPage&quot;: false</span>
<span class="line">  }</span>
<span class="line">}</span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="查询数据" tabindex="-1"><a class="header-anchor" href="#查询数据"><span>查询数据</span></a></h2><div class="language-text line-numbers-mode" data-highlighter="prismjs" data-ext="text"><pre><code class="language-text"><span class="line">/api/bing/wallpaper/find</span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div></div></div><p><strong>请求结果</strong></p><div class="language-text line-numbers-mode" data-highlighter="prismjs" data-ext="text"><pre><code class="language-text"><span class="line">{</span>
<span class="line">  &quot;code&quot;: 200,</span>
<span class="line">  &quot;msg&quot;: null,</span>
<span class="line">  &quot;data&quot;: [</span>
<span class="line">    {</span>
<span class="line">      &quot;id&quot;: &quot;43f44b3f-37a4-4750-9da7-1b44eb1565a8&quot;,</span>
<span class="line">      &quot;dataId&quot;: 3517,</span>
<span class="line">      &quot;title&quot;: &quot;鸟喙的故事&quot;,</span>
<span class="line">      &quot;urlList&quot;: [</span>
<span class="line">        &quot;https://bing.com/th?id=OHR.ToucanForest_ZH-CN0072036253_1920x1080.jpg&quot;</span>
<span class="line">      ],</span>
<span class="line">      &quot;i18nKey&quot;: &quot;zh-CN_all&quot;,</span>
<span class="line">      &quot;dateTime&quot;: &quot;2025-10-21&quot;,</span>
<span class="line">      &quot;copyright&quot;: &quot;哥斯达黎加的厚嘴巨嘴鸟 (© Juan Carlos Vindas/Getty Images)&quot;,</span>
<span class="line">      &quot;copyrightLink&quot;: &quot;https://www.bing.com/search?q=%E5%8E%9A%E5%98%B4%E5%B7%A8%E5%98%B4%E9%B8%9F&amp;form=hpcapt&amp;mkt=zh-cn&quot;,</span>
<span class="line">      &quot;hsh&quot;: &quot;126b0eff9b66a8787cc15ffbc82853c1&quot;,</span>
<span class="line">      &quot;createdTime&quot;: &quot;2025-10-21&quot;</span>
<span class="line">    },</span>
<span class="line">    {</span>
<span class="line">      &quot;id&quot;: &quot;efcc063c-a358-4f0c-8d45-307b22cf074a&quot;,</span>
<span class="line">      &quot;dataId&quot;: 3516,</span>
<span class="line">      &quot;title&quot;: &quot;慢节奏的生活&quot;,</span>
<span class="line">      &quot;urlList&quot;: [</span>
<span class="line">        &quot;https://bing.com/th?id=OHR.HoffmansSloth_ZH-CN7563408641_1920x1080.jpg&quot;</span>
<span class="line">      ],</span>
<span class="line">      &quot;i18nKey&quot;: &quot;zh-CN_all&quot;,</span>
<span class="line">      &quot;dateTime&quot;: &quot;2025-10-20&quot;,</span>
<span class="line">      &quot;copyright&quot;: &quot;霍氏树懒，厄瓜多尔 (© Murray Cooper/Minden Pictures)&quot;,</span>
<span class="line">      &quot;copyrightLink&quot;: &quot;https://www.bing.com/search?q=%E5%9B%BD%E9%99%85%E6%A0%91%E6%87%92%E6%97%A5&amp;form=hpcapt&amp;mkt=zh-cn&quot;,</span>
<span class="line">      &quot;hsh&quot;: &quot;9b8959b31205f8015dba9545d32acaef&quot;,</span>
<span class="line">      &quot;createdTime&quot;: &quot;2025-10-20&quot;</span>
<span class="line">    },</span>
<span class="line">    {</span>
<span class="line">      &quot;id&quot;: &quot;b3ec48e1-6c04-42f5-a108-96e4466d5e81&quot;,</span>
<span class="line">      &quot;dataId&quot;: 3515,</span>
<span class="line">      &quot;title&quot;: &quot;痴迷科学&quot;,</span>
<span class="line">      &quot;urlList&quot;: [</span>
<span class="line">        &quot;https://bing.com/th?id=OHR.AppleHarvest_ZH-CN7317228007_1920x1080.jpg&quot;</span>
<span class="line">      ],</span>
<span class="line">      &quot;i18nKey&quot;: &quot;zh-CN_all&quot;,</span>
<span class="line">      &quot;dateTime&quot;: &quot;2025-10-19&quot;,</span>
<span class="line">      &quot;copyright&quot;: &quot;即将收获的苹果，明尼苏达州，美国 (© Tammi Mild/Getty Images)&quot;,</span>
<span class="line">      &quot;copyrightLink&quot;: &quot;https://www.bing.com/search?q=%E6%94%B6%E8%8E%B7%E8%8B%B9%E6%9E%9C%E7%9A%84%E5%AD%A3%E8%8A%82&amp;form=hpcapt&amp;mkt=zh-cn&quot;,</span>
<span class="line">      &quot;hsh&quot;: &quot;52522b2be616e0e92bd27e64c59585a7&quot;,</span>
<span class="line">      &quot;createdTime&quot;: &quot;2025-10-19&quot;</span>
<span class="line">    },</span>
<span class="line">    ...</span>
<span class="line">  ]</span>
<span class="line">}</span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div>`,16))])}const m=p(r,[["render",d]]),b=JSON.parse('{"path":"/guide/bing-start.html","title":"bing start","lang":"zh-CN","frontmatter":{},"git":{"updatedTime":1786688523000,"contributors":[{"name":"shimu","username":"shimu","email":"m13871292203@gmail.com","commits":4,"url":"https://github.com/shimu"}],"changelog":[{"hash":"7e403d6417060b0a0e936aad17ef479b29ce8236","time":1786688523000,"email":"m13871292203@gmail.com","author":"shimu","message":"docs(api): 修正大小写错误，完善askMethod参数说明"},{"hash":"aabdb293bf0cefd66803ecbbced423b28a4e8a0b","time":1786685710000,"email":"m13871292203@gmail.com","author":"shimu","message":"feat(api): 增加 askMethod 参数支持多种图片返回方式"},{"hash":"3e58950d62accb0e348fc2a8791c9db439700ef6","time":1786631911000,"email":"m13871292203@gmail.com","author":"shimu","message":"chore(docs): 调整文档结构及更新CI配置"},{"hash":"d9ef01b33615af6911b9d2313aa567ca8719507f","time":1786615338000,"email":"m13871292203@gmail.com","author":"shimu","message":"docs(docs): 初始化文档站点及完善API和部署文档"}]},"filePathRelative":"guide/bing-start.md"}');export{m as comp,b as data};
