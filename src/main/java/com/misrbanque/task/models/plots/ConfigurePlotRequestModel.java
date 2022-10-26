package com.misrbanque.task.models.plots;

import com.misrbanque.task.models.time_slots.TimeSlot;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurePlotRequestModel {

    @NotNull
    private Long plotId;

    private List<TimeSlot> timeSlots;

}
