package com.course.singleagent.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.course.singleagent.domain.IterationResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ArtifactWriterTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldWriteIterationArtifacts() throws Exception {
        ArtifactWriter writer = new ArtifactWriter(tempDir.toString());
        IterationResult result = new IterationResult(
                1,
                "Iteration 1",
                "content",
                "```mermaid\ngraph TD\nA-->B\n```",
                "summary",
                List.of(),
                TokenUsage.empty()
        );

        writer.writeIterationArtifacts(result);

        assertThat(Files.exists(tempDir.resolve("logs/iteration-1.md"))).isTrue();
        assertThat(Files.exists(tempDir.resolve("diagrams/iteration-1.mmd"))).isTrue();
        assertThat(Files.exists(tempDir.resolve("json/iteration-1.json"))).isTrue();
    }
}
