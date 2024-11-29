package com.example.myapp.training.repository;

import com.example.myapp.training.entity.AuditLog;

public interface AuditLogRepository {
    void insert(AuditLog auditLog);
}
