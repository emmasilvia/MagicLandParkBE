package com.magicland.MagicLandPark.model;

import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity(name = "tichete")
@Table(name = "tichete")
public class Tichet {

    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "nr_bilet")
    private Long nrBilet;

    @Column
    private Long codAbonament;

    @Column
    private int durataAbonament;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "tichete_categoriiTichete", joinColumns = @JoinColumn(name = "tichet_id"), inverseJoinColumns = @JoinColumn(name = "categorie_tichet_id"))
//    private Set<Categorie_Tichet> categorie_tichet;

    @Column
    private LocalDateTime valabilitate;

    @Column
    private Long stoc = 1000L;

    @ManyToOne
    private Bon bon;

    @Enumerated(value = EnumType.STRING)
    private TipTichet tipTichet;

    @ManyToOne
    @JoinColumn(name = "categorie_varsta_id") // Specify the join column
    private Categorie_Tichet categorie_tichet;

    public Tichet(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categorie_Tichet getCategorieTichet() {
        return categorie_tichet;
    }

    public void setCategorieTichet(Categorie_Tichet categorieTichet) {
        this.categorie_tichet = categorieTichet;
    }

    public Long getNrBilet() {
        return nrBilet;
    }

    public void setNrBilet(Long nrBilet) {
        this.nrBilet = nrBilet;
    }

    public Long getCodAbonament() {
        return codAbonament;
    }

    public void setCodAbonament(Long codAbonament) {
        this.codAbonament = codAbonament;
    }

    public int getDurataAbonament() {
        return durataAbonament;
    }

    public void setDurataAbonament(int durataAbonament) {
        this.durataAbonament = durataAbonament;
    }

    public LocalDateTime getValabilitate() {
        return valabilitate;
    }



    @PrePersist
    public void setValabilitate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusYears(1);
        this.valabilitate = expirationDate;
    }

    public Bon getBon() {
        return bon;
    }

    public void setBon(Bon bon) {
        this.bon = bon;
    }

    public Long getStoc() {
        return stoc;
    }

    public void setStoc(Long stoc) {
        this.stoc = stoc;
    }

    @Scheduled(cron = "0 0 0 * * *") // Run daily at midnight
    public void resetStoc() {
        this.stoc = 1000L; // Reset stock to 1000
    }

    public TipTichet getTipTichet() {
        return tipTichet;
    }

    public void setTipTichet(TipTichet tipTichet) {
        this.tipTichet = tipTichet;
    }
}
