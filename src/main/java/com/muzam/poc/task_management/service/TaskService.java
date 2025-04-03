package com.muzam.poc.task_management.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muzam.poc.task_management.dto.TaskResponse;
import com.muzam.poc.task_management.repository.TaskRepository;
import com.muzam.poc.task_management.repository.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ObjectMapper objectMapper;

    public TaskResponse getTaskWithEntityDetails(String entityType, String entityId) {
        Optional<Task> taskOpt = taskRepository.findByEntityTypeAndEntityId(entityType, entityId);

        if (taskOpt.isEmpty()) {
            throw new RuntimeException("Task not found for entityType: " + entityType + ", entityId: " + entityId);
        }

        Task task = taskOpt.get();

        // Return the response DTO
        var entityDetails = new HashMap<String, Object>();
        return new TaskResponse(
                task.getId(),
                task.getEntityType(),
                task.getEntityId(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                convertClobToMap(task.getTaskContent().toString())
        );
    }

    public static Map<String, Object> convertClobToMap(String jsonClob) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonClob, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON CLOB to Map", e);
        }
    }


    public Task createTask() {
        Task taskRequest = new Task();
        taskRequest.setEntityId("12345");
        taskRequest.setEntityType("CAR");
        taskRequest.setStatus("SUBMITTED");
        taskRequest.setCreatedAt(LocalDateTime.now());
        taskRequest.setUpdatedAt(LocalDateTime.now());
        try {
            taskRequest.setTaskContent(new ObjectMapper().writeValueAsString(createSampleData()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return taskRepository.save(taskRequest);
    }

    private Root createSampleData() {
        return new Root(
                createMetadata(),
                createUsers(),
                createSettings(),
                createLogs()
        );
    }

    private Metadata createMetadata() {
        return new Metadata("2025-04-03T14:30:00Z", UUID.randomUUID().toString(), "SystemX",
                new Pagination(1, 50, 10));
    }

    private List<User> createUsers() {

        return List.of(
                new User(1, "John Doe", "john.doe@example.com", List.of("admin", "editor"),
                        createProfile(30, "male", "123 Main St", "New York", "USA", 40.7128, -74.0060),
                        new Preferences(true, "dark", "en-US"),
                        List.of(new Transaction("TXN1001", 250.75, "USD", "2025-04-02T12:00:00Z",
                                List.of(new Item("P001", "Laptop", 1200.99, 1),
                                        new Item("P002", "Mouse", 25.50, 2))))),

        new User(2, "Jane Smith", "jane.smith@example.com", List.of("user"),
                createProfile(28, "female", "456 Oak St", "Los Angeles", "USA", 34.0522, -118.2437),
                new Preferences(false, "light", "fr-FR"),
                List.of(new Transaction("TXN2001", 75.99, "USD", "2025-04-01T15:45:00Z",
                        List.of(new Item("P005", "Book", 15.99, 3),
                                new Item("P006", "Pen Set", 10.00, 2)))))
    );
    }

    private Profile createProfile(int age, String gender, String street, String city, String country, double lat, double lon) {
        return new Profile(age, gender, new Address(street, city, country, new Coordinates(lat, lon)));
    }

    private Settings createSettings() {
        return new Settings("2.3.5",
                new Features(true, true,
                        new Subscription("Premium", "2025-06-15T00:00:00Z",
                                List.of("unlimited storage", "priority support", "AI assistant"))));
    }

    private List<LogEntry> createLogs() {
        return List.of(
                new LogEntry("2025-04-02T08:15:00Z", "INFO", "User logged in",
                        new LogContext(1, "192.168.1.10", "Chrome on Windows 11")),
                new LogEntry("2025-04-02T09:30:00Z", "WARNING", "Failed login attempt",
                        new LogContext(2, "192.168.1.11", "Firefox on MacOS"))
        );
    }




}


