package com.magicland.MagicLandPark.model;

public enum ProgramEnum {

    Zilnic("Program zilnic"),
    Luni_Vineri("Luni-Vineri, 10:00-18:00");

    private String description;

    ProgramEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
