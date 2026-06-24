package com.course.singleagent.service;

@FunctionalInterface
public interface ModelGateway {

    ModelResponse generate(String prompt);
}
