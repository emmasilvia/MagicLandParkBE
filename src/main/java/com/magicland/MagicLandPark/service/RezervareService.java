package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.model.Bon;
import com.magicland.MagicLandPark.model.Rezervare;
import com.magicland.MagicLandPark.model.Tichet;

import javax.naming.InsufficientResourcesException;
import java.util.List;

public interface RezervareService {

    Rezervare createReservation(Rezervare rezervare) throws InsufficientResourcesException;

    Rezervare findById(Long id);

    List<Rezervare> findAllReservationsByUserId(Long id);

    void deleteRezervare(Long id);

}
