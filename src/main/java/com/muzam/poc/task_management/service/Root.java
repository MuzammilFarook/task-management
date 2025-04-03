package com.muzam.poc.task_management.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Root {
    private Metadata metadata;
    private List<User> users;
    private Settings settings;
    private List<LogEntry> logs;
}