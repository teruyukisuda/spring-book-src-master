package com.example.member.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Member {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime createdAt;
    private boolean active;
}
