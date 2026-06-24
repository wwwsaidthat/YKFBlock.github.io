package com.course.singleagent.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class OpenAiChatConfigurationTest {

    @Autowired
    private Environment environment;

    @Test
    void shouldUseArkCompatibleChatCompletionPath() {
        assertThat(environment.getProperty("spring.ai.openai.chat.base-url"))
                .isEqualTo("https://ark.cn-beijing.volces.com/api/v3");
        assertThat(environment.getProperty("spring.ai.openai.chat.completions-path"))
                .isEqualTo("/chat/completions");
    }
}
