package com.course.singleagent.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.course.singleagent.config.ModelProperties;
import com.course.singleagent.domain.IterationResult;
import com.course.singleagent.domain.RunSummary;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AssignmentExecutionServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldAggregateRunMetricsIntoSummary() throws Exception {
        SingleAgentRunner runner = new SingleAgentRunner(
                null,
                null,
                prompt -> new ModelResponse("unused", new TokenUsage(0, 0, 0))
        ) {
            @Override
            public List<IterationResult> runAllIterations() {
                return List.of(
                        new IterationResult(1, "Iteration 1", "body 1", "diagram 1", "summary 1", List.of(), new TokenUsage(10, 20, 30)),
                        new IterationResult(2, "Iteration 2", "body 2", "diagram 2", "summary 2", List.of(), new TokenUsage(15, 25, 40))
                );
            }
        };
        ArtifactWriter writer = new ArtifactWriter(tempDir.toString());
        ReportBuilder reportBuilder = new ReportBuilder();
        AssignmentExecutionService service = new AssignmentExecutionService(
                runner,
                writer,
                reportBuilder,
                new ModelProperties("key", "https://example.com", "deepseek-v4-pro-260425")
        );

        RunSummary summary = service.executeAll();

        assertThat(summary.metrics().humanInteractionTurns()).isEqualTo(1);
        assertThat(summary.metrics().promptTokens()).isEqualTo(25);
        assertThat(summary.metrics().completionTokens()).isEqualTo(45);
        assertThat(summary.metrics().totalTokens()).isEqualTo(70);
        assertThat(summary.reportPath()).endsWith("reports\\final-report.md");
    }
}
