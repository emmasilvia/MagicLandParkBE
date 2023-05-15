package com.magicland.MagicLandPark.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "rezervari")
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"persoanaId", "dataVizita", "activitateId"})
})
public class Rezervare {

    @Id
    @GeneratedValue
    private Long id;

//    @Column
//    private List<TipRezervare> TipRezervare;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dataRezervare;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dataVizita;


    @Column(nullable = false)
    private int NrPersoane;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "persoanaId", nullable = false)
    private Persoana persoana;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "activitateId", nullable = false)
    private Activitate_Parc activitate_parc;


    @ManyToOne
    private Categorie_Varsta categorie_varsta;

    @ManyToOne
    private Bon bon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public List<TipRezervare> getTipRezervare() {
//        return TipRezervare;
//    }
//
//    public void setTipRezervare(List<TipRezervare> tipRezervare) {
//        TipRezervare = tipRezervare;
//    }

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
        if(dataVizita.isBefore(dataRezervare)){
            throw new IllegalArgumentException("Data vizitei trebuie aleasa dupa data rezervarii");
        }
        this.dataVizita = dataVizita;
    }

    public int getNrPersoane() {
        return NrPersoane;
    }

    public void setNrPersoane(int nrPersoane) {
        NrPersoane = nrPersoane;
    }

    public Persoana getPersoana() {
        return persoana;
    }

    public void setPersoana(Persoana persoana) {
        this.persoana = persoana;
    }

    public Activitate_Parc getActivitate_parc() {
        return activitate_parc;
    }

    public void setActivitate_parc(Activitate_Parc activitate_parc) {
        this.activitate_parc = activitate_parc;
    }

    public Bon getBon() {
        return bon;
    }

    public void setBon(Bon bon) {
        if (bon.getDataBon().isBefore(dataRezervare)){
            throw new IllegalArgumentException("Bonul nu se poate emite inainte de data la care faceti rezervarea");
        }
        this.bon = bon;
    }

    public Categorie_Varsta getCategorie_varsta() {
        return categorie_varsta;
    }

    public void setCategorie_varsta(Categorie_Varsta categorie_varsta) {
        this.categorie_varsta = categorie_varsta;
    }
}
