package com.course.singleagent.prompt;

import com.course.singleagent.domain.IterationDefinition;
import com.course.singleagent.knowledge.AssignmentKnowledgeBase;
import org.springframework.stereotype.Component;

@Component
public class PromptFactory {

    private final AssignmentKnowledgeBase knowledgeBase;

    public PromptFactory(AssignmentKnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public String buildIterationPrompt(IterationDefinition definition, String priorSummary) {
        return """
                You are a single-agent software architecture assistant using ADD 3.0.
                Use only the provided prior knowledge.
                Produce outputs in English.
                Cover ADD Step 2 to ADD Step 7 explicitly.
                Include Mermaid code in the result.
                Perform self-reflection before finalizing the answer.

                Shared prior knowledge:
                %s

                Current iteration:
                %s

                Iteration goal:
                %s

                Previous decisions:
                %s
                """.formatted(
                knowledgeBase.buildSharedContext(),
                definition.title(),
                definition.goal(),
                priorSummary == null || priorSummary.isBlank() ? "None" : priorSummary
        );
    }

    public String buildReflectionPrompt(String draftOutput, IterationDefinition definition) {
        return """
                Perform self-reflection on the draft result for iteration %d.
                Verify that:
                1. no external knowledge was introduced;
                2. ADD Step 2 to Step 7 are all covered;
                3. Mermaid code is present;
                4. the result is consistent with the current iteration goal;
                5. the result is clear and in English.

                Draft output:
                %s
                """.formatted(definition.number(), draftOutput);
    }
}
