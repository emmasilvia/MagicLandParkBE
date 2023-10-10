package com.magicland.MagicLandPark.controller.dto.bon;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class BonRequestDto {

    private Long id;
    private String nrBon;
    private LocalDateTime dataBon;



    public String getNrBon() {
        return nrBon;
    }

    public void setNrBon(String nrBon) {
        this.nrBon = nrBon;
    }

    public LocalDateTime getDataBon() {
        return dataBon;
    }

    public void setDataBon(LocalDateTime dataBon) {
        this.dataBon = dataBon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
