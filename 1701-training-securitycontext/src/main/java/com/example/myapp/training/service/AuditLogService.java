package com.example.myapp.training.service;

public interface AuditLogService {

    void registerLog(String functionName, String userId);
}
