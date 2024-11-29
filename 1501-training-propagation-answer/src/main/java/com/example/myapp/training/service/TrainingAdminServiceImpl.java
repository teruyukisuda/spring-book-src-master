
package com.example.myapp.training.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.training.entity.Training;
import com.example.myapp.training.input.TrainingAdminInput;
import com.example.myapp.training.repository.TrainingRepository;

@Service
public class TrainingAdminServiceImpl implements TrainingAdminService {

	private final TrainingRepository trainingRepository;
    private final AuditLogService auditLogService;

	public TrainingAdminServiceImpl(AuditLogService auditLogService, TrainingRepository trainingRepository) {
		this.trainingRepository = trainingRepository;
        this.auditLogService = auditLogService;
	}

    @Transactional
	@Override
	public Training register(TrainingAdminInput trainingAdminInput) {
        auditLogService.registerLog("研修登録");

		Training training = new Training();
		training.setId(trainingAdminInput.getId());
		training.setTitle(trainingAdminInput.getTitle());
		training.setStartDateTime(trainingAdminInput.getStartDateTime());
		training.setEndDateTime(trainingAdminInput.getEndDateTime());
		training.setReserved(trainingAdminInput.getReserved());
		training.setCapacity(trainingAdminInput.getCapacity());
		trainingRepository.insert(training);
		return training;
	}
}
