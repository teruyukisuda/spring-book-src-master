package com.example.myapp.repository;

import com.example.myapp.common.util.TrainingMapper;
import com.example.myapp.repository.entity.TrainingEntity;
import com.example.myapp.view.TrainingDto;
import java.util.List;
import java.util.UUID;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TrainingMapper trainingMapper;

    public TrainingRepositoryImpl(JdbcTemplate jdbcTemplate, TrainingMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.trainingMapper = mapper;
    }

    public List<TrainingDto> selectAll() {
        List<TrainingEntity> trainings = jdbcTemplate.query("select * from training",
            new DataClassRowMapper<>(TrainingEntity.class) {
            });
        return trainingMapper.entityToDto(trainings);
    }

    public TrainingDto selectById(String id) {
        TrainingEntity entity = jdbcTemplate.queryForObject("select * from training where id = ?",
            new BeanPropertyRowMapper<>(TrainingEntity.class), id);
        return trainingMapper.entityToDto(entity);
    }

    public boolean insert(TrainingDto trainingDto) {
        TrainingEntity trainingEntity = trainingMapper.dtoToEntity(trainingDto);
        String uuid = UUID.randomUUID().toString();
        int count = jdbcTemplate.update("INSERT INTO training \n"
                + "    (id, title, start_date_time, end_date_time, reserved, capacity)\n"
                + "VALUES (?,?,?,?,?,?)",
            uuid, trainingEntity.getTitle(), trainingEntity.getStart_date_time(),
            trainingEntity.getEnd_date_time(), trainingEntity.getReserved(),
            trainingEntity.getCapacity());

        return count > 0;
    }
}
