# Submission Cleanup 实现计划

> **面向 AI 代理的工作者：** 必需子技能：使用 superpowers:subagent-driven-development（推荐）或 superpowers:executing-plans 逐任务实现此计划。步骤使用复选框（`- [ ]`）语法来跟踪进度。

**目标：** 统一完成最终提交清单整理与敏感信息清理，确保作业目录适合直接提交且不泄露真实密钥。

**架构：** 通过新增一份独立的提交清单文档承载“提交内容、补填项、自检项、敏感信息规则”，同时将 `.env.example` 恢复为安全占位值。这样 README 继续承担运行说明，提交规范则由单独文档管理，职责更清晰。

**技术栈：** Markdown、文本配置、JUnit 5（仅在需要时复用现有测试链路进行回归验证）

---

## 文件结构

**创建文件：**

- `E:\Homework\software-architecture\SingleAgent\SUBMISSION-CHECKLIST.md`
  - 最终提交清单，集中说明必须提交的文件、手工补填项、敏感信息约束和最终自检步骤。

**修改文件：**

- `E:\Homework\software-architecture\SingleAgent\.env.example`
  - 将真实 `ARK_API_KEY` 清理为安全占位示例，保留其余环境变量模板。

## 任务 1：先用红灯锁定敏感信息清理

**文件：**
- 修改：`E:\Homework\software-architecture\SingleAgent\.env.example`

- [ ] **步骤 1：检查当前示例文件仍含真实密钥**

运行：`rg -n "ARK_API_KEY=" .env.example`
预期：命中当前真实值，证明需要清理。

- [ ] **步骤 2：将 `.env.example` 改为安全占位值**

```env
ARK_API_KEY=your-ark-api-key-here
ARK_BASE_URL=https://ark.cn-beijing.volces.com/api/v3
LLM_MODEL=deepseek-v4-pro-260425
```

- [ ] **步骤 3：验证敏感信息已清理**

运行：`rg -n "a4fd7f90-4bdd-4262-ad32-5f892914da24|your-ark-api-key-here" .env.example`
预期：
- 不再命中真实 key
- 命中 `your-ark-api-key-here`

- [ ] **步骤 4：Commit**

```bash
git add .env.example
git commit -m "chore: sanitize env example"
```

## 任务 2：新增统一提交清单文档

**文件：**
- 创建：`E:\Homework\software-architecture\SingleAgent\SUBMISSION-CHECKLIST.md`

- [ ] **步骤 1：编写提交清单文档**

```md
# Submission Checklist

## Files to submit
- `pom.xml`
- `README.md`
- `src/main/`
- `src/test/`
- `outputs/logs/`
- `outputs/diagrams/`
- `outputs/json/`
- `outputs/reports/final-report.md`

## Items to fill in before submission
- Update the contribution table in `outputs/reports/final-report.md`.
- Confirm the final report still contains the real interaction metrics from the successful run.

## Sensitive information rules
- Do not submit any real `ARK_API_KEY`.
- Keep `.env.example` as placeholders only.
- Do not create a committed `.env` file with real secrets.

## Final self-check
- Run `mvn -q test`.
- Confirm the four logs, four diagrams, four JSON files, and one final report exist.
- Open `outputs/reports/final-report.md` and verify the contribution table has been filled.
```

- [ ] **步骤 2：验证清单包含关键章节**

运行：`rg -n "^## |ARK_API_KEY|final-report.md|mvn -q test|outputs/logs" SUBMISSION-CHECKLIST.md`
预期：
- 命中 `Files to submit`
- 命中 `Items to fill in before submission`
- 命中 `Sensitive information rules`
- 命中 `Final self-check`

- [ ] **步骤 3：Commit**

```bash
git add SUBMISSION-CHECKLIST.md
git commit -m "docs: add final submission checklist"
```

## 任务 3：最终回归验证

**文件：**
- 验证：`E:\Homework\software-architecture\SingleAgent\.env.example`
- 验证：`E:\Homework\software-architecture\SingleAgent\SUBMISSION-CHECKLIST.md`
- 验证：`E:\Homework\software-architecture\SingleAgent\outputs\reports\final-report.md`

- [ ] **步骤 1：运行项目测试**

运行：`mvn -q test`
预期：PASS。

- [ ] **步骤 2：检查输出产物数量**

运行：`rg --files outputs`
预期：包含 4 个 `logs`、4 个 `diagrams`、4 个 `json` 和 1 个 `reports/final-report.md`。

- [ ] **步骤 3：检查报告补填项仍可见**

运行：`rg -n "Please fill in|Name（Chinese）" outputs/reports/final-report.md`
预期：个人贡献表占位内容仍存在，提醒提交前手工补填。

- [ ] **步骤 4：Commit**

```bash
git add .env.example SUBMISSION-CHECKLIST.md
git commit -m "docs: prepare project for final submission"
```

## 自检结果

- 规格覆盖度：计划覆盖了敏感信息清理、统一提交清单编写和最终回归验证。
- 占位符扫描：无 “TODO”“后续实现”“类似任务” 等占位语。
- 类型一致性：仅涉及 Markdown 文档与环境变量示例，不引入新的代码类型或接口。
