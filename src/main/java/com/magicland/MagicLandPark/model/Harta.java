package com.magicland.MagicLandPark.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "harti")
public class Harta {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Zona> zone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Zona> getZone() {
        return zone;
    }

    public void setZone(List<Zona> zone) {
        this.zone = zone;
    }
}
