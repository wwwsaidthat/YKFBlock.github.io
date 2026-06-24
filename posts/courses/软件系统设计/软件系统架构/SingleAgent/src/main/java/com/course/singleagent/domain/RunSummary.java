package com.course.singleagent.domain;

import com.course.singleagent.service.RunMetrics;
import java.util.List;

public record RunSummary(
        List<IterationResult> iterationResults,
        String reportPath,
        RunMetrics metrics
) {
}
