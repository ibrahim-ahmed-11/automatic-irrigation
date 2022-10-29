package com.misrbanque.task.services;

import com.misrbanque.task.entities.TimeSlotEntity;
import com.misrbanque.task.models.time_slots.EditTimeSlotRequestModel;
import com.misrbanque.task.models.time_slots.TimeSlot;
import com.misrbanque.task.repositories.TimeSlotsRepository;
import com.misrbanque.task.services.contracts.ScheduledJobsManager;
import com.misrbanque.task.services.contracts.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotsRepository timeSlotsRepository;
    private final ScheduledJobsManager jobsManager;

    @Override
    public TimeSlot editTimeSlot(EditTimeSlotRequestModel requestModel) throws ChangeSetPersister.NotFoundException {
        TimeSlotEntity timeSlotEntity = timeSlotsRepository.findById(requestModel.getTimeSlotId()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        timeSlotEntity.fromModel(requestModel);

        timeSlotsRepository.save(timeSlotEntity);
        timeSlotsRepository.refresh(timeSlotEntity);

        jobsManager.editTask(timeSlotEntity.getPlot().getId(), requestModel);

        return new TimeSlot().fromEntity(timeSlotEntity);
    }

    @Override
    public boolean deleteTimeSlot(Long timeSlotId) {
        jobsManager.deleteTask(timeSlotId);
        timeSlotsRepository.deleteById(timeSlotId);

        return true;
    }
}
