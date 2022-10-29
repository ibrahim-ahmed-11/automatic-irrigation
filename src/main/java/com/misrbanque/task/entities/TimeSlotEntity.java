package com.misrbanque.task.entities;

import com.misrbanque.task.models.time_slots.EditTimeSlotRequestModel;
import com.misrbanque.task.models.time_slots.TimeSlot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "time_slots", schema = "public")
public class TimeSlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "duration_in_minutes", nullable = false)
    private Integer durationInMinutes;

    @Column(name = "water_amount", nullable = false)
    private Integer waterAmount;

    @Basic
    @Column(name = "plot_id", nullable = false)
    private Long plotId;

    @ManyToOne
    @JoinColumn(name = "plot_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private PlotEntity plot;

    public TimeSlotEntity fromModel(Long plotId, TimeSlot timeSlot) {
        this.startTime = timeSlot.getStartTime();
        this.durationInMinutes = timeSlot.getDurationInMinutes();
        this.waterAmount = timeSlot.getWaterAmount();
        this.plotId = plotId;

        return this;
    }

    public void fromModel(EditTimeSlotRequestModel timeSlotModel) {
        this.startTime = timeSlotModel.getStartTime();
        this.durationInMinutes = timeSlotModel.getDurationInMinutes();
        this.waterAmount = timeSlotModel.getWaterAmount();
    }

}
