package com.course.singleagent.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.course.singleagent.domain.RunSummary;
import com.course.singleagent.service.RunMetrics;
import com.course.singleagent.service.TokenUsage;
import com.course.singleagent.service.AssignmentExecutionService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RunController.class)
class RunControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentExecutionService assignmentExecutionService;

    @Test
    void shouldRenderIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void shouldTriggerRun() throws Exception {
        when(assignmentExecutionService.executeAll())
                .thenReturn(new RunSummary(
                        List.of(),
                        "outputs/reports/final-report.md",
                        new RunMetrics("deepseek-v4-pro-260425", 1, 0, 0, 0, 0)
                ));

        mockMvc.perform(post("/run"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void shouldShowFriendlyMessageWhenRunFails() throws Exception {
        when(assignmentExecutionService.executeAll())
                .thenThrow(new IllegalStateException("ARK_API_KEY must be provided through environment variables before running the assignment."));

        mockMvc.perform(post("/run"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("statusMessage",
                        "Execution failed: ARK_API_KEY must be provided through environment variables before running the assignment."));
    }
}
