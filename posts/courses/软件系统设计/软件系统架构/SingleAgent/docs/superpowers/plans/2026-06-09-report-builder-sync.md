# ReportBuilder Sync 实现计划

> **面向 AI 代理的工作者：** 必需子技能：使用 superpowers:subagent-driven-development（推荐）或 superpowers:executing-plans 逐任务实现此计划。步骤使用复选框（`- [ ]`）语法来跟踪进度。

**目标：** 让 `ReportBuilder` 自动生成的报告结构与当前已润色的 `outputs/reports/final-report.md` 一致，避免重新运行后回退到旧版重复结构。

**架构：** 保留 `RunSummary`、四轮 `IterationResult` 和运行统计的现有数据流，只重写 `ReportBuilder` 的字符串拼装策略。每个迭代在最终报告中只出现一次，交互成本表与反思模板继续由生成器输出，从而让运行产物和手工润色版保持一致。

**技术栈：** Java 17、Spring Boot、JUnit 5、AssertJ

---

## 文件结构

**修改文件：**

- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\ReportBuilder.java`
  - 负责生成最终报告 Markdown，需要从“按 ADD Step 2~7 重复铺开”改为“按四轮迭代顺序各输出一次完整结果”。
- `E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\service\ReportBuilderTest.java`
  - 负责约束新报告结构，防止重新生成时回退到旧版重复格式。

## 任务 1：用测试锁定新的报告结构

**文件：**

- 修改：`E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\service\ReportBuilderTest.java`
- 测试：`E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\service\ReportBuilderTest.java`

- [ ] **步骤 1：编写失败的测试**

```java
package com.course.singleagent.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.course.singleagent.domain.IterationResult;
import com.course.singleagent.domain.RunSummary;
import java.util.List;
import org.junit.jupiter.api.Test;

class ReportBuilderTest {

    @Test
    void shouldBuildCompactReportWithOneSectionPerIteration() {
        ReportBuilder builder = new ReportBuilder();
        RunSummary summary = new RunSummary(
                List.of(
                        new IterationResult(1, "Iteration 1", "Iteration 1 body", "diagram", "summary", List.of(), new TokenUsage(20, 40, 60)),
                        new IterationResult(2, "Iteration 2", "Iteration 2 body", "diagram", "summary", List.of(), new TokenUsage(30, 50, 80))
                ),
                "outputs/reports/final-report.md",
                new RunMetrics("deepseek-v4-pro-260425", 1, 50, 90, 140, 120)
        );

        String report = builder.buildReport(summary);

        assertThat(report).contains("### 1）Iteration 1: Iteration 1");
        assertThat(report).contains("### 2）Iteration 2: Iteration 2");
        assertThat(report).contains("The figures above come from the successful real run executed in the current local environment.");
        assertThat(report).contains("Replace the placeholders below with the actual student names and contributions before final submission.");
        assertThat(report).doesNotContain("### 1）Output results of each step");
        assertThat(report).doesNotContain("ADD Step 2:");
        assertThat(report).contains("| Token Consumption（K tokens） | 0.14 |");
    }
}
```

- [ ] **步骤 2：运行测试验证失败**

运行：`mvn -q "-Dtest=ReportBuilderTest" test`
预期：FAIL，旧版 `ReportBuilder` 仍包含 `### 1）Output results of each step` 和 `ADD Step 2:`。

- [ ] **步骤 3：根据 polished 报告重写生成逻辑**

