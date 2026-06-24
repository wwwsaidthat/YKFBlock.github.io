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
                        new IterationResult(
                                1,
                                "Iteration 1",
                                "Iteration 1 body",
                                "diagram",
                                "summary",
                                List.of(),
                                new TokenUsage(20, 40, 60)
                        ),
                        new IterationResult(
                                2,
                                "Iteration 2",
                                "Iteration 2 body",
                                "diagram",
                                "summary",
                                List.of(),
                                new TokenUsage(30, 50, 80)
                        )
                ),
                "outputs/reports/final-report.md",
                new RunMetrics("deepseek-v4-pro-260425", 1, 50, 90, 140, 120)
        );
        String report = builder.buildReport(summary);

        assertThat(report).contains("### 1）Iteration 1: Iteration 1");
        assertThat(report).contains("### 2）Iteration 2: Iteration 2");
        assertThat(report).contains("The following results are reproduced from the completed single-agent run. Each iteration records the full ADD reasoning outcome for one refinement cycle.");
        assertThat(report).contains("The figures above come from the successful real run executed in the current local environment.");
        assertThat(report).contains("Replace the placeholders below with the actual student names and contributions before final submission.");
        assertThat(report).doesNotContain("### 1）Output results of each step");
        assertThat(report).doesNotContain("ADD Step 2:");
        assertThat(report).contains("| Token Consumption（K tokens） | 0.14 |");
        assertThat(report).contains("| Time Cost（min） | 2.00 |");
    }
}
