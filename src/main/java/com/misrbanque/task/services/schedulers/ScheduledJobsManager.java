package com.misrbanque.task.services.schedulers;

import com.misrbanque.task.entities.PlotEntity;
import com.misrbanque.task.models.time_slots.EditTimeSlotRequestModel;
import com.misrbanque.task.repositories.PlotsRepository;
import com.misrbanque.task.services.contracts.SensorIntegrationService;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ScheduledJobsManager {

    private final PlotsRepository plotsRepository;
    private final SensorIntegrationService sensorIntegrationService;
    @Getter
    private final List<ScheduledJob> scheduledJobs;


    public ScheduledJobsManager(PlotsRepository plotsRepository, SensorIntegrationService sensorIntegrationService) {
        this.plotsRepository = plotsRepository;
        this.sensorIntegrationService = sensorIntegrationService;
        this.scheduledJobs = initialize();
    }

    public List<ScheduledJob> initialize() {

        List<PlotEntity> plots = plotsRepository.findAll();
        List<ScheduledJob> scheduledJobs = new ArrayList<>();

        plots.forEach(plotEntity -> {
            plotEntity.getTimeSlots().forEach(timeSlot -> {
                try {
                    ScheduledJob scheduledJob = new ScheduledJob(
                            plotEntity.getId(),
                            timeSlot.getId(),
                            timeSlot.getStartTime(),
                            timeSlot.getDurationInMinutes(),
                            timeSlot.getWaterAmount(),
                            sensorIntegrationService
                    );
                    scheduledJobs.add(scheduledJob);
                    scheduledJob.run();

                } catch (Exception ex) {
                    log.error("Failed to start job for plot : " + plotEntity.getName());
                    log.error(ex.getMessage());
                }

            });
        });

        return scheduledJobs;
    }

    private void startTask(Long plotId, Long timeSlotId) {
        getTasks(plotId, timeSlotId).forEach(ScheduledJob::run);
    }

    private void stopTask(Long plotId, Long timeSlotId) {
        getTasks(plotId, timeSlotId).forEach(ScheduledJob::shutdown);
    }

    private void stopTask(Long timeSlotId) {
        getTasks(scheduledJob -> scheduledJob.getTimeSlotId().equals(timeSlotId)).forEach(ScheduledJob::shutdown);
    }

    public void restartTask(Long plotId, Long timeSlotId) {
        stopTask(plotId, timeSlotId);
        startTask(plotId, timeSlotId);
    }

    private List<ScheduledJob> getTasks(Predicate<? super ScheduledJob> predicate) {
        return scheduledJobs.stream().filter(predicate).collect(Collectors.toList());
    }

    private List<ScheduledJob> getTasks(Long plotId, Long timeSlotId) {
        return scheduledJobs.stream().filter(scheduledJob -> scheduledJob.getPlotId().equals(plotId) && scheduledJob.getTimeSlotId().equals(timeSlotId)).collect(Collectors.toList());
    }

    public void editTask(Long plotId, EditTimeSlotRequestModel newTimeSlotData) {
        List<ScheduledJob> tasks = getTasks(plotId, newTimeSlotData.getTimeSlotId());
        tasks.forEach(task -> {
            task.updateTask(newTimeSlotData);
            restartTask(plotId, newTimeSlotData.getTimeSlotId());
        });
    }

    public void deleteTask(Long timeSlotId) {
        stopTask(timeSlotId);
        scheduledJobs.removeIf(scheduledJob -> scheduledJob.getTimeSlotId().equals(timeSlotId));
    }


}
