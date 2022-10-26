package com.misrbanque.task.services.schedulers;

import com.misrbanque.task.models.integration.SensorRequest;
import com.misrbanque.task.models.time_slots.EditTimeSlotRequestModel;
import com.misrbanque.task.services.contracts.SensorIntegrationService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Log4j2
public class ScheduledJob implements Runnable {

    @Getter
    private final Long plotId;
    @Getter
    private final Long timeSlotId;
    @Getter
    @Setter
    private Integer waterAmount;
    @Getter
    @Setter
    private Time startTime;
    private long periodInMilliseconds;

    private final ScheduledExecutorService scheduler;
    private final Runnable runnableCommand;

    public ScheduledJob(Long plotId, Long timeSlotId, Time startTime, long periodInMinutes, Integer waterAmount, SensorIntegrationService sensorIntegrationService) {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.plotId = plotId;
        this.timeSlotId = timeSlotId;
        this.waterAmount = waterAmount;
        this.startTime = startTime;
        this.runnableCommand = () -> {
            try {
                SensorRequest request = new SensorRequest().fromConfigurationData(this.plotId, this.waterAmount);
                sensorIntegrationService.sendSensorReading(request);
            } catch (Exception ex) {
                log.error("Exception was thrown while execution: " + ex.getMessage());
            }
        };
        this.periodInMilliseconds = TimeUnit.MINUTES.toMillis(periodInMinutes);
    }

    @Override
    public void run() {
        scheduler.scheduleAtFixedRate(runnableCommand, computeNextDelay(startTime), periodInMilliseconds, TimeUnit.MILLISECONDS);
    }

    public void updateTask(EditTimeSlotRequestModel editTimeSlotRequestModel){
        this.startTime = editTimeSlotRequestModel.getStartTime();
        this.waterAmount = editTimeSlotRequestModel.getWaterAmount();
        this.periodInMilliseconds = TimeUnit.MINUTES.toMillis(editTimeSlotRequestModel.getDurationInMinutes());
    }

    public void shutdown() {
        if (!scheduler.isShutdown())
            scheduler.shutdown();
    }

    private long computeNextDelay(Time time)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        int targetHour = calendar.get(Calendar.HOUR_OF_DAY);
        int targetMin = calendar.get(Calendar.MINUTE);
        int targetSec = calendar.get(Calendar.SECOND);

        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNextTarget = zonedNow.withHour(targetHour).withMinute(targetMin).withSecond(targetSec);
        if(zonedNow.compareTo(zonedNextTarget) > 0)
            zonedNextTarget = zonedNextTarget.plusDays(1);

        Duration duration = Duration.between(zonedNow, zonedNextTarget);
        return duration.getSeconds();
    }

}
