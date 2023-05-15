package com.magicland.MagicLandPark.exception;

public class ResursaDejaExistentaInDB extends RuntimeException{

    public ResursaDejaExistentaInDB(String mesaj){
        super(mesaj);
    }
}
