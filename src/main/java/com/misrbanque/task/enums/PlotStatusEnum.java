package com.misrbanque.task.enums;

import lombok.Getter;

public enum PlotStatusEnum {

    IDLE("IDLE"),
    IRRIGATING("IRRIGATING");

    @Getter
    private final String name;

    PlotStatusEnum(String name) {
        this.name = name;
    }

}
