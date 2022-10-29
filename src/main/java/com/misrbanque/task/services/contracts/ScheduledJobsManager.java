package com.misrbanque.task.services.contracts;

import com.misrbanque.task.entities.PlotEntity;
import com.misrbanque.task.models.time_slots.EditTimeSlotRequestModel;
import com.misrbanque.task.services.schedulers.ScheduledJob;

import java.util.List;

public interface ScheduledJobsManager {
    List<ScheduledJob> initialize();

    void restartTask(Long plotId, Long timeSlotId);

    void editTask(Long plotId, EditTimeSlotRequestModel newTimeSlotData);

    void deleteTask(Long timeSlotId);

    void addPlotTasks(PlotEntity plotEntities);

    void deletePlotTasks(Long plotId);
}
