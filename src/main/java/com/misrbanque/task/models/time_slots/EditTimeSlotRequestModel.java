package com.misrbanque.task.models.time_slots;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTimeSlotRequestModel {

    @NotNull(message = "TimeSlot ID must be provided!")
    private Long timeSlotId;

    @NotNull(message = "Start time must be provided!")
    private Time startTime;

    @NotNull(message = "Duration in minutes must be provided!")
    private int durationInMinutes;

    @NotNull(message = "Water Amount must be provided!")
    private int waterAmount;

}
