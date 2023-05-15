package com.magicland.MagicLandPark.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "actori")
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private int codActor;

    @Column(nullable = false)
    private String nume;

    @OneToMany
    private List<Distributie> distributii;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodActor() {
        return codActor;
    }

    public void setCodActor(int codActor) {
        this.codActor = codActor;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Distributie> getDistributii() {
        return distributii;
    }

    public void setDistributii(List<Distributie> distributii) {
        this.distributii = distributii;
    }
}
