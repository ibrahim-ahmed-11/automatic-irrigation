package com.misrbanque.task.services;

import com.misrbanque.task.entities.PlotEntity;
import com.misrbanque.task.enums.PlotStatusEnum;
import com.misrbanque.task.repositories.PlotsRepository;
import com.misrbanque.task.services.contracts.PlotUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PlotUpdateServiceImpl implements PlotUpdateService {

    private final PlotsRepository plotsRepository;

    @Override
    public void updatePlotStatus(Long plotId, PlotStatusEnum plotStatus) {
        try {
            PlotEntity plotEntity = plotsRepository.findById(plotId).orElseThrow(ChangeSetPersister.NotFoundException::new);
            plotEntity.setStatus(plotStatus.getName());

            plotsRepository.save(plotEntity);
            plotsRepository.refresh(plotEntity);
        } catch (Exception ex) {
            log.error("Failed to update plot: " + plotId + " status!");
            log.error(ex.getMessage());
        }
    }
}
