package com.magicland.MagicLandPark.model;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tip {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nume;

    public Tip() {

    }

    public Tip(String nume){
        this.nume = nume;
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
}
