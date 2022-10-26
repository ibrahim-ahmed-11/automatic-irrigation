package com.misrbanque.task.entities;

import com.misrbanque.task.enums.PlotStatusEnum;
import com.misrbanque.task.models.plots.CreatePlotRequestModel;
import com.misrbanque.task.models.plots.EditPlotRequestModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "plots", schema = "public")
public class PlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "creation_date")
    private Date creationDate;

    @OneToMany(mappedBy = "plot")
    private Collection<TimeSlotEntity> timeSlots;

    public PlotEntity fromModel(CreatePlotRequestModel requestModel){
        this.name = requestModel.getName();
        this.status = PlotStatusEnum.IDLE.name();
        this.timeSlots = new ArrayList<>();

        return this;
    }

    public void fromModel(EditPlotRequestModel requestModel){
        this.name = requestModel.getName();
    }

}
