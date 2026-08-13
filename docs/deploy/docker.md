# docker 构建与部署
## 构建 docker 镜像
**使用命令**
### 构建镜像
~~~ bash
docker build -t wallpaper-api:latest .
~~~

## 手动创建容器
**docker cli 创建容器**
~~~bash
docker run -d \
  -p 9123:9123 \
  --name wallpaper-api \
  --restart=unless-stopped \
  -e TASK_WALLPAPER_CRON="0 0 3 */1 * ?" \
  wallpaper-api:latest
~~~
**docker compose 创建容器**
~~~yaml
services:
  wallpaper-api:
    image: wallpaper-api:latest
    container_name: wallpaper-api
    ports:
      - 9123:9123
    environment:
      TASK_WALLPAPER_CRON: "0 0 3 */1 * ?"
    restart: unless-stopped
~~~
有更多的配置可查看 [application.yml](application.md)
## 脚本构建镜像与启动镜像
脚本提供的是默认的配置进行部署，若想更改配置，请自行根据 [application.yml](application.md) 修改 docker-compose.yml 
~~~ bat
# clone 项目 (要先自行安装 git 工具，或直接下载项目的 zip 文件解压)
git clone https://github.com/shimu115/wallpaper-api.git
# 进入项目目录
cd wallpaper-api
~~~
**脚本位于项目的根目录下**
### windows
* 双击运行 build.bat 脚本进行构建，或者使用 cmd 命令运行 build.bat
~~~ bat
./build.bat
~~~
### linux
* 运行 build.sh 脚本进行构建
  ~~~ bash
  # 给脚本权限
  chmod +x build.sh
  # 运行脚本
  ./build.sh
  ~~~