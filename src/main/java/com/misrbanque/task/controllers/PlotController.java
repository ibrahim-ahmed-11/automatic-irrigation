package com.misrbanque.task.controllers;

import com.misrbanque.task.helpers.ControllerHelper;
import com.misrbanque.task.models.Response;
import com.misrbanque.task.models.plots.CreatePlotRequestModel;
import com.misrbanque.task.models.plots.EditPlotRequestModel;
import com.misrbanque.task.models.plots.Plot;
import com.misrbanque.task.services.contracts.PlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/plot")
@Validated
@RequiredArgsConstructor
public class PlotController {

    private final PlotService plotService;

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Response<List<Plot>>> getAllPlots() {
        return ControllerHelper.formatResponse(plotService.getPlots());
    }

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Response<Plot>> createPlot(@RequestBody @Validated CreatePlotRequestModel requestModel) {
        return ControllerHelper.formatResponse(plotService.createPlot(requestModel));
    }

    @PutMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Response<Plot>> updatePlot(@RequestBody @Validated EditPlotRequestModel requestModel) throws ChangeSetPersister.NotFoundException {
        return ControllerHelper.formatResponse(plotService.editPlot(requestModel));
    }

}
