package com.misrbanque.task.models.plots;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPlotRequestModel {

    @NotNull
    private Long plotId;

    @NotNull(message = "Plot name must be provided!")
    @Size(min = 4, max = 200, message = "Plot name must be between 4 and 200 characters!")
    private String name;

}
