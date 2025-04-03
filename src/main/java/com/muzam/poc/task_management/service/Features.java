package com.muzam.poc.task_management.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Features {

    private boolean betaAccess;
    private boolean multiFactorAuth;
    private Subscription subscription;
}
