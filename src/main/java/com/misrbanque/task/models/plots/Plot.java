package com.misrbanque.task.models.plots;

import com.misrbanque.task.entities.PlotEntity;
import com.misrbanque.task.models.time_slots.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plot {

    private Long id;
    private String name;

    private String status;

    private Date creationDate;

    private Collection<TimeSlot> timeSlots;

    public Plot fromEntity(PlotEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.status = entity.getStatus();
        this.creationDate = entity.getCreationDate();
        this.timeSlots = entity.getTimeSlots().stream().map(timeSlot -> new TimeSlot().fromEntity(timeSlot)).collect(Collectors.toList());

        return this;
    }
}
