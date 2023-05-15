package com.magicland.MagicLandPark.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "distributii")
public class Distributie {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Spectacol spectacol;

    @ManyToOne
    private Actor actor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Spectacol getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(Spectacol spectacol) {
        this.spectacol = spectacol;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
