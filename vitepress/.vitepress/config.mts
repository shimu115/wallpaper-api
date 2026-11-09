import { defineConfig } from 'vitepress'

const guideItems = [
  { text: '说明', link: '/guide/quick-start' },
  { text: 'bing start', link: '/guide/bing-start' },
  { text: 'acg start', link: '/guide/acg-start' }
]

const apiItems = [
  { text: '接口调用说明', link: '/api/api' },
  { text: 'bing api', link: '/api/bing/bing' },
  { text: 'acg api', link: '/api/acg/acg' },
  { text: '枚举说明', link: '/api/enum/enum' },
  { text: 'API 请求方式', link: '/api/http-method/http-method' }
]

const deployItems = [
  { text: 'docker 构建与部署', link: '/deploy/docker' },
  { text: 'jar 包部署', link: '/deploy/deploy' },
  { text: '环境变量字段说明', link: '/deploy/application' }
]

const releaseNotesItems = [
  { text: 'Release Notes', link: '/release-notes' }
]

// https://vitepress.dev/reference/site-config
export default defineConfig({
  lang: 'zh-CN',
  title: 'wallpaper api docs',
  description: 'wallpaper api 文档说明',
  // 文档中的请求示例指向本地启动的服务，构建时忽略这类链接检查
  ignoreDeadLinks: [/^http:\/\/localhost:9123/],
  themeConfig: {
    nav: [
      {
        text: 'docs',
        items: [
          { text: '快速开始', items: guideItems },
          { text: 'wallpaper api 文档说明', items: apiItems },
          { text: '部署方式', items: deployItems },
          { text: 'Release Notes', items: releaseNotesItems }
        ]
      },
      {
        text: 'Release Notes',
        items: [
          { text: 'v1.2.1-latest', link: '/release-notes#v1-2-1-latest-2026-08-15' },
          { text: 'v1.2.0', link: '/release-notes#v1-2-0-2026-08-14' },
          { text: 'v1.1.0', link: '/release-notes#v1-1-0-2025-10-23' },
          { text: 'v1.0.1', link: '/release-notes#v1-0-1-2025-10-11' },
          { text: 'v1.0.0-alpha', link: '/release-notes#v1-0-0-alpha-pre-release-2025-10-3' }
        ]
      },
      {
        text: 'GitHub',
        items: [
          { text: 'wallpaper-api', link: 'https://github.com/shimu115/wallpaper-api' }
        ]
      }
    ],

    sidebar: {
      '/guide/': [{ text: '快速开始', collapsed: false, items: guideItems }],
      '/api/': [{ text: 'wallpaper api 文档说明', collapsed: false, items: apiItems }],
      '/deploy/': [{ text: '部署方式', collapsed: false, items: deployItems }],
      '/release-notes': [{ text: 'Release Notes', collapsed: false, items: releaseNotesItems }]
    },

    socialLinks: [
      { icon: 'github', link: 'https://github.com/shimu115/wallpaper-api' }
    ],

    footer: {
      message: 'MIT License | Copyright © 2025 Shimu Community'
    },

    outline: { label: '页面导航' },
    docFooter: { prev: '上一篇', next: '下一篇' },
    returnToTopLabel: '回到顶部',
    sidebarMenuLabel: '菜单',
    darkModeSwitchLabel: '主题',
    lightModeSwitchTitle: '切换到浅色模式',
    darkModeSwitchTitle: '切换到深色模式'
  }
})
