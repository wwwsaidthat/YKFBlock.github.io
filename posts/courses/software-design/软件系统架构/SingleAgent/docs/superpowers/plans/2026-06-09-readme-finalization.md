# README Finalization 实现计划

> **面向 AI 代理的工作者：** 必需子技能：使用 superpowers:subagent-driven-development（推荐）或 superpowers:executing-plans 逐任务实现此计划。步骤使用复选框（`- [ ]`）语法来跟踪进度。

**目标：** 将 `README.md` 整理为最终提交版英文说明文档，使其与当前项目状态、输出产物和提交规则保持一致。

**架构：** 保留单文件 README 结构，通过重写章节组织来区分项目概览、环境配置、运行步骤、输出目录和提交提醒。README 继续承担项目入口文档职责，敏感信息清理与最终提交流程则通过交叉引用 `SUBMISSION-CHECKLIST.md` 完成。

**技术栈：** Markdown、Maven、Spring Boot

---

## 文件结构

**修改文件：**

- `E:\Homework\software-architecture\SingleAgent\README.md`
  - 更新为最终提交版英文 README，补充运行说明、环境变量、输出产物和提交提醒。

## 任务 1：用红灯锁定最终 README 结构

**文件：**

- 修改：`E:\Homework\software-architecture\SingleAgent\README.md`

- [ ] **步骤 1：检查当前 README 仍是简略版**

运行：`rg -n "## Technology stack|## Environment variables|## Generated outputs|SUBMISSION-CHECKLIST" README.md`
预期：

- 命中旧的基础章节
- 不命中 `SUBMISSION-CHECKLIST`

- [ ] **步骤 2：重写 README 为最终提交版**

````md
# SingleAgent

Single-agent implementation for Software Architecture Assignment 2 using the DeepSeek V4 Pro model through a Spring Boot application.

## Project overview

- Approach: Single-agent
- Model: `deepseek-v4-pro-260425`
- Stack: Java 17, Spring Boot, Spring AI, Spring AI Alibaba, Maven

## Environment variables

- `ARK_API_KEY`: required for real model execution
- `ARK_BASE_URL`: defaults to `https://ark.cn-beijing.volces.com/api/v3`
- `LLM_MODEL`: defaults to `deepseek-v4-pro-260425`

Use `.env.example` as the safe template and keep real secrets out of versioned files.

## Verify the project

```bash
mvn -q test
```
````

## Run the application

```bash
mvn spring-boot:run
```

Then open `http://localhost:8080/` and trigger the run from the web page.

## Generated outputs

- `outputs/logs/`
- `outputs/diagrams/`
- `outputs/json/`
- `outputs/reports/final-report.md`

## Submission notes

- Fill in the contribution table in `outputs/reports/final-report.md` before submission.
- Review `SUBMISSION-CHECKLIST.md` before packaging the final deliverable.
- Do not submit any real API key or private environment file.

````

- [ ] **步骤 3：验证新结构存在**

运行：`rg -n "^## |SUBMISSION-CHECKLIST|mvn -q test|Project overview|Submission notes" README.md`
预期：
- 命中 `Project overview`
- 命中 `Environment variables`
- 命中 `Verify the project`
- 命中 `Submission notes`
- 命中 `SUBMISSION-CHECKLIST.md`

- [ ] **步骤 4：Commit**

```bash
git add README.md
git commit -m "docs: finalize project readme"
````

## 任务 2：回归验证 README 与当前项目状态一致

**文件：**

- 验证：`E:\Homework\software-architecture\SingleAgent\README.md`
- 参考：`E:\Homework\software-architecture\SingleAgent\SUBMISSION-CHECKLIST.md`

- [ ] **步骤 1：检查 README 提到的命令和文件真实存在**

运行：`rg -n "mvn -q test|mvn spring-boot:run|outputs/reports/final-report.md|SUBMISSION-CHECKLIST.md" README.md`
预期：所有关键命令和路径均被命中。

- [ ] **步骤 2：运行项目测试**

运行：`mvn -q test`
预期：PASS。

- [ ] **步骤 3：检查诊断**

使用 IDE diagnostics 检查 `README.md`
预期：无新问题。

- [ ] **步骤 4：Commit**

```bash
git add README.md
git commit -m "docs: verify readme matches final submission state"
```

## 自检结果

- 规格覆盖度：计划覆盖了 README 的最终结构重写、与提交清单联动、以及项目状态一致性验证。
- 占位符扫描：无 “TODO”“后续实现”“类似任务” 等占位语。
- 类型一致性：README 中引用的命令、路径和文件名均使用项目当前真实名称。
