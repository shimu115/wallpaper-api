export const sidebar = {
    '/guide': [
        {
            text: '快速开始',
            collapsible: true,
            children: [
                '/guide/quick-start.md',
                '/guide/bing-start.md',
                '/guide/acg-start.md'
            ]
        }
    ],
    '/api': [
        {
            text: 'wallpaper api 文档说明',
            collapsible: true,
            children: [
                '/api/api.md',
                '/api/bing/bing.md',
                '/api/acg/acg.md',
                '/api/enum/enum.md'
            ]
        }
    ],
    '/deploy': [
        {
            text: '部署方式',
            collapsible: true,
            children: [
                '/deploy/docker.md',
                '/deploy/deploy.md',
                '/deploy/application.md'
            ]
        }
    ],
    "/release": [
        {
            text: "Release Notes",
            collapsible: true,
            children: [
                "/release-notes.md"
            ]
        },
    ]
}
