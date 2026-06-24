package com.course.singleagent.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

@Component
public class SpringAiModelGateway implements ModelGateway {

    private final ChatClient chatClient;

    public SpringAiModelGateway(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public ModelResponse generate(String prompt) {
        ChatResponse response = chatClient.prompt(prompt).call().chatResponse();
        return new ModelResponse(response.getResult().getOutput().getText(), extractTokenUsage(response));
    }

    private TokenUsage extractTokenUsage(ChatResponse response) {
        Usage usage = response.getMetadata().getUsage();
        if (usage == null) {
            return TokenUsage.empty();
        }
        return new TokenUsage(
                defaultValue(usage.getPromptTokens()),
                defaultValue(usage.getCompletionTokens()),
                defaultValue(usage.getTotalTokens())
        );
    }

    private int defaultValue(Integer value) {
        return value == null ? 0 : value;
    }
}
