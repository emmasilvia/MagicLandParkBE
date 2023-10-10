package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.*;

import javax.naming.InsufficientResourcesException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RezervareService {

    Rezervare createReservation(Rezervare rezervare) throws InsufficientResourcesException;

    Rezervare findById(Long id);

    Rezervare findByDataRezervare(LocalDateTime dataRezervare);

    List<Rezervare> findAllReservationsByEmail(String email);

    Rezervare findByNrBon(String nrBon);

    List<Rezervare> getAllReservations();

    void deleteRezervare(Long id);

    Optional<Rezervare> findByIdOptional(Long id);

    List<Rezervare> findAll();


}
