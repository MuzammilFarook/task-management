package com.muzam.poc.task_management.repository.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityType;
    private String entityId;
    private String status;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String taskContent;  // Store CLOB data in H2

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}