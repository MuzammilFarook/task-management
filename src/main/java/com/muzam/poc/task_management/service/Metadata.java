package com.muzam.poc.task_management.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    private String generatedAt;
    private String requestId;
    private String source;
    private Pagination pagination;
}
