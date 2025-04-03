package com.muzam.poc.task_management.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEntry {

    private String timestamp;
    private String level;
    private String message;
    private LogContext context;
}
