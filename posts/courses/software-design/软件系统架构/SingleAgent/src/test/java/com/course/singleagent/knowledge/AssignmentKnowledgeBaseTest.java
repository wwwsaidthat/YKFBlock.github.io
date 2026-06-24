package com.course.singleagent.knowledge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AssignmentKnowledgeBaseTest {

    @Test
    void shouldContainOnlyAssignmentKnowledge() {
        AssignmentKnowledgeBase knowledgeBase = new AssignmentKnowledgeBase();
        String promptContext = knowledgeBase.buildSharedContext();

        assertThat(promptContext).contains("Attribute-Driven Design");
        assertThat(promptContext).contains("Hotel Pricing System");
        assertThat(promptContext).contains("Iteration 1");
    }
}
