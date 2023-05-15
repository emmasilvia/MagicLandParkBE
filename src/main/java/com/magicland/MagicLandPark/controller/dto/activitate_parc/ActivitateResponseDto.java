package com.magicland.MagicLandPark.controller.dto.activitate_parc;

public class ActivitateResponseDto extends ActivitateRequestDto{

    private Long id;

    private String tipActivitateNume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipActivitateNume() {
        return tipActivitateNume;
    }

    public void setTipActivitateNume(String tipActivitateNume) {
        this.tipActivitateNume = tipActivitateNume;
    }
}
