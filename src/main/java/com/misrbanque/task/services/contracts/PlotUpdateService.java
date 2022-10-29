package com.misrbanque.task.services.contracts;

import com.misrbanque.task.enums.PlotStatusEnum;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface PlotUpdateService {

    void updatePlotStatus(Long plotId, PlotStatusEnum plotStatus) throws ChangeSetPersister.NotFoundException;

}
