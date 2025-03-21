# git指南

---

## **Git 是什么？**
Git 是一个**版本控制系统**，帮助你管理文件的修改历史。比如：
- 保存代码的不同版本（如 v1.0、v2.0）。
- 多人协作时，合并不同人的代码。
- 找回误删的文件或代码。

---

## **Git 核心概念**
### 1. **仓库（Repository）**
- 一个项目的“数据库”，存储所有文件和历史记录。
- **本地仓库**：在你电脑上的仓库。
- **远程仓库**：在服务器（如 GitHub、GitLab）上的仓库。

### 2. **提交（Commit）**
- 将文件的改动**永久保存**到本地仓库，类似游戏存档。
- 每个提交有一个唯一的 ID（如 `a1b2c3d`）。

### 3. **分支（Branch）**
- 默认分支是 `master` 或 `main`（新版 Git 用 `main`）。
- 分支允许你在独立线上开发，不影响主代码。

### 4. **远程仓库（Remote）**
- 常用远程仓库名称是 `origin`（默认指向 GitHub/GitLab 等）。

---

## **Git 基础操作**
### 第一步：安装 Git
- 下载地址：https://git-scm.com/
- 安装后打开终端（Windows 用 Git Bash 或 CMD），输入 `git --version` 验证是否成功。

### 第二步：配置用户信息
```bash
git config --global user.name "你的名字"
git config --global user.email "你的邮箱"
```
- 这些信息会记录在提交历史中。

---

### 第三步：初始化仓库
1. 进入项目文件夹（如 `D:\Desktop\2024.03.21`）：
   ```bash
   cd D:\Desktop\2024.03.21
   ```

2. 初始化 Git 仓库：
   ```bash
   git init
   ```
   - 这会创建一个隐藏的 `.git` 文件夹，存储 Git 所有数据。

---

### 第四步：添加文件到暂存区
1. 创建或修改文件（如 `README.md`）。
2. 将文件添加到暂存区（Staging Area）：
   ```bash
   git add README.md       # 添加单个文件
   git add .              # 添加所有修改的文件
   ```
   - **暂存区**：临时存放要提交的文件。

---

### 第五步：提交到本地仓库
```bash
git commit -m "提交描述（如：第一次提交，添加README文件）"
```
- `-m` 后跟提交的说明（**必填**，建议清晰描述改动内容）。

---

### 第六步：关联远程仓库（如 GitHub）
1. 在 GitHub 上创建一个新仓库（如 `JoyceNote`）。
2. 复制仓库的 SSH 或 HTTPS 地址（如 `git@github.com:HelloJoyce/JoyceNote.git`）。
3. 本地关联远程仓库：
   ```bash
   git remote add origin git@github.com:HelloJoyce/JoyceNote.git
   ```
   - `origin` 是远程仓库的默认名称（可自定义）。

---

### 第七步：推送代码到远程仓库
```bash
git push -u origin master
```
- `-u` 表示将本地分支与远程分支关联（下次可直接用 `git push`）。
- 如果远程仓库是空的，直接推送成功。
- **如果远程仓库有文件（如 README）**，需先拉取合并（见下文）。

---

## **常见问题与解决方法**
### 问题 1：远程仓库有本地没有的文件（如 README）
- **错误提示**：`! [rejected] master -> master (fetch first)`
- **原因**：远程仓库的提交历史与本地不兼容。
- **解决步骤**：
  1. 拉取远程内容并合并：
     ```bash
     git pull origin master --allow-unrelated-histories
     ```
  2. 解决可能的冲突（如有冲突，Git 会提示）。
  3. 重新推送：
     ```bash
     git push origin master
     ```

---

### 问题 2：权限不足（SSH 密钥问题）
- **错误提示**：`Permission denied (publickey)`
- **原因**：未配置 SSH 密钥或密钥未添加到 GitHub。
- **解决方法**：
  1. 生成 SSH 密钥：
     ```bash
     ssh-keygen -t xxxxx -C "你的邮箱"
     ```
  2. 将公钥（`~/.ssh/id_ed25519.pub` 内容）添加到 GitHub：
     - GitHub → Settings → SSH and GPG keys → New SSH key.

---

## **Git 常用命令速查表**
| 命令                   | 说明                                        |
| ---------------------- | ------------------------------------------- |
| `git init`             | 初始化本地仓库                              |
| `git add <文件>`       | 添加文件到暂存区                            |
| `git commit -m "描述"` | 提交到本地仓库                              |
| `git status`           | 查看文件状态（红：未跟踪/修改，绿：已暂存） |
| `git log`              | 查看提交历史                                |
| `git remote -v`        | 查看远程仓库地址                            |
| `git push`             | 推送代码到远程仓库                          |
| `git pull`             | 拉取远程仓库的更新                          |
| `git clone <仓库地址>` | 下载远程仓库到本地                          |

---

## **Git 工作流程总结**
1. 修改文件 → `git add` → `git commit` → `git push`。
2. 多人协作时，先 `git pull` 拉取最新代码，再提交自己的修改。

---

## **学习建议**
1. **动手实践**：按步骤操作一个小项目（如上传笔记）。
2. **理解原理**：暂存区、分支、合并等概念是关键。
3. **使用图形化工具**（可选）：
   - GitHub Desktop：https://desktop.github.com/
   - VS Code 内置 Git 支持。
4. **参考文档**：
   - 官方文档：https://git-scm.com/doc
   - 交互式学习：https://learngitbranching.js.org/
