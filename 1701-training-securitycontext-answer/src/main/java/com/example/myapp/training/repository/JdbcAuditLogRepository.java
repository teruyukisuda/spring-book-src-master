package com.example.myapp.training.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.myapp.training.entity.AuditLog;

@Repository
public class JdbcAuditLogRepository implements AuditLogRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAuditLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(AuditLog auditLog) {
        jdbcTemplate.update("INSERT INTO audit_log VALUES (?,?,?,?)",
                auditLog.getId(),
                auditLog.getFunctionName(),
                auditLog.getEventDateTime(),
                auditLog.getUserId()
                );
    }
}
