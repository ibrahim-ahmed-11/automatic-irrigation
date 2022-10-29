package com.misrbanque.task.services.schedulers;

import com.misrbanque.task.entities.PlotEntity;
import com.misrbanque.task.models.time_slots.EditTimeSlotRequestModel;
import com.misrbanque.task.repositories.PlotsRepository;
import com.misrbanque.task.services.contracts.ScheduledJobsManager;
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
public class ScheduledJobsManagerImpl implements ScheduledJobsManager {

    private final PlotsRepository plotsRepository;
    private final SensorIntegrationService sensorIntegrationService;
    @Getter
    private List<ScheduledJob> scheduledJobs;


    public ScheduledJobsManagerImpl(PlotsRepository plotsRepository, SensorIntegrationService sensorIntegrationService) {
        this.plotsRepository = plotsRepository;
        this.sensorIntegrationService = sensorIntegrationService;
        this.scheduledJobs = initialize();
    }

    @Override
    public List<ScheduledJob> initialize() {

        List<PlotEntity> plots = plotsRepository.findAll();
        this.scheduledJobs = new ArrayList<>();

        plots.forEach(this::addPlotTasks);

        return scheduledJobs;
    }

    @Override
    public void restartTask(Long plotId, Long timeSlotId) {
        stopTask(plotId, timeSlotId);
        startTask(plotId, timeSlotId);
    }

    @Override
    public void editTask(Long plotId, EditTimeSlotRequestModel newTimeSlotData) {
        List<ScheduledJob> tasks = getTasks(plotId, newTimeSlotData.getTimeSlotId());
        tasks.forEach(task -> {
            task.updateTask(newTimeSlotData);
            restartTask(plotId, newTimeSlotData.getTimeSlotId());
        });
    }

    @Override
    public void deleteTask(Long timeSlotId) {
        stopTimeSlotTasks(timeSlotId);
        scheduledJobs.removeIf(scheduledJob -> scheduledJob.getTimeSlotId().equals(timeSlotId));
    }

    @Override
    public void deletePlotTasks(Long plotId) {
        stopPlotTasks(plotId);
        scheduledJobs.removeIf(scheduledJob -> scheduledJob.getPlotId().equals(plotId));
    }

    @Override
    public void addPlotTasks(PlotEntity plotEntity) {
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
    }

    private void startTask(Long plotId, Long timeSlotId) {
        getTasks(plotId, timeSlotId).forEach(ScheduledJob::run);
    }

    private void stopTask(Long plotId, Long timeSlotId) {
        getTasks(plotId, timeSlotId).forEach(ScheduledJob::shutdown);
    }

    private void stopTimeSlotTasks(Long timeSlotId) {
        getTasks(scheduledJob -> scheduledJob.getTimeSlotId().equals(timeSlotId)).forEach(ScheduledJob::shutdown);
    }

    private void stopPlotTasks(Long plotId) {
        getTasks(scheduledJob -> scheduledJob.getPlotId().equals(plotId)).forEach(ScheduledJob::shutdown);
    }

    private List<ScheduledJob> getTasks(Predicate<? super ScheduledJob> predicate) {
        return scheduledJobs.stream().filter(predicate).collect(Collectors.toList());
    }

    private List<ScheduledJob> getTasks(Long plotId, Long timeSlotId) {
        return scheduledJobs.stream().filter(scheduledJob -> scheduledJob.getPlotId().equals(plotId) && scheduledJob.getTimeSlotId().equals(timeSlotId)).collect(Collectors.toList());
    }


}
