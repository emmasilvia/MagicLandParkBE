package com.magicland.MagicLandPark.controller.dto.bon;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class BonRequestDto {

    private int nrBon;
    private LocalDateTime dataBon;

    public int getNrBon() {
        return nrBon;
    }

    public void setNrBon(int nrBon) {
        this.nrBon = nrBon;
    }

    public LocalDateTime getDataBon() {
        return dataBon;
    }

    public void setDataBon(LocalDateTime dataBon) {
        this.dataBon = dataBon;
    }
}
