package com.magicland.MagicLandPark.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name = "bonuri")
public class Bon {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int NrBon;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dataBon;

    @OneToMany
    private List<Rezervare> rezervari;

    @OneToMany
    private List<Tichet> tichete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNrBon() {
        return NrBon;
    }

    public void setNrBon(int nrBon) {
        NrBon = nrBon;
    }

    public LocalDateTime getDataBon() {
        return dataBon;
    }

    public void setDataBon(LocalDateTime dataBon) {
        dataBon = dataBon;
    }

    public List<Rezervare> getRezervari() {
        return rezervari;
    }

    public void setRezervari(List<Rezervare> rezervari) {
        this.rezervari = rezervari;
    }

    public List<Tichet> getTichete() {
        return tichete;
    }

    public void setTichete(List<Tichet> tichete) {
        this.tichete = tichete;
    }
}
