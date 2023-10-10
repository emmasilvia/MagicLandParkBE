package com.magicland.MagicLandPark.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "rezervari")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"persoana_id", "dataVizita", "activitate_id"})
})
public class Rezervare {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @CreationTimestamp
    private LocalDateTime dataRezervare;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime dataVizita;

    @Column
    private int nrPersoane;
    @ManyToOne
    private Persoana persoana;

    @ManyToOne
    private Bon bon;

    @ManyToOne
    private Activitate_Parc activitate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataRezervare() {
        return dataRezervare;
    }

    public void setDataRezervare(LocalDateTime dataRezervare) {
        this.dataRezervare = dataRezervare;
    }

    public LocalDateTime getDataVizita() {
        return dataVizita;
    }

    public void setDataVizita(LocalDateTime dataVizita) {
//        if(dataVizita.isBefore(this.dataRezervare)){
//            throw new IllegalArgumentException("Data vizitei trebuie aleasa dupa data rezervarii");
//        }
        this.dataVizita = dataVizita;
    }
    public Persoana getPersoana() {
        return persoana;
    }

    public void setPersoana(Persoana persoana) {
        this.persoana = persoana;
    }

    public int getNrPersoane() {
        return nrPersoane;
    }

    public void setNrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }

    public Bon getBon() {
        return bon;
    }

    public void setBon(Bon bon) {
        this.bon = bon;
    }

    public Activitate_Parc getActivitate() {
        return activitate;
    }

    public void setActivitate(Activitate_Parc activitate) {
        this.activitate = activitate;
    }
}
