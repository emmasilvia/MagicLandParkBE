package com.magicland.MagicLandPark.config;

import com.magicland.MagicLandPark.exception.ResursaDejaExistentaInDB;
import com.magicland.MagicLandPark.exception.ResursaNegasitaInDB;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptieController {
    @ExceptionHandler(value = {ResursaNegasitaInDB.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public MesajEroare resursaNegasitaInDB(ResursaNegasitaInDB exceptieResursa){
        MesajEroare mesajEroare = new MesajEroare();
        mesajEroare.setMesaj(exceptieResursa.getMessage());
        return mesajEroare;
    }

    @ExceptionHandler(value = {ResursaDejaExistentaInDB.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MesajEroare resursaDejaExistentaExceptie(ResursaDejaExistentaInDB resursaDejaExistentaInDBExceptie){
        MesajEroare mesajEroare = new MesajEroare();
        mesajEroare.setMesaj(resursaDejaExistentaInDBExceptie.getMessage());
        return mesajEroare;
    }
}
