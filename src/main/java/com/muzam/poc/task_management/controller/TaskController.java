package com.muzam.poc.task_management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muzam.poc.task_management.dto.TaskResponse;
import com.muzam.poc.task_management.repository.model.Task;
import com.muzam.poc.task_management.service.Root;
import com.muzam.poc.task_management.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

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