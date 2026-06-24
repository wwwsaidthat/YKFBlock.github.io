package com.course.singleagent.service;

import com.course.singleagent.config.ModelProperties;
import com.course.singleagent.domain.IterationResult;
import com.course.singleagent.domain.RunSummary;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AssignmentExecutionService {

    private final SingleAgentRunner singleAgentRunner;
    private final ArtifactWriter artifactWriter;
    private final ReportBuilder reportBuilder;
    private final ModelProperties modelProperties;

    public AssignmentExecutionService(
            SingleAgentRunner singleAgentRunner,
            ArtifactWriter artifactWriter,
            ReportBuilder reportBuilder,
            ModelProperties modelProperties
    ) {
        this.singleAgentRunner = singleAgentRunner;
        this.artifactWriter = artifactWriter;
        this.reportBuilder = reportBuilder;
        this.modelProperties = modelProperties;
    }

    public RunSummary executeAll() throws IOException {
        validateModelConfiguration();

        long startedAt = System.nanoTime();
        List<IterationResult> results = singleAgentRunner.runAllIterations();
        for (IterationResult result : results) {
            artifactWriter.writeIterationArtifacts(result);
        }

        RunMetrics metrics = buildRunMetrics(results, startedAt);
        RunSummary summary = new RunSummary(results, "", metrics);
        String reportContent = reportBuilder.buildReport(summary);
        Path reportPath = artifactWriter.writeReport(reportContent);
        return new RunSummary(results, reportPath.toString(), metrics);
    }

    private RunMetrics buildRunMetrics(List<IterationResult> results, long startedAt) {
        int promptTokens = results.stream().map(IterationResult::tokenUsage).mapToInt(TokenUsage::promptTokens).sum();
        int completionTokens = results.stream().map(IterationResult::tokenUsage).mapToInt(TokenUsage::completionTokens).sum();
        int totalTokens = results.stream().map(IterationResult::tokenUsage).mapToInt(TokenUsage::totalTokens).sum();
        long elapsedSeconds = (System.nanoTime() - startedAt) / 1_000_000_000L;
        return new RunMetrics(
                modelProperties.modelName(),
                1,
                promptTokens,
                completionTokens,
                totalTokens,
                elapsedSeconds
        );
    }

    private void validateModelConfiguration() {
        if (modelProperties.apiKey() == null || modelProperties.apiKey().isBlank() || "test-api-key".equals(modelProperties.apiKey())) {
            throw new IllegalStateException("ARK_API_KEY must be provided through environment variables before running the assignment.");
        }
    }
}
