package com.muzam.poc.task_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.muzam.poc.task_management.dto.TaskResponse;
import com.muzam.poc.task_management.repository.model.Task;
import com.muzam.poc.task_management.service.JsonSchemaValidator;
import com.muzam.poc.task_management.service.Root;
import com.muzam.poc.task_management.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping("/validate")
    public ResponseEntity<String> validateJSON(@RequestBody Map<String, Object> request) {
        try {
            String jsonString = new ObjectMapper().writeValueAsString(request);
            JsonSchemaValidator.validate(jsonString);
            return ResponseEntity.ok("Valid JSON");
        } catch (ProcessingException | IOException e) {
            return ResponseEntity.badRequest().body("Invalid JSON: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<TaskResponse> getTaskWithDetails(
            @RequestParam String entityType,
            @RequestParam String entityId) {

        TaskResponse response = taskService.getTaskWithEntityDetails(entityType, entityId);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Root root = objectMapper.convertValue(response.getEntityDetails(), Root.class);
            System.out.println(root.getUsers().get(1).getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Task> createTask() {
        Task task = taskService.createTask();
        return ResponseEntity.ok(task);
    }
}