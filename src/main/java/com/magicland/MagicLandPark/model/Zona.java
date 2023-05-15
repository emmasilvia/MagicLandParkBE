package com.magicland.MagicLandPark.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "zone")
public class Zona {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Activitate_Parc activitate_parc;

    @ManyToOne
    private Harta harta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activitate_Parc getActivitate_parc() {
        return activitate_parc;
    }

    public void setActivitate_parc(Activitate_Parc activitate_parc) {
        this.activitate_parc = activitate_parc;
    }

    public Harta getHarta() {
        return harta;
    }

    public void setHarta(Harta harta) {
        this.harta = harta;
    }
}
