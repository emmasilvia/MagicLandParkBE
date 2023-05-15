package com.magicland.MagicLandPark.controller;

import antlr.Token;
import com.magicland.MagicLandPark.config.DetaliiPersoanaService;
import com.magicland.MagicLandPark.config.JwtTokenUtil;
import com.magicland.MagicLandPark.controller.dto.persoana.PersoanaDTO;
import com.magicland.MagicLandPark.model.Persoana;
import com.magicland.MagicLandPark.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.magicland.MagicLandPark.service.PersoanaService;
import com.magicland.MagicLandPark.service.TipService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public PersoanaController(PersoanaService persoanaService, TipService tipService){
        this.persoanaService = persoanaService;
        this.tipService = tipService;
    }

    @PostMapping(value = "/register")
    public PersoanaDTO create(@RequestBody PersoanaDTO persoanaDTO){
        Persoana persoana = persoanaService.create(mapPersoanaDtoToPersoana(persoanaDTO));
        return mapPersoanaToPersoanaDto(persoana);
    }

    @PostMapping(value = "/login")
    public org.springframework.security.core.userdetails.User login() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping(value = "/persoane/tipuri")
    public List<Tip> findAll() {
        return tipService.findAll();
    }


//    @PostMapping(value = "/login")
//    public ResponseEntity<String> login(@RequestBody PersoanaDTO persoanaDTO) {
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    persoanaDTO.getEmail(), persoanaDTO.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\":\"Invalid email or password\"}");
//        }
//    }

    @GetMapping(path = "/test")
    public HttpStatus testAuth() {
        return HttpStatus.OK;
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
}
