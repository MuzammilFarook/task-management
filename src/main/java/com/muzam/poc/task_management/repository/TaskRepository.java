package com.muzam.poc.task_management.repository;

import com.muzam.poc.task_management.repository.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByEntityTypeAndEntityId(String entityType, String entityId);
}