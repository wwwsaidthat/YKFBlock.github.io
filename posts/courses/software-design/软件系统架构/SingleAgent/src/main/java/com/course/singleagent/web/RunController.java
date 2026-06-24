package com.course.singleagent.web;

import com.course.singleagent.domain.RunSummary;
import com.course.singleagent.service.AssignmentExecutionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RunController {

    private final AssignmentExecutionService assignmentExecutionService;

    public RunController(AssignmentExecutionService assignmentExecutionService) {
        this.assignmentExecutionService = assignmentExecutionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("statusMessage")) {
            model.addAttribute("statusMessage", "Ready to run all four ADD iterations.");
        }
        return "index";
    }

    @PostMapping("/run")
    public String run(RedirectAttributes redirectAttributes) throws Exception {
        try {
            RunSummary summary = assignmentExecutionService.executeAll();
            redirectAttributes.addFlashAttribute("statusMessage",
                    "Execution completed. Final report: " + summary.reportPath());
        }
        catch (IllegalStateException exception) {
            redirectAttributes.addFlashAttribute("statusMessage", "Execution failed: " + exception.getMessage());
        }
        return "redirect:/";
    }
}
