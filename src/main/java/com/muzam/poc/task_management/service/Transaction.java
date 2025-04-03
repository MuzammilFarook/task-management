package com.muzam.poc.task_management.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String id;
    private double amount;
    private String currency;
    private String timestamp;
    private List<Item> items;
}
