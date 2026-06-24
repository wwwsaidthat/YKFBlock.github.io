package com.course.singleagent.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ModelProperties.class, OutputProperties.class})
public class AppConfig {
}
