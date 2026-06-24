package com.course.singleagent.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.course.singleagent.domain.IterationResult;
import com.course.singleagent.knowledge.AssignmentKnowledgeBase;
import com.course.singleagent.prompt.PromptFactory;
import java.util.List;
import org.junit.jupiter.api.Test;

class SingleAgentRunnerTest {

    @Test
    void shouldProduceFourIterations() {
        ModelGateway gateway = prompt -> new ModelResponse("mock response", new TokenUsage(10, 20, 30));
        PromptFactory promptFactory = new PromptFactory(new AssignmentKnowledgeBase());
        SingleAgentRunner runner = new SingleAgentRunner(promptFactory, new TimestampService(), gateway);

        List<IterationResult> results = runner.runAllIterations();

        assertThat(results).hasSize(4);
        assertThat(results.get(0).finalContent()).contains("mock response");
        assertThat(results.get(0).tokenUsage().totalTokens()).isEqualTo(30);
    }
}
