package com.magicland.MagicLandPark.controller.dto.tichet;

import com.magicland.MagicLandPark.model.Tichet;
import com.magicland.MagicLandPark.model.TipTichet;

import java.util.List;

public class TichetResponseDto extends TichetRequestDto{

    private Long id;

    private double pretTichet;

    private List<TichetResponseDto> tichetList;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPretTichet() {
        return pretTichet;
    }

    public void setPretTichet(double pretTotal) {
        this.pretTichet = pretTichet;
    }

    public List<TichetResponseDto> getTichetList() {
        return tichetList;
    }

    public void setTichetList(List<TichetResponseDto> tichetList) {
        this.tichetList = tichetList;
    }
}
