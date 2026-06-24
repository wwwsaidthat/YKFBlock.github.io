package com.course.singleagent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.model")
public record ModelProperties(
        String apiKey,
        String baseUrl,
        String modelName
) {
}
