#  Docker 入门指南

---

## **Docker 是什么？**
Docker 是一个**容器化平台**，可以将应用程序及其依赖环境打包成一个独立的“集装箱”（即容器），实现**一次构建，到处运行**。  
👉 **类比理解**：  
- **虚拟机**：模拟完整操作系统（笨重，占用资源多）。  
- **容器**：只打包应用和必要依赖（轻量、秒级启动，共享主机内核）。  

---

## **为什么需要 Docker？**
1. **环境一致性**：解决“在我电脑上能运行，到服务器就不行”的问题。  
2. **快速部署**：秒级启动应用，无需配置复杂环境。  
3. **资源高效**：一台服务器可运行数百个容器（虚拟机通常只能几十个）。  

---

## **Docker 核心概念**
### 1. **镜像（Image）**
- 容器的“模板”，包含运行应用所需的代码、库、环境等。  
- 例如：`nginx` 镜像、`python` 镜像。  

### 2. **容器（Container）**
- 镜像的运行实例（类似“类”和“对象”的关系）。  
- 容器是轻量级、隔离的进程，可以启动、停止、删除。  

### 3. **仓库（Registry）**
- 存储和分发镜像的平台。  
- **Docker Hub**：官方公共仓库（类似 GitHub）。  
- 私有仓库：企业自建的镜像仓库（如 Harbor）。  

---

## **Docker 安装**
### Windows/macOS
1. 下载 Docker Desktop：https://www.docker.com/products/docker-desktop  
2. 安装后启动，确保右下角 Docker 图标显示为“Running”。  

### Linux（Ubuntu）
```bash
# 安装 Docker
sudo apt update
sudo apt install docker.io

# 启动 Docker 服务
sudo systemctl start docker
sudo systemctl enable docker

# 验证安装
docker --version
```

---

## **Docker 基础操作**
### 1. 拉取镜像（从仓库下载）
```bash
docker pull nginx:latest  # 下载最新版 Nginx 镜像
```

### 2. 运行容器
```bash
docker run -d -p 80:80 --name my-nginx nginx
```
- `-d`：后台运行（detached mode）。  
- `-p 80:80`：将主机的 80 端口映射到容器的 80 端口。  
- `--name`：给容器命名（否则 Docker 会随机生成名称）。  

### 3. 查看容器状态
```bash
docker ps          # 查看运行中的容器
docker ps -a       # 查看所有容器（包括已停止的）
```

### 4. 停止/启动/删除容器
```bash
docker stop my-nginx    # 停止容器
docker start my-nginx   # 启动已停止的容器
docker rm my-nginx      # 删除容器（需先停止）
```

### 5. 进入容器内部
```bash
docker exec -it my-nginx /bin/bash
```
- `-it`：以交互模式进入容器。  
- `/bin/bash`：启动容器的 Bash 终端。  

### 6. 查看镜像列表
```bash
docker images
```

---

## **实际应用场景**
### 场景 1：快速部署一个网页
1. 创建一个 `index.html` 文件：
   ```html
   <h1>Hello Docker!</h1>
   ```

2. 运行 Nginx 容器并挂载本地目录：
   ```bash
   docker run -d -p 80:80 -v /path/to/html:/usr/share/nginx/html --name my-web nginx
   ```
   - `-v`：将本地的 `/path/to/html` 目录挂载到容器的 `/usr/share/nginx/html`。  

3. 访问 `http://localhost`，即可看到你的网页！

---

### 场景 2：运行 Python 应用
1. 创建一个 `app.py`：
   ```python
   from flask import Flask
   app = Flask(__name__)
   
   @app.route('/')
   def hello():
       return "Hello Docker with Flask!"
   
   if __name__ == '__main__':
       app.run(host='0.0.0.0', port=5000)
   ```

2. 编写 `Dockerfile`（镜像构建文件）：
   ```dockerfile
   # 使用 Python 官方镜像
   FROM python:3.9-slim
   
   # 设置工作目录
   WORKDIR /app
   
   # 复制代码到容器
   COPY . .
   
   # 安装依赖
   RUN pip install flask
   
   # 暴露端口
   EXPOSE 5000
   
   # 启动命令
   CMD ["python", "app.py"]
   ```

3. 构建镜像：
   ```bash
   docker build -t my-flask-app .
   ```

4. 运行容器：
   ```bash
   docker run -d -p 5000:5000 --name my-app my-flask-app
   ```

5. 访问 `http://localhost:5000`，看到 Flask 应用的输出！

---

## **Docker 核心命令速查表**
| 命令                                 | 说明         |
| ------------------------------------ | ------------ |
| `docker pull <镜像名>`               | 拉取镜像     |
| `docker run [选项] <镜像>`           | 运行容器     |
| `docker ps`                          | 查看容器列表 |
| `docker stop <容器名>`               | 停止容器     |
| `docker start <容器名>`              | 启动容器     |
| `docker rm <容器名>`                 | 删除容器     |
| `docker rmi <镜像名>`                | 删除镜像     |
| `docker exec -it <容器名> /bin/bash` | 进入容器终端 |
| `docker logs <容器名>`               | 查看容器日志 |

---

## **常见问题与解决**
### 问题 1：端口冲突
- **错误**：`Bind for 0.0.0.0:80 failed: port is already allocated`  
- **解决**：更换主机端口（如 `-p 8080:80`）或停止占用端口的容器。

### 问题 2：权限不足
- **错误**：`Got permission denied while trying to connect to the Docker daemon`  
- **解决**（Linux）：
  ```bash
  sudo usermod -aG docker $USER  # 将当前用户加入 docker 组
  newgrp docker                 # 刷新用户组
  ```

### 问题 3：镜像下载失败
- **错误**：`Error response from daemon: pull access denied`  
- **解决**：检查镜像名称拼写，或登录 Docker Hub：
  ```bash
  docker login
  ```

---

## **深入学习建议**
1. **理解 Dockerfile**：学习编写自定义镜像的配置文件。  
2. **数据持久化**：掌握 `-v` 挂载卷和 `docker volume` 的使用。  
3. **网络配置**：学习容器间通信（如 `docker network`）。  
4. **Docker Compose**：用 YAML 文件管理多容器应用。  

推荐资源：
- 官方文档：https://docs.docker.com/  
- 互动教程：https://www.docker.com/101-tutorial/  
