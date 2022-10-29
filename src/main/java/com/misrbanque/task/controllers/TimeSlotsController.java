package com.misrbanque.task.controllers;

import com.misrbanque.task.helpers.BaseController;
import com.misrbanque.task.models.Response;
import com.misrbanque.task.models.plots.ConfigurePlotRequestModel;
import com.misrbanque.task.models.plots.Plot;
import com.misrbanque.task.models.time_slots.EditTimeSlotRequestModel;
import com.misrbanque.task.models.time_slots.TimeSlot;
import com.misrbanque.task.services.contracts.PlotService;
import com.misrbanque.task.services.contracts.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/slot")
@Validated
@RequiredArgsConstructor
public class TimeSlotsController extends BaseController {

    private final PlotService plotService;
    private final TimeSlotService timeSlotService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response<Plot>> configurePlotTimeSlot(@RequestBody @Validated ConfigurePlotRequestModel requestModel) throws ChangeSetPersister.NotFoundException {
        return formatResponse(plotService.setPlotTimeSlots(requestModel));
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response<TimeSlot>> editTimeSlot(@RequestBody @Validated EditTimeSlotRequestModel requestModel) throws ChangeSetPersister.NotFoundException {
        return formatResponse(timeSlotService.editTimeSlot(requestModel));
    }

    @DeleteMapping(value = "/{timeSlotId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response<Boolean>> deleteTimeSlot(@PathVariable Long timeSlotId) {
        return formatResponse(timeSlotService.deleteTimeSlot(timeSlotId));
    }

}
