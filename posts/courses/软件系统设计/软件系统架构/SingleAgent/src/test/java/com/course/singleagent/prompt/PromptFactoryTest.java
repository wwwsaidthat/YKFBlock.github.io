package com.course.singleagent.prompt;

import static org.assertj.core.api.Assertions.assertThat;

import com.course.singleagent.domain.IterationDefinition;
import com.course.singleagent.knowledge.AssignmentKnowledgeBase;
import org.junit.jupiter.api.Test;

class PromptFactoryTest {

    @Test
    void shouldBuildPromptWithReflectionRules() {
        PromptFactory factory = new PromptFactory(new AssignmentKnowledgeBase());
        String prompt = factory.buildIterationPrompt(IterationDefinition.iteration1(), "");

        assertThat(prompt).contains("self-reflection");
        assertThat(prompt).contains("Mermaid");
        assertThat(prompt).contains("ADD Step 2");
    }
}
