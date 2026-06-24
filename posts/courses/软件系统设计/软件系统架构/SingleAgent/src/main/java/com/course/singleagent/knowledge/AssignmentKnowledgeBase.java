package com.course.singleagent.knowledge;

import org.springframework.stereotype.Component;

@Component
public class AssignmentKnowledgeBase {

    public String buildSharedContext() {
        return """
                Attribute-Driven Design (ADD) Method

                Step 1 Review Inputs:
                The first step of the ADD method involves reviewing the inputs and identifying which requirements will be considered as architectural drivers.

                Step 2 Establish the Iteration Goal by Selecting Drivers:
                A design round generally takes the form of a series of design iterations, where each iteration focuses on achieving a particular goal.

                Step 3 Choose One or More Elements of the System to Refine:
                For greenfield development, establish the system context and select the system itself for refinement by decomposition.

                Step 4 Choose One or More Design Concepts That Satisfy the Selected Drivers:
                Identify design concept alternatives and select one or more alternatives that satisfy the iteration goal.

                Step 5 Instantiate Architectural Elements, Allocate Responsibilities, and Define Interfaces:
                Instantiate architectural elements based on selected design concepts and assign responsibilities and interfaces.

                System Under Design:
                Hotel Pricing System

                Design purpose:
                This is greenfield development that replaces an existing system and supports construction from scratch.

                Primary Functionality:
                HPS-1 Log In: credentials are checked against a user identity service and access is limited to authorized hotels.
                HPS-2 Change Prices: authorized users change base rate or fixed rate prices, simulate changes, and publish changes to the Channel Management System.
                HPS-3 Query Prices: a user or an external system queries prices through the user interface or a query API.
                HPS-4 Manage Hotels: an administrator manages hotel information, tax rates, rates, and room types.
                HPS-5 Manage Rates: an administrator manages rates and rate calculation business rules.
                HPS-6 Manage Users: an administrator changes user permissions.

                Quality Attributes:
                QA-1 Performance: price publication after a base rate change must be ready for query in less than 100 ms.
                QA-2 Reliability: 100 percent of price changes must be published successfully and received by the Channel Management System.
                QA-3 Availability: pricing queries uptime SLA must be 99.9 percent outside maintenance windows.
                QA-4 Scalability: initially support 100000 queries per day and scale to 1000000 without average latency decreasing by more than 20 percent.
                QA-5 Security: only authorized functions are presented to each user after login.
                QA-6 Modifiability: support a non-REST query endpoint later without changing core components.
                QA-7 Deployability: application moves across nonproduction environments without code changes.
                QA-8 Monitorability: operation can collect 100 percent of performance and reliability measures for price publication.
                QA-9 Testability: 100 percent of the system and its elements should support integration testing independently of external systems.

                Architectural Concerns:
                CRN-1 Establish an overall initial system structure.
                CRN-2 Leverage the team's knowledge about Java technologies, Angular, and Kafka.
                CRN-3 Allocate work to members of the development team.
                CRN-4 Avoid introducing technical debt.
                CRN-5 Set up a continuous deployment infrastructure.

                Constraints:
                CON-1 Users interact through a web browser on multiple platforms and devices.
                CON-2 Manage users through a cloud provider identity service and host resources in the cloud.
                CON-3 Code must be hosted on the proprietary Git-based platform already used in the company.
                CON-4 Initial release in 6 months and MVP in at most 2 months.
                CON-5 Initial interaction with existing systems is through REST APIs and may later support other protocols.
                CON-6 Favor a cloud-native approach.

                Iteration Plan:
                Iteration 1: Establishing an Overall System Structure
                Iteration 2: Identifying Structures to Support Primary Functionality
                Iteration 3: Addressing Reliability and Availability Quality Attributes
                Iteration 4: Addressing Development and Operations

                Hard rules:
                Use only the provided prior knowledge.
                Do not use external domain knowledge.
                Do not add few-shot examples.
                Do not reinterpret or augment the assignment requirements.
                All decision rules must come from explicit system instructions.
                For the single-agent option, sequential reasoning and self-reflection are allowed.
                Views produced during the iteration should be generated using Mermaid or PlantUML code.
                """;
    }
}
