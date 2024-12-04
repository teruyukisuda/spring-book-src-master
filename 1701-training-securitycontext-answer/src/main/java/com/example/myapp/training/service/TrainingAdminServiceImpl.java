package com.example.myapp.training.service;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.input.TrainingAdminInput;
import com.example.myapp.training.repository.TrainingRepository;

@Service
@Transactional
public class TrainingAdminServiceImpl implements TrainingAdminService {

	private final TrainingRepository trainingRepository;
    private final AuditLogService auditLogService;

	public TrainingAdminServiceImpl(TrainingRepository trainingRepository, AuditLogService auditLogService) {
		this.trainingRepository = trainingRepository;
        this.auditLogService = auditLogService;
    }

	@Override
	public List<Training> findAll() {
		return trainingRepository.selectAll();
	}

	@Override
	public Training findById(String trainingId) {
		return trainingRepository.selectById(trainingId);
	}

	@Override
	public void update(TrainingAdminInput trainingAdminInput) {
        auditLogService.registerLog("研修更新");
        Training training = new Training();
        training.setId(trainingAdminInput.getId());
        training.setTitle(trainingAdminInput.getTitle());
        training.setStartDateTime(trainingAdminInput.getStartDateTime());
        training.setEndDateTime(trainingAdminInput.getEndDateTime());
        training.setReserved(trainingAdminInput.getReserved());
        training.setCapacity(trainingAdminInput.getCapacity());
        boolean result = trainingRepository.update(training);
        if (!result) {
            throw new OptimisticLockingFailureException("すでに削除された可能性があります。id=" + training.getId());
        }
    }
}