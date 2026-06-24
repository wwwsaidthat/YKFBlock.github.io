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
