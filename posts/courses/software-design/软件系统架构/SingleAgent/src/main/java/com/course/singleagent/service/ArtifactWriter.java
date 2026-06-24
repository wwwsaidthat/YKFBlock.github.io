package com.course.singleagent.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.singleagent.config.OutputProperties;
import com.course.singleagent.domain.IterationResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ArtifactWriter {

    private final Path rootDir;
    private final ObjectMapper objectMapper;

    @Autowired
    public ArtifactWriter(OutputProperties outputProperties, ObjectMapper objectMapper) {
        this(Path.of(outputProperties.rootDir()), objectMapper);
    }

    public ArtifactWriter(String rootDir) {
        this(Path.of(rootDir), new ObjectMapper());
    }

    public ArtifactWriter(Path rootDir, ObjectMapper objectMapper) {
        this.rootDir = rootDir;
        this.objectMapper = objectMapper;
    }

    public void writeIterationArtifacts(IterationResult result) throws IOException {
        Files.createDirectories(rootDir.resolve("logs"));
        Files.createDirectories(rootDir.resolve("diagrams"));
        Files.createDirectories(rootDir.resolve("json"));

        Files.writeString(rootDir.resolve("logs/iteration-" + result.iterationNumber() + ".md"), buildConversationLog(result));
        Files.writeString(rootDir.resolve("diagrams/iteration-" + result.iterationNumber() + ".mmd"), result.mermaidDiagram());

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("iterationNumber", result.iterationNumber());
        payload.put("title", result.title());
        payload.put("summary", result.summary());
        payload.put("conversationEntries", result.conversationEntries());
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(rootDir.resolve("json/iteration-" + result.iterationNumber() + ".json").toFile(), payload);
    }

    public Path writeReport(String reportContent) throws IOException {
        Files.createDirectories(rootDir.resolve("reports"));
        Path reportPath = rootDir.resolve("reports/final-report.md");
        Files.writeString(reportPath, reportContent);
        return reportPath;
    }

    private String buildConversationLog(IterationResult result) {
        String conversation = result.conversationEntries().stream()
                .map(entry -> """
                        ### %s
                        - Timestamp: %s

                        **Prompt**

                        %s

                        **Response**

                        %s
                        """.formatted(entry.phase(), entry.timestamp(), entry.prompt(), entry.response()))
                .collect(Collectors.joining("\n\n"));

        return """
                # Iteration %d Conversation Log

                ## Title
                %s

                ## Final Result
                %s

                ## Mermaid Diagram
                %s

                ## Conversation
                %s
                """.formatted(
                result.iterationNumber(),
                result.title(),
                result.finalContent(),
                result.mermaidDiagram(),
                conversation
        );
    }
}
