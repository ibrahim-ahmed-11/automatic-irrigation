package com.misrbanque.task.services.contracts;

import com.misrbanque.task.models.time_slots.EditTimeSlotRequestModel;
import com.misrbanque.task.models.time_slots.TimeSlot;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface TimeSlotService {

    TimeSlot editTimeSlot(EditTimeSlotRequestModel requestModel) throws ChangeSetPersister.NotFoundException;

    boolean deleteTimeSlot(Long timeSlotId);

}
