package com.example.member.service;

import com.example.member.dto.MemberDTO;
import java.util.List;

public interface MemberService {
    MemberDTO createMember(MemberDTO memberDTO);
    MemberDTO getMember(Long id);
    MemberDTO updateMember(Long id, MemberDTO memberDTO);
    void deleteMember(Long id);
    List<MemberDTO> getAllMembers();
    MemberDTO findByEmail(String email);
    boolean isEmailExists(String email);
}
