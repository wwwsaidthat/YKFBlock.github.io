package com.course.singleagent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.output")
public record OutputProperties(String rootDir) {
}
