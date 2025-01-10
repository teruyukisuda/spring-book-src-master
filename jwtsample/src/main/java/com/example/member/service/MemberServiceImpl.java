package com.example.member.service;

import com.example.member.dto.MemberDTO;
import com.example.member.model.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MemberDTO createMember(MemberDTO memberDTO) {
        if (memberRepository.existsByEmail(memberDTO.getEmail())) {
            throw new IllegalStateException("Email already exists: " + memberDTO.getEmail());
        }

        Member member = new Member();
        member.setEmail(memberDTO.getEmail());
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.setName(memberDTO.getName());
        member.setCreatedAt(LocalDateTime.now());
        member.setActive(true);

        Member savedMember = memberRepository.save(member);
        return convertToDTO(savedMember);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDTO getMember(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Member not found with id: " + id));
        return convertToDTO(member);
    }

    @Override
    @Transactional
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        Member existingMember = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Member not found with id: " + id));

        // メールアドレスが変更される場合、重複チェック
        if (!existingMember.getEmail().equals(memberDTO.getEmail()) &&
            memberRepository.existsByEmail(memberDTO.getEmail())) {
            throw new IllegalStateException("Email already exists: " + memberDTO.getEmail());
        }

        existingMember.setEmail(memberDTO.getEmail());
        existingMember.setName(memberDTO.getName());
        // パスワードが提供された場合のみ更新
        if (memberDTO.getPassword() != null && !memberDTO.getPassword().isEmpty()) {
            existingMember.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        }

        Member updatedMember = memberRepository.save(existingMember);
        return convertToDTO(updatedMember);
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.findById(id).isPresent()) {
            throw new IllegalStateException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDTO findByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalStateException("Member not found with email: " + email));
        return convertToDTO(member);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        return memberRepository.existsByEmail(email);
    }

    private MemberDTO convertToDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setEmail(member.getEmail());
        dto.setName(member.getName());
        // パスワードはDTOに含めない
        return dto;
    }
}
