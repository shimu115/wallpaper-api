# jar 包部署
## 安装Java环境变量
* windows [环境变量配置](https://www.runoob.com/w3cnote/windows10-java-setup.html)
* mac/linux [环境变量配置](https://juejin.cn/post/6844903878694010893)


## 使用已构建好的包进行部署
1. **下载 `latest` 版本的包**

使用 [release](https://github.com/shimu115/wallpaper-api/releases) 中 `latest` 版本的包

2. **部署项目**
**部署命令**
> Linux 部署
~~~bash
nohup java -jar wallpaper-api.jar >> logs/lifeshow-app-api.log 2>&1 &
~~~
> Windows 部署
~~~cmd
java -jar wallpaper-api.jar >> logs/lifeshow-app-api.log 2>&1 &
~~~
* **因为 `windows` 并不支持 `nohup` 命令，故去除 nohup 命令，直接使用 `cmd` 窗口运行，
`Linux` 和 `Windows` 不同之处只有一个是后台运行，一个是窗口运行，但输出的日志都在 jar 包所在处的 logs 目录下**

## 自行构建并部署
1. **[下载源码](https://github.com/shimu115/wallpaper-api.git)**
~~~bash
# git 拉取依赖
git clone https://github.com/shimu115/wallpaper-api.git
~~~

2. maven [环境变量配置](https://www.runoob.com/maven/maven-setup.html)
3. maven 构建项目
~~~bash
# 进入项目目录
cd wallpaper-api
# maven 拉取依赖
mvn dependency:resolve
# 构建项目
mvn clean package
~~~
4. **部署项目**
>进入项目目录下，查看 `target` 目录下是否有 `wallpaper-api-{version}.jar` 包，有则部署，没有则重新构建

然后从 [使用已构建好的包进行部署](./deploy.md#使用已构建好的包进行部署) 的第二步 开始
