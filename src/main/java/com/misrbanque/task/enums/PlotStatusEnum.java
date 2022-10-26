package com.misrbanque.task.enums;

public enum PlotStatusEnum {

    IDLE("IDLE"),
    IRRIGATING("IRRIGATING");

    private final String name;

    private PlotStatusEnum(String name) {
        this.name = name;
    }

}
