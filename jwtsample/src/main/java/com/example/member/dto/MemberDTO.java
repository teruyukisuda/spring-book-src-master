package com.example.member.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
}
