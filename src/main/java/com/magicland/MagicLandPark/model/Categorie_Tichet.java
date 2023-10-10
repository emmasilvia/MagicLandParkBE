package com.magicland.MagicLandPark.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "categorii_tichete")
public class Categorie_Tichet {

    @Id
    @GeneratedValue
    private Long id;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "categoriiTichete_tipAcces", joinColumns = @JoinColumn(name = "categorie_tichet_id"), inverseJoinColumns = @JoinColumn(name = "tip_acces_id"))
//    @Column(name = "tip_acces_id")
//    private Set<TipAcces> tipAcces;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "categorii_tichete_varsta", joinColumns = @JoinColumn(name = "categorie_tichet_id"), inverseJoinColumns = @JoinColumn(name = "categorie_varsta_id"))
//    @Column(name = "categorie_varsta_id")
//    private Set<CategorieVarsta> categorieVarsta;


    @Column(nullable = false)
    private String categorieVarsta;

    @Column
    private double tarif;
    @OneToMany
    private List<Tichet> tichete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorieVarsta() {
        return categorieVarsta;
    }

    public void setCategorieVarsta(String categorieVarsta) {
        this.categorieVarsta = categorieVarsta;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public List<Tichet> getTichete() {
        return tichete;
    }

    public void setTichete(List<Tichet> tichete) {
        this.tichete = tichete;
    }
}

