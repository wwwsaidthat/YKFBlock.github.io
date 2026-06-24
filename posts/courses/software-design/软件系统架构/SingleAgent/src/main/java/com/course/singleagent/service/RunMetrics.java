package com.course.singleagent.service;

public record RunMetrics(
        String modelName,
        int humanInteractionTurns,
        int promptTokens,
        int completionTokens,
        int totalTokens,
        long elapsedSeconds
) {
    public double totalTokensInK() {
        return totalTokens / 1000.0;
    }

    public double elapsedMinutes() {
        return elapsedSeconds / 60.0;
    }
}
