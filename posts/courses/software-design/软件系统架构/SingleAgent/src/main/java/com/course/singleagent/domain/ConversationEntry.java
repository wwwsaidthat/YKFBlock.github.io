package com.course.singleagent.domain;

public record ConversationEntry(
        String phase,
        String timestamp,
        String prompt,
        String response
) {
}
