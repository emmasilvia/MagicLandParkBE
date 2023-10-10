package com.magicland.MagicLandPark.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "persoane")
public class Persoana {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private String prenume;

    @Column(unique=true)
    private String email;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tip_conturi",joinColumns = @JoinColumn(name = "persoana_id"), inverseJoinColumns = @JoinColumn(name = "tip_id"))
    private Set<Tip> tip;

    @OneToMany
    private List<Rezervare> rezervare;

    public Persoana(){

    }

    public Persoana(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Tip> getTip() {
        return tip;
    }

    public void setTip(Set<Tip> tip) {
        this.tip = tip;
    }

    public List<Rezervare> getRezervari() {
        return rezervare;
    }

    public void setRezervari(List<Rezervare> rezervari) {
        this.rezervare = rezervari;
    }
}
