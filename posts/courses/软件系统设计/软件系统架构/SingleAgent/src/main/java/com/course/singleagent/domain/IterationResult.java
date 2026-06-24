package com.course.singleagent.domain;

import com.course.singleagent.service.TokenUsage;
import java.util.List;

public record IterationResult(
        int iterationNumber,
        String title,
        String finalContent,
        String mermaidDiagram,
        String summary,
        List<ConversationEntry> conversationEntries,
        TokenUsage tokenUsage
) {
}