```java
public String buildReport(RunSummary summary) {
    String iterationSections = IntStream.range(0, summary.iterationResults().size())
            .mapToObj(index -> buildIterationSection(index + 1, summary.iterationResults().get(index)))
            .collect(Collectors.joining("\n\n"));

    RunMetrics metrics = summary.metrics();
    return """
            # Assignment 2 Report

            ## 1. Output results of ADD

            ADD Step 1:
            Review the assignment inputs and identify the architectural drivers from the provided prior knowledge.

            The following results are reproduced from the completed single-agent run. Each iteration records the full ADD reasoning outcome for one refinement cycle.

            %s

            ## 2. Interaction cost analysis

            | Item | Value |
            | --- | --- |
            | The way of completing the assignment | Single-agent |
            | The LLM used | %s |
            | Number of Human Interactions（turns） | %d |
            | Token Consumption（K tokens） | %.2f |
            | Time Cost（min） | %.2f |

            The figures above come from the successful real run executed in the current local environment.

            ## 3. Individual Reflection

            ### 1）The problems encountered and the solutions adopted

            Three practical issues were encountered during implementation and execution. First, integrating Spring AI with the Volcengine Ark OpenAI-compatible endpoint required explicit chat endpoint configuration, because the default Spring AI chat completion path did not match Ark's expected path. This was solved by configuring the chat-specific base URL and completions path explicitly. Second, the system needed to remain testable while still supporting real model invocation, so the model call was abstracted behind a gateway and the execution pipeline was validated with focused tests before the live run. Third, the final submission artifacts had to include both architectural content and runtime evidence, so the application was extended to record token usage, elapsed time, structured logs, Mermaid diagrams, JSON summaries, and the final report in a single run.

            ### 2）A detailed account of your personal contributions to the group work

            Replace the placeholders below with the actual student names and contributions before final submission.

            | Name（Chinese） | Contributions |
            | --- | --- |
            | [Please fill in] | Implement the parts personally completed by this student. |
            | [Please fill in] | Implement the parts personally completed by this student. |
            | [Please fill in] | Implement the parts personally completed by this student. |
            """.formatted(
            iterationSections,
            metrics.modelName(),
            metrics.humanInteractionTurns(),
            metrics.totalTokensInK(),
            metrics.elapsedMinutes()
    );
}
```

- [ ] **步骤 4：实现新的单轮 section 拼装**

```java
private String buildIterationSection(int displayIndex, IterationResult result) {
    return """
            ### %d）Iteration %d: %s

            %s
            """.formatted(
            displayIndex,
            result.iterationNumber(),
            result.title(),
            result.finalContent()
    );
}
```

- [ ] **步骤 5：运行测试验证通过**

运行：`mvn -q "-Dtest=ReportBuilderTest" test`
预期：PASS。

- [ ] **步骤 6：Commit**

```bash
git add src/main/java/com/course/singleagent/service/ReportBuilder.java src/test/java/com/course/singleagent/service/ReportBuilderTest.java
git commit -m "feat: align report builder with polished final report"
```

## 任务 2：验证生成器与最终产物重新对齐

**文件：**

- 修改：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\ReportBuilder.java`
- 验证产物：`E:\Homework\software-architecture\SingleAgent\outputs\reports\final-report.md`

- [ ] **步骤 1：运行全量测试**

运行：`mvn -q test`
预期：PASS。

- [ ] **步骤 2：重新生成最终报告**

运行：`mvn spring-boot:run`
预期：应用启动成功，可通过 `/run` 重新生成 `outputs/reports/final-report.md`。

- [ ] **步骤 3：检查新报告结构**

运行：`rg -n "^#|^##|^###|Interaction cost analysis|ADD Step 2:" outputs/reports/final-report.md`
预期：

- 包含 `### 1）Iteration 1:`
- 包含 `### 2）Iteration 2:`
- 包含 `## 2. Interaction cost analysis`
- 不再包含 `ADD Step 2:`

- [ ] **步骤 4：检查统计与反思段落**

运行：`rg -n "Token Consumption|Time Cost|Replace the placeholders" outputs/reports/final-report.md`
预期：

- 统计表保留真实数据
- 个人贡献区域保留占位模板

- [ ] **步骤 5：Commit**

```bash
git add outputs/reports/final-report.md
git commit -m "docs: regenerate final report with compact iteration sections"
```

## 自检结果

- 规格覆盖度：计划覆盖了生成器改造、测试约束、重新生成产物和最终结构验证。
- 占位符扫描：无 “TODO”“后续实现”“类似任务” 等占位语。
- 类型一致性：统一使用 `RunSummary`、`IterationResult`、`RunMetrics` 和现有统计字段，不引入新领域类型。
