package com.example.member.repository;

import com.example.member.model.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    Member save(Member member);
    void deleteById(Long id);
    List<Member> findAll();
    boolean existsByEmail(String email);
    List<Member> search(String keyword);
}
