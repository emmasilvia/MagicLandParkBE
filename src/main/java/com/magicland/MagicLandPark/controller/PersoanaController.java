package com.magicland.MagicLandPark.controller;

import antlr.Token;
import com.magicland.MagicLandPark.config.DetaliiPersoanaService;
import com.magicland.MagicLandPark.config.JwtTokenUtil;
import com.magicland.MagicLandPark.controller.dto.persoana.PersoanaDTO;
import com.magicland.MagicLandPark.controller.dto.rezervare.RezervareResponseDto;
import com.magicland.MagicLandPark.model.Persoana;
import com.magicland.MagicLandPark.model.Rezervare;
import com.magicland.MagicLandPark.model.Tip;
import com.magicland.MagicLandPark.service.RezervareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.magicland.MagicLandPark.service.PersoanaService;
import com.magicland.MagicLandPark.service.TipService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersoanaController {

    private PersoanaService persoanaService;
    @Autowired(required = true)
    private AuthenticationManager authenticationManager;

    @Autowired
    private DetaliiPersoanaService detaliiPersoanaService;

    private JwtTokenUtil jwtTokenUtil;

    private TipService tipService;

    private RezervareService rezervareService;

    @Autowired
    private JavaMailSender javaMailSender;

    public PersoanaController(PersoanaService persoanaService, TipService tipService, JavaMailSender javaMailSender){
        this.persoanaService = persoanaService;
        this.tipService = tipService;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping(value = "/register")
    public PersoanaDTO create(@RequestBody PersoanaDTO persoanaDTO){
        Persoana persoana = persoanaService.create(mapPersoanaDtoToPersoana(persoanaDTO));
        return mapPersoanaToPersoanaDto(persoana);
    }
    public Persoana mapPersoanaDtoToPersoana(PersoanaDTO persoanaDTO){

        Persoana p1 = new Persoana();
        p1.setNume(persoanaDTO.getNume());
        p1.setPrenume(persoanaDTO.getPrenume());
        p1.setEmail(persoanaDTO.getEmail());
        p1.setPassword(persoanaDTO.getPassword());
        Tip tip = tipService.findByName(persoanaDTO.getTip());
        Set<Tip> tip_conturi = new HashSet<>();
        tip_conturi.add(tip);
        p1.setTip(tip_conturi);
        return p1;
    }

    public PersoanaDTO mapPersoanaToPersoanaDto(Persoana persoana){
        PersoanaDTO pd1 = new PersoanaDTO();
         pd1.setNume(persoana.getNume());
        pd1.setPrenume(persoana.getPrenume());
        pd1.setEmail(persoana.getEmail());
        pd1.setPassword(persoana.getPassword());
        Set<Tip> tip_conturi = persoana.getTip();
        for (Tip tip: tip_conturi){
            pd1.setTip(tip.getNume());
        }
        return pd1;
    }
    @PostMapping(value = "/login")
    public org.springframework.security.core.userdetails.User login() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping(value = "/persoane/tipuri")
    public List<Tip> findAll() {
        return tipService.findAll();
    }

    @GetMapping(value = "persoane/{email}")
    public Persoana findByEmail(@PathVariable String email){
        return persoanaService.findByEmail(email);
    }

//    @GetMapping(value = "/current-user")
//    public PersoanaDTO getCurrentUser(Principal principal) {
//
//        String username = principal.getName();
//
//        Persoana persoana = persoanaService.findByNume(username);
//
//        return mapPersoanaToPersoanaDto(persoana);
//    }

//    @GetMapping(value = "/current-user")
//    public String currentUserName(Principal principal) {
//        return principal.getName();
//    }

    @PostMapping("/changepassword")
    public ResponseEntity<String> changePassword(
            @RequestParam String email,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        boolean passwordChanged = persoanaService.changePassword(email, oldPassword, newPassword);

        if (passwordChanged) {
            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to change password.");
        }
    }


    @GetMapping
    public String showResetForm() {
        return "resetPasswordForm";
    }

    @PostMapping(value = "/reset")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        Persoana persoana = persoanaService.findByEmail(email);

        if (persoana != null) {
            String temporaryPassword = generateTemporaryPassword();
            persoanaService.updatePassword(persoana, temporaryPassword);
            sendTemporaryPasswordByEmail(persoana.getEmail(), temporaryPassword);
            return ResponseEntity.ok("Password reset successful.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email.");
        }
    }


    private String generateTemporaryPassword() {
        // Generate a random temporary password
        // You can use libraries like SecureRandom to generate a secure password
        return "temporary123"; // Replace with actual generated password
    }

    private void sendTemporaryPasswordByEmail(String email, String temporaryPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reset Password");
        message.setText("Aceasta este parola temporara de resetare: "+ temporaryPassword);

        javaMailSender.send(message);
    }

    @GetMapping(value = "/current-user")
    public Persoana getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return persoanaService.findByEmail(user.getUsername());
        }

        // Return a default or null value when the user is not authenticated
        return null;
    }

    @GetMapping(path = "/test")
    public HttpStatus testAuth() {
        return HttpStatus.OK;
    }


}
