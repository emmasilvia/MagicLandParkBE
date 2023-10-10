package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.Persoana;
import org.springframework.stereotype.Service;


public interface PersoanaService {

    Persoana create(Persoana persoana);
    Persoana findByEmail(String email);

    boolean changePassword(String username, String oldPassword, String newPassword);

    void updatePassword(Persoana persoana, String newPassword);

    Persoana findByNume(String nume);

//    Persoana findById(Long id);
}
