package com.muzam.poc.task_management.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogContext {

    private int userId;
    private String ip;
    private String device;
}
