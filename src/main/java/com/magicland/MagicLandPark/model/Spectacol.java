package com.magicland.MagicLandPark.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "spectacole")
public class Spectacol extends Activitate_Parc{


    @Column
    private int nrLocuri;

    @Column
    private int sala;

    @OneToMany
    private List<Distributie> distributii;


    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public List<Distributie> getDistributii() {
        return distributii;
    }

    public void setDistributii(List<Distributie> distributii) {
        this.distributii = distributii;
    }
}
