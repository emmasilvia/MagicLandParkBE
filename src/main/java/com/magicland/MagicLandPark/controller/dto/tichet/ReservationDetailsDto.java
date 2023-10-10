package com.magicland.MagicLandPark.controller.dto.tichet;

import com.magicland.MagicLandPark.model.Bon;

import java.util.List;

public class ReservationDetailsDto {

    private Bon bon;
    private List<TichetDetailsDto> ticheteDetailsList;

    public Bon getBon() {
        return bon;
    }

    public void setBon(Bon bon) {
        this.bon = bon;
    }

    public List<TichetDetailsDto> getTicheteDetailsList() {
        return ticheteDetailsList;
    }

    public void setTicheteDetailsList(List<TichetDetailsDto> ticheteDetailsList) {
        this.ticheteDetailsList = ticheteDetailsList;
    }
}
