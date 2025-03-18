package ru.absolutins.camundaFirstApp.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class ProcessController {

    private final RuntimeService runtimeService;

    private ProcessController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping("/start-process")
    public ResponseEntity<?> startProcess(@RequestBody Map<String, Object> request) {
        String email = (String) request.get("email");

        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                "CatImageProcess",
                Variables.putValue("email", email)
        );

        return ResponseEntity.ok().body(
                Collections.singletonMap("processId", instance.getId())
        );
    }

}
