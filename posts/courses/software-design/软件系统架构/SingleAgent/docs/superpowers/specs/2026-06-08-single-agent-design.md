# Single-Agent Hotel Pricing System Architecture Assignment Design

## 1. Goal

Build a complete submission package for Assignment 2 under `E:\Homework\software-architecture\SingleAgent` using:

- AI paradigm: Single-agent
- Base model: `deepseek-v4-pro-260425`
- Model access: Spring AI Alibaba integration with the DeepSeek-compatible endpoint configuration provided by the user

The project must produce:

- source code
- complete conversation logs with timestamps for four iterations
- an English report following the provided appendix structure
- Mermaid or PlantUML views for each iteration

## 2. Constraints and Rules

The implementation must follow the assignment constraints exactly:

- only the provided ADD 3.0 content and Hotel Pricing System case content may be used as prior knowledge
- no handcrafted few-shot examples may be embedded in prompts
- no external domain knowledge may be injected
- no requirement reinterpretation or augmentation beyond the provided assignment content
- all decision rules must come from explicit system instructions
- the single agent may perform sequential reasoning, planning, and self-reflection

Additional user constraints:

- do not change the technology stack away from the assignment-guided direction
- treat the user-provided DeepSeek code as a model invocation example, not as a stack replacement
- extract both model selection and API key from environment variables

## 3. Recommended Technical Approach

### 3.1 Stack

- Java 17
- Spring Boot 3
- Spring AI Alibaba
- Maven
- optional lightweight Thymeleaf or REST-based interface for running all four iterations

### 3.2 Why this approach

This approach stays aligned with the assignment guidance, especially the explicit instruction to learn and use Spring AI Alibaba for single-agent systems, while still supporting the user's DeepSeek endpoint and environment-based model configuration requirements.

## 4. Deliverable Layout

All work will be created inside `E:\Homework\software-architecture\SingleAgent`.

Proposed structure:

```text
SingleAgent/
  README.md
  .env.example
  pom.xml
  src/
    main/
      java/
        .../config/
        .../controller/
        .../service/
        .../prompt/
        .../domain/
        .../writer/
      resources/
        application.yml
        templates/
  outputs/
    logs/
    reports/
    diagrams/
    json/
  docs/
    superpowers/
      specs/
        2026-06-11-single-agent-design.md
```

## 5. Architecture

### 5.1 Main components

- `config`
  - loads environment variables and Spring configuration
  - validates required model settings
- `knowledge base service`
  - stores the assignment-provided prior knowledge only
  - organizes ADD method, case study, quality attributes, concerns, constraints, and report template
- `prompt package`
  - defines the system prompt for the single agent
  - defines per-iteration prompts
  - defines a self-reflection prompt
- `llm client configuration`
  - wraps Spring AI Alibaba model access
  - reads `ARK_API_KEY`, `ARK_BASE_URL`, and model selection from environment variables
- `domain package`
  - defines typed structures for iteration output, report sections, and conversation records
- `agent orchestration service`
  - executes four iterations in sequence
  - for each iteration performs generation, reflection, and revision
- `artifact writer`
  - records timestamped prompts and responses
  - saves markdown and JSON artifacts
- `report generator`
  - assembles the English report from generated iteration outputs
- `controller or CLI runner`
  - exposes a minimal way to run the whole assignment flow in one action

### 5.2 Single-agent workflow

For each iteration:

1. load shared prior knowledge
2. inject the current iteration goal
3. ask the model to produce structured ADD results
4. ask the same model to perform self-reflection against explicit checks
5. if reflection finds issues, perform one repair pass
6. save the final result, diagram code, and timestamped conversation log

The same logical agent is used across all iterations. Reflection is an internal step of the same agent, not a second agent.

## 6. Model Integration Design

### 6.1 Environment variables

The project will read configuration from environment variables:

- `ARK_API_KEY`: required API key
- `ARK_BASE_URL`: optional, defaults to `https://ark.cn-beijing.volces.com/api/v3`
- `LLM_MODEL`: optional, defaults to `deepseek-v4-pro-260425`

### 6.2 Spring integration usage

The implementation will keep the application stack in Spring Boot and Spring AI Alibaba, while configuring the model endpoint and credentials through environment variables. The user's sample code is treated as the authoritative reference for:

- the target base URL
- the target model name
- the requirement that credentials come from environment variables

The Java-side integration will map these values into Spring configuration and use the Spring AI Alibaba client abstraction to invoke the selected model.

The selected model value will come from `LLM_MODEL`.

### 6.3 About web search tools

The user-provided sample includes a `web_search` tool. Because the assignment explicitly disallows external domain knowledge beyond the provided prior knowledge, the project will not enable web search during assignment execution by default.

This keeps the implementation aligned with the assignment rules while still following the same SDK family and endpoint style.

## 7. Prompting Design

### 7.1 System prompt responsibilities

The system prompt will explicitly define:

- the agent role as a software architecture assistant using ADD 3.0
- the restriction to use only the provided prior knowledge
- the required output structure
- the need to generate Mermaid diagrams
- the need to perform self-reflection before finalizing each iteration

### 7.2 Iteration plan

The four required iterations are:

1. Establishing an Overall System Structure
2. Identifying Structures to Support Primary Functionality
3. Addressing Reliability and Availability Quality Attributes
4. Addressing Development and Operations

### 7.3 Reflection checklist

The reflection prompt will explicitly verify:

- no external knowledge was introduced
- the output aligns with the current iteration goal
- ADD step outputs are complete
- Mermaid code is present
- decisions stay consistent with previous iterations
- the report content is understandable and in English

## 8. Output Format Design

Each iteration will generate:

- structured ADD step outputs
- one Mermaid diagram block
- decision rationale
- identified risks or tradeoffs
- timestamped request and response logs

The report will contain:

- ADD output results for all four iterations
- interaction cost analysis table
- individual reflection template section for the group to finalize

## 9. Error Handling

The implementation should handle:

- missing API key
- missing model configuration
- empty or malformed model response
- file writing failures
- partial failure in one iteration

Behavior:

- fail fast on missing configuration
- save intermediate artifacts after each successful iteration
- write clear console errors for troubleshooting

## 10. Verification Plan

Before claiming completion, verify:

- Maven dependencies resolve successfully
- the project can start from the CLI or main application entry
- configuration loading works
- generated files appear in expected output directories
- edited Java files and resources have no obvious diagnostics

## 11. Scope Boundaries

Included:

- complete Spring AI Alibaba based single-agent source code
- generated deliverable files
- English README and report content

Not included:

- any use of external business knowledge
- multi-agent collaboration
- a large web application UI unless needed for minimal usability

## 12. Chosen Direction

The implementation will use a Spring AI Alibaba based single-agent pipeline with:

- sequential reasoning
- explicit self-reflection
- environment-based model configuration
- DeepSeek endpoint configuration derived from the user's invocation example
- local generation of logs, report, and Mermaid artifacts

This design is sufficient to complete the coursework deliverables while staying aligned with both the assignment rules and the user's additional model integration requirements.
