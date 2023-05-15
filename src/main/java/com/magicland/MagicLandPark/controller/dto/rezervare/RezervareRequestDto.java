package com.magicland.MagicLandPark.controller.dto.rezervare;

import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.model.Categorie_Varsta;
import com.magicland.MagicLandPark.model.Persoana;

import java.time.LocalDateTime;
import java.util.Date;

public class RezervareRequestDto {

    private LocalDateTime dataRezervare;
    private LocalDateTime dataVizita;
    private int nrPersoane;
    private Persoana email_persoana;
    private Activitate_Parc denumire_activitate;
    private Categorie_Varsta categorie_varsta;

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
        this.dataVizita = dataVizita;
    }

    public int getNrPersoane() {
        return nrPersoane;
    }

    public void setNrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }

    public Persoana getEmail_persoana() {
        return email_persoana;
    }

    public void setEmail_persoana(Persoana email_persoana) {
        this.email_persoana = email_persoana;
    }

    public Activitate_Parc getDenumire_activitate() {
        return denumire_activitate;
    }

    public void setDenumire_activitate(Activitate_Parc denumire_activitate) {
        this.denumire_activitate = denumire_activitate;
    }

    public Categorie_Varsta getCategorie_varsta() {
        return categorie_varsta;
    }

    public void setCategorie_varsta(Categorie_Varsta categorie_varsta) {
        this.categorie_varsta = categorie_varsta;
    }
}
