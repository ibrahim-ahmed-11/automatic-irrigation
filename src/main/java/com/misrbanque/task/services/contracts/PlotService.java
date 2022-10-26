package com.misrbanque.task.services.contracts;

import com.misrbanque.task.models.plots.ConfigurePlotRequestModel;
import com.misrbanque.task.models.plots.CreatePlotRequestModel;
import com.misrbanque.task.models.plots.EditPlotRequestModel;
import com.misrbanque.task.models.plots.Plot;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface PlotService {

    Plot createPlot(CreatePlotRequestModel requestModel);

    Plot setPlotTimeSlots(ConfigurePlotRequestModel requestModel) throws ChangeSetPersister.NotFoundException;

    Plot editPlot(EditPlotRequestModel requestModel) throws ChangeSetPersister.NotFoundException;

    List<Plot> getPlots();

}
