package com.example.member.repository;

import com.example.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SELECT_BY_ID =
        "SELECT id, email, password, name, created_at, active FROM members WHERE id = ?";
    private static final String SELECT_BY_EMAIL =
        "SELECT id, email, password, name, created_at, active FROM members WHERE email = ?";
    private static final String INSERT_MEMBER =
        "INSERT INTO members (email, password, name, created_at, active) VALUES (:email, :password, :name, :createdAt, :active)";
    private static final String UPDATE_MEMBER =
        "UPDATE members SET email = :email, name = :name, active = :active WHERE id = :id";
    private static final String DELETE_MEMBER =
        "DELETE FROM members WHERE id = ?";
    private static final String SELECT_ALL =
        "SELECT id, email, password, name, created_at, active FROM members";
    private static final String EXISTS_BY_EMAIL =
        "SELECT COUNT(1) FROM members WHERE email = ?";
    private static final String SEARCH_MEMBERS =
        "SELECT id, email, password, name, created_at, active FROM members WHERE LOWER(email) LIKE LOWER(?) OR LOWER(name) LIKE LOWER(?)";

    private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> {
        Member member = new Member();
        member.setId(rs.getLong("id"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("password"));
        member.setName(rs.getString("name"));
        member.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        member.setActive(rs.getBoolean("active"));
        return member;
    };

    @Override
    public Optional<Member> findById(Long id) {
        try {
            Member member = jdbcTemplate.queryForObject(SELECT_BY_ID, memberRowMapper, id);
            return Optional.ofNullable(member);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        try {
            Member member = jdbcTemplate.queryForObject(SELECT_BY_EMAIL, memberRowMapper, email);
            return Optional.ofNullable(member);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Member save(Member member) {
        if (member.getId() == null) {
            return insert(member);
        } else {
            return update(member);
        }
    }

    private Member insert(Member member) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("email", member.getEmail())
            .addValue("password", member.getPassword())
            .addValue("name", member.getName())
            .addValue("createdAt", LocalDateTime.now())
            .addValue("active", member.isActive());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_MEMBER, params, keyHolder, new String[]{"id"});

        member.setId(keyHolder.getKey().longValue());
        return member;
    }

    private Member update(Member member) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", member.getId())
            .addValue("email", member.getEmail())
            .addValue("name", member.getName())
            .addValue("active", member.isActive());

        namedParameterJdbcTemplate.update(UPDATE_MEMBER, params);
        return member;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_MEMBER, id);
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query(SELECT_ALL, memberRowMapper);
    }

    @Override
    public boolean existsByEmail(String email) {
        Integer count = jdbcTemplate.queryForObject(EXISTS_BY_EMAIL, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public List<Member> search(String keyword) {
        String searchPattern = "%" + keyword + "%";
        return jdbcTemplate.query(SEARCH_MEMBERS, memberRowMapper, searchPattern, searchPattern);
    }
}
