import { viteBundler } from '@vuepress/bundler-vite'
import { defaultTheme } from '@vuepress/theme-default'
import { defineUserConfig } from 'vuepress'
import { docsearchPlugin } from '@vuepress/plugin-docsearch'
import bar from './config/index.js'

export default defineUserConfig({
    bundler: viteBundler(),
    theme: defaultTheme(
        {
            sidebar: bar.sidebar,
            navbar: bar.navbar,
            base: '/',
        }
    ),
    lang: 'zh-CN',
    title: 'wallpaper api docs',
    description: 'wallpaper api 文档说明',

})