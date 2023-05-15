package com.magicland.MagicLandPark.config;

import com.magicland.MagicLandPark.exception.ResursaNegasitaInDB;
import com.magicland.MagicLandPark.model.Persoana;
import com.magicland.MagicLandPark.model.Tip;
import com.magicland.MagicLandPark.repository.PersoanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class  DetaliiPersoanaService implements UserDetailsService {

    private PersoanaRepository persoanaRepository;

    @Autowired
    public DetaliiPersoanaService(PersoanaRepository persoanaRepository){
        this.persoanaRepository = persoanaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Persoana persoana = persoanaRepository.findByEmail(s);
        if(persoana == null){
            throw new ResursaNegasitaInDB(String.format("Persoana cu emailul %s nu exista.", persoana.getEmail()));
        }
        return mapPersoanaToDetaliiPersoana(persoana);
    }

    public UserDetails mapPersoanaToDetaliiPersoana(Persoana persoana){
        List<GrantedAuthority> grantedAuthorities = mapTipToGrantedAuthority(persoana.getTip());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(persoana.getEmail(), persoana.getPassword(), grantedAuthorities);
        return userDetails;
    }

    public List<GrantedAuthority> mapTipToGrantedAuthority(Set<Tip> tip_conturi){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Tip tip : tip_conturi){
            grantedAuthorities.add(new SimpleGrantedAuthority(tip.getNume()));
        }
        return grantedAuthorities;
    }
}
