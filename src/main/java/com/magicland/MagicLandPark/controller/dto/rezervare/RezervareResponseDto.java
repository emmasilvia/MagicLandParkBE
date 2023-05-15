package com.magicland.MagicLandPark.controller.dto.rezervare;

public class RezervareResponseDto extends RezervareRequestDto{

    private Long id;

    private String tipRezervare;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipRezervare() {
        return tipRezervare;
    }

    public void setTipRezervare(String tipRezervare) {
        this.tipRezervare = tipRezervare;
    }
}
