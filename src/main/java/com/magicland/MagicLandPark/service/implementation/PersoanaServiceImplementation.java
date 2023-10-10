package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.exception.ResursaDejaExistentaInDB;
import com.magicland.MagicLandPark.model.Persoana;
import com.magicland.MagicLandPark.service.PersoanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.magicland.MagicLandPark.repository.PersoanaRepository;

@Service
public class PersoanaServiceImplementation implements PersoanaService {

    @Autowired
    private PersoanaRepository persoanaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersoanaServiceImplementation(PersoanaRepository persoanaRepository){
        this.persoanaRepository = persoanaRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public Persoana create(Persoana persoana) {

        Persoana persoanaExistInDatabase = persoanaRepository.findByEmail(persoana.getEmail());
        if(persoanaExistInDatabase != null) {
            throw new ResursaDejaExistentaInDB(String.format("Persoana cu emailul %s exista deja in baza de date",persoana.getEmail()));
        }
        persoana.setPassword(passwordEncoder.encode(persoana.getPassword()));
        return persoanaRepository.save(persoana);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Persoana user = persoanaRepository.findByEmail(username);

        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            persoanaRepository.save(user);
            return true;
        }

        return false;
    }

    public void updatePassword(Persoana persoana, String newPassword) {
        String hashedPassword = hashPassword(newPassword); // Hash the new password

        persoana.setPassword(hashedPassword);
        persoanaRepository.save(persoana);
    }

    private String hashPassword(String password) {
        // You should use a strong password hashing algorithm like BCrypt
        // BCryptPasswordEncoder can be used for this purpose
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public Persoana findByEmail(String email) {
        return persoanaRepository.findByEmail(email);
    }

    @Override
    public Persoana findByNume(String username) {
        return persoanaRepository.findByNume(username);
    }
}
