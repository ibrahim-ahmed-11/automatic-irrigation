package com.misrbanque.task.models.time_slots;

import com.misrbanque.task.entities.TimeSlotEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {

    private Time startTime;

    private int durationInMinutes;

    private int waterAmount;

    public TimeSlot fromEntity(TimeSlotEntity timeSlotEntity) {
        this.startTime = timeSlotEntity.getStartTime();
        this.durationInMinutes = timeSlotEntity.getDurationInMinutes();
        this.waterAmount = timeSlotEntity.getWaterAmount();

        return this;
    }

}
