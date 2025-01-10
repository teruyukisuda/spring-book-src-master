package com.example.member.security;

import com.example.member.model.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Attempting to load user by username: {}", username);

        Member member = memberRepository.findByEmail(username)
            .orElseThrow(() -> {
                log.error("User not found with email: {}", username);
                return new UsernameNotFoundException("User not found with email: " + username);
            });

        log.debug("User found: {}", member.getEmail());

        return User.builder()
            .username(member.getEmail())
            .password(member.getPassword())
            .roles("USER")
            .build();
    }
}
