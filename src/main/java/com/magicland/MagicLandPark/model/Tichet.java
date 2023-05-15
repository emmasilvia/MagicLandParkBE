package com.magicland.MagicLandPark.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tichete")
public class Tichet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private double tarif;

    @Column(nullable = false)
    @OneToMany
    private List<Activitate_Parc> TipAcces;

    @Column
    private double valabilitate;

    @Column(nullable = false)
    private TipTichet tipTichet;

    @Column(nullable = false)
    private int CodBilet;

    @Column
    private int DurataAbonament;

    @Column(nullable = false)
    private int CodAbonament;


    @ManyToOne
    private Categorie_Varsta categorieVarsta;

    @ManyToOne
    @JoinColumn(name = "IdBon")
    private Bon bon;

    private int stoc;

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public int getStocRezervat() {
        return stocRezervat;
    }

    public void setStocRezervat(int stocRezervat) {
        this.stocRezervat = stocRezervat;
    }

    private int stocRezervat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public List<Activitate_Parc> getTipAcces() {
        return TipAcces;
    }

    public void setTipAcces(List<Activitate_Parc> tipAcces) {
        TipAcces = tipAcces;
    }

    public double getValabilitate() {
        return valabilitate;
    }

    public void setValabilitate(double valabilitate) {
        this.valabilitate = valabilitate;
    }

    public Bon getBon() {
        return bon;
    }

    public void setBon(Bon bon) {
        this.bon = bon;
    }

    public TipTichet getTipTichet() {
        return tipTichet;
    }

    public void setTipTichet(TipTichet tipTichet) {
        this.tipTichet = tipTichet;
    }

    public int getCodBilet() {
        return CodBilet;
    }

    public void setCodBilet(int codBilet) {
        CodBilet = codBilet;
    }

    public int getDurataAbonament() {
        return DurataAbonament;
    }

    public void setDurataAbonament(int durataAbonament) {
        DurataAbonament = durataAbonament;
    }

    public int getCodAbonament() {
        return CodAbonament;
    }

    public void setCodAbonament(int codAbonament) {
        CodAbonament = codAbonament;
    }

    public Categorie_Varsta getCategorieVarsta() {
        return categorieVarsta;
    }

    public void setCategorieVarsta(Categorie_Varsta categorieVarsta) {
        this.categorieVarsta = categorieVarsta;
    }
}
