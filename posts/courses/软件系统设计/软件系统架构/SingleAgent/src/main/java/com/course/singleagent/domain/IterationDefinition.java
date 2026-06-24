package com.course.singleagent.domain;

import java.util.List;

public record IterationDefinition(
        int number,
        String title,
        String goal
) {

    public static IterationDefinition iteration1() {
        return new IterationDefinition(1, "Establishing an Overall System Structure",
                "Focus on the overall system structure and the primary architectural drivers.");
    }

    public static IterationDefinition iteration2() {
        return new IterationDefinition(2, "Identifying Structures to Support Primary Functionality",
                "Refine the structure to support login, price change, price query, hotel management, rate management, and user management.");
    }

    public static IterationDefinition iteration3() {
        return new IterationDefinition(3, "Addressing Reliability and Availability Quality Attributes",
                "Refine the design to satisfy reliability, availability, performance, and observability concerns.");
    }

    public static IterationDefinition iteration4() {
        return new IterationDefinition(4, "Addressing Development and Operations",
                "Refine the design to support deployability, testability, monitorability, and development workflow concerns.");
    }

    public static List<IterationDefinition> all() {
        return List.of(iteration1(), iteration2(), iteration3(), iteration4());
    }
}
