package com.misrbanque.task.services;

import com.misrbanque.task.entities.PlotEntity;
import com.misrbanque.task.entities.TimeSlotEntity;
import com.misrbanque.task.models.plots.ConfigurePlotRequestModel;
import com.misrbanque.task.models.plots.CreatePlotRequestModel;
import com.misrbanque.task.models.plots.EditPlotRequestModel;
import com.misrbanque.task.models.plots.Plot;
import com.misrbanque.task.repositories.PlotsRepository;
import com.misrbanque.task.services.contracts.PlotService;
import com.misrbanque.task.services.contracts.ScheduledJobsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PlotServiceImpl implements PlotService {

    private final PlotsRepository plotsRepository;
    private final ScheduledJobsManager jobsManager;

    @Override
    public Plot createPlot(CreatePlotRequestModel requestModel) {
        PlotEntity plotEntity = new PlotEntity().fromModel(requestModel);
        plotsRepository.save(plotEntity);
        plotsRepository.refresh(plotEntity);

        return new Plot().fromEntity(plotEntity);
    }

    @Override
    public Plot setPlotTimeSlots(ConfigurePlotRequestModel requestModel) throws ChangeSetPersister.NotFoundException {
        PlotEntity plotEntity = plotsRepository.findById(requestModel.getPlotId()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        List<TimeSlotEntity> timeSlotEntities = requestModel.getTimeSlots().stream().map(timeSlot -> new TimeSlotEntity().fromModel(plotEntity.getId(), timeSlot)).collect(Collectors.toList());
        plotEntity.setTimeSlots(timeSlotEntities);

        plotsRepository.save(plotEntity);
        plotsRepository.refresh(plotEntity);

        jobsManager.deletePlotTasks(plotEntity.getId());
        jobsManager.addPlotTasks(plotEntity);

        return new Plot().fromEntity(plotEntity);
    }

    @Override
    public Plot editPlot(EditPlotRequestModel requestModel) throws ChangeSetPersister.NotFoundException {
        PlotEntity plotEntity = plotsRepository.findById(requestModel.getPlotId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        plotEntity.fromModel(requestModel);

        plotsRepository.save(plotEntity);
        plotsRepository.refresh(plotEntity);

        return new Plot().fromEntity(plotEntity);
    }

    @Override
    public List<Plot> getPlots() {
        List<PlotEntity> plots = plotsRepository.findAll();

        return plots.stream().map(entity -> new Plot().fromEntity(entity)).collect(Collectors.toList());
    }

}
