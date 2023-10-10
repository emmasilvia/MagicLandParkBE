package com.magicland.MagicLandPark.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "bonuri")
public class Bon {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nrBon;

    @Column(nullable = false)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime dataBon;

    @OneToMany
    private List<Rezervare> rezervari;

    @OneToMany
    private List<Tichet> tichete;

    public Bon(){}
    public Bon(String nrBon) {
        this.nrBon = nrBon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
