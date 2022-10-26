package com.misrbanque.task.models.integration;

import com.misrbanque.task.entities.PlotEntity;
import com.misrbanque.task.entities.TimeSlotEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorRequest {

    private Long plotId;
    private Integer waterAmount;

    public SensorRequest fromConfigurationData(Long plotId, Integer waterAmount){
        this.plotId = plotId;
        this.waterAmount = waterAmount;

        return this;
    }

}
