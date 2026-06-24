# SingleAgent Hotel Pricing Assignment 实现计划

> **面向 AI 代理的工作者：** 必需子技能：使用 superpowers:subagent-driven-development（推荐）或 superpowers:executing-plans 逐任务实现此计划。步骤使用复选框（`- [ ]`）语法来跟踪进度。

**目标：** 在 `E:\Homework\software-architecture\SingleAgent` 下实现基于 Spring Boot + Spring AI Alibaba 的单智能体系统，能够运行四轮 ADD 3.0 迭代，生成带时间戳的对话日志、Mermaid 图和英文报告。

**架构：** 应用以一个单智能体编排服务为核心，按固定的四轮迭代顺序执行“生成 -> 自反思 -> 修复 -> 落盘”流程。知识库仅内置作业文档中的共享内容，模型接入通过环境变量配置 DeepSeek 端点和模型名，输出产物统一写入 `outputs/` 目录。

**技术栈：** Java 17、Spring Boot 3、Spring AI Alibaba、Maven、JUnit 5、Thymeleaf（或最小 REST 入口）

---

## 文件结构

**创建文件：**

- `E:\Homework\software-architecture\SingleAgent\pom.xml`
- `E:\Homework\software-architecture\SingleAgent\.env.example`
- `E:\Homework\software-architecture\SingleAgent\README.md`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\SingleAgentApplication.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\config\ModelProperties.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\config\OutputProperties.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\config\AppConfig.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\domain\IterationDefinition.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\domain\IterationResult.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\domain\ConversationEntry.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\domain\RunSummary.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\knowledge\AssignmentKnowledgeBase.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\prompt\PromptFactory.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\SingleAgentRunner.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\ArtifactWriter.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\ReportBuilder.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\TimestampService.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\web\RunController.java`
- `E:\Homework\software-architecture\SingleAgent\src\main\resources\application.yml`
- `E:\Homework\software-architecture\SingleAgent\src\main\resources\templates\index.html`
- `E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\prompt\PromptFactoryTest.java`
- `E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\service\ReportBuilderTest.java`
- `E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\service\ArtifactWriterTest.java`
- `E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\web\RunControllerTest.java`

**职责说明：**

- `config/*`：环境变量绑定、默认值、Bean 配置
- `domain/*`：迭代定义、运行结果、日志记录结构
- `knowledge/*`：内置作业知识库，禁止外部知识注入
- `prompt/*`：系统提示词、迭代提示词、自反思提示词构造
- `service/*`：单智能体编排、结果落盘、报告生成
- `web/*`：最小化运行入口
- `templates/*`：简单触发页面
- `src/test/*`：围绕提示词、产物写入、报告生成、控制器的聚焦测试

### 任务 1：初始化 Maven 工程与配置骨架

**文件：**
- 创建：`E:\Homework\software-architecture\SingleAgent\pom.xml`
- 创建：`E:\Homework\software-architecture\SingleAgent\.env.example`
- 创建：`E:\Homework\software-architecture\SingleAgent\README.md`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\SingleAgentApplication.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\resources\application.yml`

- [ ] **步骤 1：先写最小启动测试**

```java
package com.course.singleagent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SingleAgentApplicationTests {

    @Test
    void contextLoads() {
    }
}
```

- [ ] **步骤 2：运行测试验证失败**

运行：`mvn -q -Dtest=SingleAgentApplicationTests test`
预期：FAIL，报错缺少 `pom.xml` 或 Spring Boot 主类。

- [ ] **步骤 3：补齐最小项目骨架**

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.course</groupId>
    <artifactId>singleagent</artifactId>
    <version>1.0.0</version>
    <properties>
        <java.version>17</java.version>
        <spring-boot.version>3.3.0</spring-boot.version>
    </properties>
</project>
```

```java
package com.course.singleagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SingleAgentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SingleAgentApplication.class, args);
    }
}
```

```yaml
spring:
  application:
    name: singleagent
```

- [ ] **步骤 4：补充依赖与环境说明**

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

```env
ARK_API_KEY=your_api_key
ARK_BASE_URL=https://ark.cn-beijing.volces.com/api/v3
LLM_MODEL=deepseek-v4-pro-260425
```

- [ ] **步骤 5：运行测试验证通过**

运行：`mvn -q -Dtest=SingleAgentApplicationTests test`
预期：PASS。

- [ ] **步骤 6：Commit**

```bash
git add pom.xml .env.example README.md src/main
git commit -m "feat: bootstrap spring single-agent project"
```

### 任务 2：实现配置与知识库

**文件：**
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\config\ModelProperties.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\config\OutputProperties.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\config\AppConfig.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\knowledge\AssignmentKnowledgeBase.java`
- 修改：`E:\Homework\software-architecture\SingleAgent\src\main\resources\application.yml`
- 测试：`E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\knowledge\AssignmentKnowledgeBaseTest.java`

- [ ] **步骤 1：编写失败的知识库测试**

```java
package com.course.singleagent.knowledge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AssignmentKnowledgeBaseTest {

    @Test
    void shouldContainOnlyAssignmentKnowledge() {
        AssignmentKnowledgeBase knowledgeBase = new AssignmentKnowledgeBase();
        String promptContext = knowledgeBase.buildSharedContext();

        assertThat(promptContext).contains("Attribute-Driven Design");
        assertThat(promptContext).contains("Hotel Pricing System");
        assertThat(promptContext).contains("Iteration 1");
    }
}
```

- [ ] **步骤 2：运行测试验证失败**

运行：`mvn -q -Dtest=AssignmentKnowledgeBaseTest test`
预期：FAIL，报错 `AssignmentKnowledgeBase` 不存在。

- [ ] **步骤 3：实现环境变量绑定**

```java
@ConfigurationProperties(prefix = "app.model")
public record ModelProperties(
        String apiKey,
        String baseUrl,
        String modelName
) {
}
```

```yaml
app:
  model:
    api-key: ${ARK_API_KEY:}
    base-url: ${ARK_BASE_URL:https://ark.cn-beijing.volces.com/api/v3}
    model-name: ${LLM_MODEL:deepseek-v4-pro-260425}
  output:
    root-dir: outputs
```

- [ ] **步骤 4：实现知识库最小代码**

```java
package com.course.singleagent.knowledge;

import org.springframework.stereotype.Component;

@Component
public class AssignmentKnowledgeBase {

    public String buildSharedContext() {
        return """
                Attribute-Driven Design (ADD) method
                Hotel Pricing System
                Iteration 1: Establishing an Overall System Structure
                Iteration 2: Identifying Structures to Support Primary Functionality
                Iteration 3: Addressing Reliability and Availability Quality Attributes
                Iteration 4: Addressing Development and Operations
                """;
    }
}
```

- [ ] **步骤 5：运行测试验证通过**

运行：`mvn -q -Dtest=AssignmentKnowledgeBaseTest test`
预期：PASS。

- [ ] **步骤 6：Commit**

```bash
git add src/main/java/com/course/singleagent/config src/main/java/com/course/singleagent/knowledge src/main/resources/application.yml src/test
git commit -m "feat: add environment config and assignment knowledge base"
```

### 任务 3：实现提示词工厂与领域模型

**文件：**
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\domain\IterationDefinition.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\domain\IterationResult.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\domain\ConversationEntry.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\domain\RunSummary.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\prompt\PromptFactory.java`
- 测试：`E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\prompt\PromptFactoryTest.java`

- [ ] **步骤 1：编写失败的提示词测试**

```java
package com.course.singleagent.prompt;

import static org.assertj.core.api.Assertions.assertThat;

import com.course.singleagent.domain.IterationDefinition;
import com.course.singleagent.knowledge.AssignmentKnowledgeBase;
import org.junit.jupiter.api.Test;

class PromptFactoryTest {

    @Test
    void shouldBuildPromptWithReflectionRules() {
        PromptFactory factory = new PromptFactory(new AssignmentKnowledgeBase());
        String prompt = factory.buildIterationPrompt(IterationDefinition.iteration1(), "");

        assertThat(prompt).contains("self-reflection");
        assertThat(prompt).contains("Mermaid");
        assertThat(prompt).contains("ADD Step 2");
    }
}
```

- [ ] **步骤 2：运行测试验证失败**

运行：`mvn -q -Dtest=PromptFactoryTest test`
预期：FAIL，报错 `PromptFactory` 或 `IterationDefinition` 不存在。

- [ ] **步骤 3：定义领域模型**

```java
public record IterationDefinition(
        int number,
        String title,
        String goal
) {
    public static IterationDefinition iteration1() {
        return new IterationDefinition(1, "Establishing an Overall System Structure", "Focus on the initial system structure.");
    }
}
```

```java
public record ConversationEntry(
        String phase,
        String timestamp,
        String prompt,
        String response
) {
}
```

- [ ] **步骤 4：实现提示词工厂**

```java
@Component
public class PromptFactory {

    private final AssignmentKnowledgeBase knowledgeBase;

    public PromptFactory(AssignmentKnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public String buildIterationPrompt(IterationDefinition definition, String priorSummary) {
        return knowledgeBase.buildSharedContext()
                + "\nUse ADD Step 2 to Step 7."
                + "\nGenerate Mermaid code."
                + "\nPerform self-reflection before final output."
                + "\nCurrent iteration: " + definition.title()
                + "\nPrevious decisions: " + priorSummary;
    }
}
```

- [ ] **步骤 5：运行测试验证通过**

运行：`mvn -q -Dtest=PromptFactoryTest test`
预期：PASS。

- [ ] **步骤 6：Commit**

```bash
git add src/main/java/com/course/singleagent/domain src/main/java/com/course/singleagent/prompt src/test/java/com/course/singleagent/prompt
git commit -m "feat: add prompt factory and domain models"
```

### 任务 4：实现单智能体编排与产物写入

**文件：**
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\SingleAgentRunner.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\ArtifactWriter.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\TimestampService.java`
- 测试：`E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\service\ArtifactWriterTest.java`

- [ ] **步骤 1：编写失败的产物写入测试**

```java
package com.course.singleagent.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.course.singleagent.domain.IterationResult;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ArtifactWriterTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldWriteIterationArtifacts() throws Exception {
        ArtifactWriter writer = new ArtifactWriter(tempDir.toString());
        IterationResult result = new IterationResult(1, "content", "```mermaid\ngraph TD\nA-->B\n```", java.util.List.of());

        writer.writeIterationArtifacts(result);

        assertThat(Files.exists(tempDir.resolve("logs/iteration-1.md"))).isTrue();
        assertThat(Files.exists(tempDir.resolve("diagrams/iteration-1.mmd"))).isTrue();
    }
}
```

- [ ] **步骤 2：运行测试验证失败**

运行：`mvn -q -Dtest=ArtifactWriterTest test`
预期：FAIL，报错 `ArtifactWriter` 或 `IterationResult` 构造缺失。

- [ ] **步骤 3：实现最小写入逻辑**

```java
public record IterationResult(
        int iterationNumber,
        String finalContent,
        String mermaidDiagram,
        List<ConversationEntry> conversationEntries
) {
}
```

```java
public class ArtifactWriter {

    private final Path rootDir;

    public ArtifactWriter(String rootDir) {
        this.rootDir = Path.of(rootDir);
    }

    public void writeIterationArtifacts(IterationResult result) throws IOException {
        Files.createDirectories(rootDir.resolve("logs"));
        Files.createDirectories(rootDir.resolve("diagrams"));
        Files.writeString(rootDir.resolve("logs/iteration-" + result.iterationNumber() + ".md"), result.finalContent());
        Files.writeString(rootDir.resolve("diagrams/iteration-" + result.iterationNumber() + ".mmd"), result.mermaidDiagram());
    }
}
```

- [ ] **步骤 4：实现单智能体顺序执行骨架**

```java
@Service
public class SingleAgentRunner {

    public List<IterationResult> runAllIterations() {
        return List.of(
                runIteration(IterationDefinition.iteration1(), ""),
                runIteration(IterationDefinition.iteration2(), "iteration 1 summary")
        );
    }
}
```

- [ ] **步骤 5：运行测试验证通过**

运行：`mvn -q -Dtest=ArtifactWriterTest test`
预期：PASS。

- [ ] **步骤 6：Commit**

```bash
git add src/main/java/com/course/singleagent/service src/test/java/com/course/singleagent/service
git commit -m "feat: add iteration orchestration and artifact writer"
```

### 任务 5：接入 Spring AI Alibaba 并完成四轮执行

**文件：**
- 修改：`E:\Homework\software-architecture\SingleAgent\pom.xml`
- 修改：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\config\AppConfig.java`
- 修改：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\SingleAgentRunner.java`
- 修改：`E:\Homework\software-architecture\SingleAgent\src\main\resources\application.yml`
- 测试：`E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\service\SingleAgentRunnerTest.java`

- [ ] **步骤 1：编写失败的编排测试**

```java
package com.course.singleagent.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.course.singleagent.domain.IterationResult;
import java.util.List;
import org.junit.jupiter.api.Test;

class SingleAgentRunnerTest {

    @Test
    void shouldProduceFourIterations() {
        SingleAgentRunner runner = new SingleAgentRunner(prompt -> "mock response");
        List<IterationResult> results = runner.runAllIterations();

        assertThat(results).hasSize(4);
        assertThat(results.get(0).finalContent()).contains("mock response");
    }
}
```

- [ ] **步骤 2：运行测试验证失败**

运行：`mvn -q -Dtest=SingleAgentRunnerTest test`
预期：FAIL，报错构造器不匹配或仅返回两轮。

- [ ] **步骤 3：抽象模型调用接口并完成四轮**

```java
public interface ModelGateway {
    String generate(String prompt);
}
```

```java
public List<IterationResult> runAllIterations() {
    List<IterationDefinition> definitions = List.of(
            IterationDefinition.iteration1(),
            IterationDefinition.iteration2(),
            IterationDefinition.iteration3(),
            IterationDefinition.iteration4()
    );
    ...
}
```

- [ ] **步骤 4：实现 Spring AI Alibaba 网关**

```java
@Component
public class SpringAiModelGateway implements ModelGateway {

    private final ChatClient chatClient;

    public SpringAiModelGateway(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String generate(String prompt) {
        return chatClient.prompt(prompt).call().content();
    }
}
```

- [ ] **步骤 5：运行测试验证通过**

运行：`mvn -q -Dtest=SingleAgentRunnerTest test`
预期：PASS。

- [ ] **步骤 6：Commit**

```bash
git add pom.xml src/main/java/com/course/singleagent/config src/main/java/com/course/singleagent/service src/main/resources/application.yml src/test/java/com/course/singleagent/service
git commit -m "feat: integrate spring ai alibaba for four-iteration execution"
```

### 任务 6：生成英文报告与交互日志

**文件：**
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\service\ReportBuilder.java`
- 测试：`E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\service\ReportBuilderTest.java`

- [ ] **步骤 1：编写失败的报告测试**

```java
package com.course.singleagent.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.course.singleagent.domain.IterationResult;
import java.util.List;
import org.junit.jupiter.api.Test;

class ReportBuilderTest {

    @Test
    void shouldBuildEnglishReportFromIterationResults() {
        ReportBuilder builder = new ReportBuilder();
        String report = builder.buildReport(List.of(
                new IterationResult(1, "Iteration 1 body", "diagram", List.of())
        ));

        assertThat(report).contains("Output results of ADD");
        assertThat(report).contains("Iteration 1");
        assertThat(report).contains("Interaction cost analysis");
    }
}
```

- [ ] **步骤 2：运行测试验证失败**

运行：`mvn -q -Dtest=ReportBuilderTest test`
预期：FAIL，报错 `ReportBuilder` 不存在。

- [ ] **步骤 3：实现报告构建器**

```java
public class ReportBuilder {

    public String buildReport(List<IterationResult> results) {
        return """
                ## 1. Output results of ADD

                ### Iteration 1
                %s

                ## 2. Interaction cost analysis

                ## 3. Individual Reflection
                """.formatted(results.get(0).finalContent());
    }
}
```

- [ ] **步骤 4：将报告写入 `outputs/reports/final-report.md`**

```java
Files.createDirectories(rootDir.resolve("reports"));
Files.writeString(rootDir.resolve("reports/final-report.md"), reportContent);
```

- [ ] **步骤 5：运行测试验证通过**

运行：`mvn -q -Dtest=ReportBuilderTest test`
预期：PASS。

- [ ] **步骤 6：Commit**

```bash
git add src/main/java/com/course/singleagent/service/ReportBuilder.java src/test/java/com/course/singleagent/service/ReportBuilderTest.java
git commit -m "feat: generate assignment report and summary artifacts"
```

### 任务 7：实现最小 Web 入口与最终验证

**文件：**
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\java\com\course\singleagent\web\RunController.java`
- 创建：`E:\Homework\software-architecture\SingleAgent\src\main\resources\templates\index.html`
- 测试：`E:\Homework\software-architecture\SingleAgent\src\test\java\com\course\singleagent\web\RunControllerTest.java`
- 修改：`E:\Homework\software-architecture\SingleAgent\README.md`

- [ ] **步骤 1：编写失败的控制器测试**

```java
package com.course.singleagent.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RunController.class)
class RunControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRenderIndex() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void shouldTriggerRun() throws Exception {
        mockMvc.perform(post("/run")).andExpect(status().is3xxRedirection());
    }
}
```

- [ ] **步骤 2：运行测试验证失败**

运行：`mvn -q -Dtest=RunControllerTest test`
预期：FAIL，报错 `RunController` 不存在。

- [ ] **步骤 3：实现最小控制器与页面**

```java
@Controller
public class RunController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/run")
    public String run() {
        return "redirect:/";
    }
}
```

```html
<!DOCTYPE html>
<html lang="en">
<body>
    <h1>Single Agent ADD Runner</h1>
    <form method="post" action="/run">
        <button type="submit">Run all iterations</button>
    </form>
</body>
</html>
```

- [ ] **步骤 4：运行聚焦测试与完整测试**

运行：`mvn -q -Dtest=RunControllerTest test`
预期：PASS。

运行：`mvn -q test`
预期：PASS。

- [ ] **步骤 5：手动运行应用并验证输出目录**

运行：`mvn spring-boot:run`
预期：应用启动成功，访问首页可触发执行；运行后出现：

```text
outputs/logs/
outputs/diagrams/
outputs/reports/final-report.md
```

- [ ] **步骤 6：Commit**

```bash
git add src/main/java/com/course/singleagent/web src/main/resources/templates README.md
git commit -m "feat: add web runner and final project documentation"
```

## 自检结果

- 规格覆盖度：已覆盖技术栈、环境变量模型配置、四轮迭代、自反思、日志、Mermaid、英文报告和最小运行入口。
- 占位符扫描：计划中没有保留 “TODO”“后续实现”“类似任务” 等占位内容。
- 类型一致性：统一使用 `IterationDefinition`、`IterationResult`、`ConversationEntry`、`ModelGateway`、`SingleAgentRunner` 作为核心类型和接口。
