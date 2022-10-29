package com.misrbanque.task.models.plots;

import com.misrbanque.task.models.time_slots.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurePlotRequestModel {

    @NotNull
    private Long plotId;

    private List<TimeSlot> timeSlots;

}
