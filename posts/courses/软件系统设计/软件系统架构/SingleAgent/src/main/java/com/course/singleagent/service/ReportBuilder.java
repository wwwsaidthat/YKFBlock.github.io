package com.course.singleagent.service;

import com.course.singleagent.domain.IterationResult;
import com.course.singleagent.domain.RunSummary;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReportBuilder {

    public String buildReport(RunSummary summary) {
        AtomicInteger displayIndex = new AtomicInteger(1);
        String iterationSections = summary.iterationResults().stream()
                .map(result -> buildIterationSection(displayIndex.getAndIncrement(), result))
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
}
