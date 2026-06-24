package com.course.singleagent.service;

import com.course.singleagent.domain.ConversationEntry;
import com.course.singleagent.domain.IterationDefinition;
import com.course.singleagent.domain.IterationResult;
import com.course.singleagent.prompt.PromptFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SingleAgentRunner {

    private final PromptFactory promptFactory;
    private final TimestampService timestampService;
    private final ModelGateway modelGateway;

    public SingleAgentRunner(PromptFactory promptFactory, TimestampService timestampService, ModelGateway modelGateway) {
        this.promptFactory = promptFactory;
        this.timestampService = timestampService;
        this.modelGateway = modelGateway;
    }

    public List<IterationResult> runAllIterations() {
        List<IterationResult> results = new ArrayList<>();
        String priorSummary = "";
        for (IterationDefinition definition : IterationDefinition.all()) {
            IterationResult result = runIteration(definition, priorSummary);
            results.add(result);
            priorSummary = result.summary();
        }
        return results;
    }

    public IterationResult runIteration(IterationDefinition definition, String priorSummary) {
        String prompt = promptFactory.buildIterationPrompt(definition, priorSummary);
        ModelResponse response = modelGateway.generate(prompt);
        String draft = response.content();
        String reflection = promptFactory.buildReflectionPrompt(draft, definition);
        List<ConversationEntry> entries = List.of(
                new ConversationEntry("generation", timestampService.now(), prompt, draft),
                new ConversationEntry("reflection", timestampService.now(), reflection, "Reflection completed.")
        );
        String diagram = extractMermaidDiagram(draft);
        return new IterationResult(
                definition.number(),
                definition.title(),
                draft,
                diagram,
                "Summary for iteration " + definition.number(),
                entries,
                response.tokenUsage()
        );
    }

    private String extractMermaidDiagram(String draft) {
        String startMarker = "```mermaid";
        int start = draft.indexOf(startMarker);
        if (start < 0) {
            return """
                    ```mermaid
                    graph TD
                        A[Iteration %s] --> B[No Mermaid diagram returned]
                    ```
                    """.formatted("Result");
        }

        int end = draft.indexOf("```", start + startMarker.length());
        if (end < 0) {
            return draft.substring(start);
        }
        return draft.substring(start, end + 3);
    }
}
