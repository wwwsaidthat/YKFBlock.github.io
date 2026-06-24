package com.course.singleagent.service;

public record ModelResponse(
        String content,
        TokenUsage tokenUsage
) {
    public ModelResponse {
        if (tokenUsage == null) {
            tokenUsage = TokenUsage.empty();
        }
    }
}
