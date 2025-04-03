package com.muzam.poc.task_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String entityType;
    private String entityId;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Map<String, Object> entityDetails;  // Dynamic entity-specific fields
}