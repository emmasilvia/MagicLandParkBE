package com.magicland.MagicLandPark.controller;

import com.magicland.MagicLandPark.controller.dto.activitate_parc.ActivitateRequestDto;
import com.magicland.MagicLandPark.controller.dto.activitate_parc.ActivitateResponseDto;
import com.magicland.MagicLandPark.exception.ResursaNegasitaInDB;
import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.model.Harta;
import com.magicland.MagicLandPark.model.NivelDificultate;
import com.magicland.MagicLandPark.model.TipActivitate;
import com.magicland.MagicLandPark.service.Activitate_ParcService;
import com.magicland.MagicLandPark.service.HartaService;
import com.magicland.MagicLandPark.service.PersoanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class Activitate_ParcController {

    private Activitate_ParcService activitate_parcService;
    private PersoanaService persoanaService;

    private HartaService hartaService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Activitate_ParcController(Activitate_ParcService activitate_parcService, PersoanaService persoanaService, HartaService hartaService){
        this.activitate_parcService = activitate_parcService;
        this.persoanaService = persoanaService;
        this.hartaService = hartaService;
    }

    @PostMapping(value = "/activitati")
    public ActivitateResponseDto create(@RequestBody ActivitateRequestDto activitateRequestDto) {
        Activitate_Parc activitate_parc = activitate_parcService.create(mapActivitateRequestDtoToActivitate(activitateRequestDto));
        return mapActivitateToActivitateResponseDto(activitate_parc);
    }

    @GetMapping(value = "/activitati/{id}")
    public Activitate_Parc findById(@PathVariable("id") Long id){
        return activitate_parcService.findById(id);
    }

    @GetMapping(value = "/activitati/denumire/{denumire}")
    public Activitate_Parc findByName(@PathVariable("denumire") String denumire){
        return activitate_parcService.findByName(denumire);
    }

//    @GetMapping(value = "/activitati")
//    public List<ActivitateResponseDto> findAll(){
//        List<Activitate_Parc> activitate_parcList = activitate_parcService.findAll();
//        List<ActivitateResponseDto> activitateResponseDtoList = new ArrayList<>();
//        for(Activitate_Parc activitate_parc : activitate_parcList) {
//            activitateResponseDtoList.add(mapActivitateToActivitateResponseDto(activitate_parc));
//        }
//        return activitateResponseDtoList;
//    }

    @GetMapping(value = "/activitati")
    public Page<ActivitateRequestDto> searchProducts(@RequestParam Map<String, String> params) {
        Page<Activitate_Parc> activitatiList = activitate_parcService.searchActivities(params);
        return new PageImpl<ActivitateRequestDto>(
                activitatiList
                        .getContent()
                        .stream()
                        .map(this::mapActivitateToActivitateResponseDto)
                        .collect(Collectors.toList()),
                activitatiList.getPageable(),
                activitatiList.getTotalElements()
        );
    }

    @GetMapping(value = "/tipuriActivitate")
    public List<TipActivitate> getTipuriActivitate() {
        return Arrays.asList(TipActivitate.values());
    }

    @GetMapping(value = "/niveleDificultate")
    public List<NivelDificultate> getNiveleDificultate() {
        return Arrays.asList(NivelDificultate.values());
    }

    @GetMapping(value = "/zone")
    public List<Harta> getZoneHarta() {
        return hartaService.findAll();
    }

    @GetMapping("/harta/{denumire}")
    public List<Activitate_Parc> findByZonaHarta(@PathVariable("denumire")  String denumire){
        Harta harta = hartaService.findByDenumire(denumire);
        return activitate_parcService.findByZonaHarta(harta);
    }

//    @GetMapping("/harta")
//    public List<ActivitateResponseDto> getActivitiesWithZones() {
//        List<Activitate_Parc> activitati = activitate_parcService.findAll(); // sau orice altă metodă de obținere a activităților
//        List<ActivitateResponseDto> activitatiResponse = new ArrayList<>();
//
//        for (Activitate_Parc activitate : activitati) {
//            ActivitateResponseDto activitateResponse = mapActivitateToActivitateResponseDto(activitate);
//            activitatiResponse.add(activitateResponse);
//        }
//
//        return activitatiResponse;
//    }

    @DeleteMapping("/activitati/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        // Check if there are any reservations associated with the activity
        String sql = "SELECT COUNT(*) FROM rezervari WHERE activitate_id = ?";
        int reservationCount = jdbcTemplate.queryForObject(sql, Integer.class, id);

        if (reservationCount > 0) {
            return ResponseEntity.badRequest().body("Cannot delete activity with reservations.");
        }

        // Continue with the deletion logic as before
        Activitate_Parc activitate_parc = activitate_parcService.findById(id);

        if (activitate_parc == null) {
            return ResponseEntity.notFound().build();
        }

        // Detach or remove the zonaHarta associations to prevent deletion
        activitate_parc.setZonaHarta(Collections.emptySet()); // Assuming zonaHarta is a Set

        // Delete the Activitate_Parc entity without affecting zonaHarta
        activitate_parcService.delete(id);

        return ResponseEntity.ok("Activity deleted successfully.");
    }

//    @DeleteMapping("/activitati/delete/{id}")
//    public HttpStatus delete(@PathVariable("id") Long id) {
//        Activitate_Parc activitate_parc = activitate_parcService.findById(id);
//
//        if (activitate_parc != null) {
//            // Detach or remove the zonaHarta associations to prevent deletion
//            activitate_parc.setZonaHarta(Collections.emptySet()); // Assuming zonaHarta is a Set
//
//            // Delete the Activitate_Parc entity without affecting zonaHarta
//            activitate_parcService.delete(id);
//
//            return HttpStatus.OK;
//        } else {
//            return HttpStatus.NOT_FOUND;
//        }
//    }


    @PutMapping(value = "/activitati/{id}")
    public ActivitateRequestDto update(@PathVariable("id")Long id, @RequestBody ActivitateRequestDto activitateRequestDto){
        Activitate_Parc updateActivitate_parc = updateActivitateDtoToActivitate(activitate_parcService.findById(id), activitateRequestDto);
        return mapActivitateToActivitateResponseDto(activitate_parcService.create(updateActivitate_parc));
    }


    private ActivitateResponseDto mapActivitateToActivitateResponseDto(Activitate_Parc activitate_parc){
        ActivitateResponseDto activitateResponseDto = new ActivitateResponseDto();
        activitateResponseDto.setId(activitate_parc.getId());
        activitateResponseDto.setDenumire(activitate_parc.getDenumire());
        activitateResponseDto.setDescriere(activitate_parc.getDescriere());
        activitateResponseDto.setDurata(activitate_parc.getDurata());
        activitateResponseDto.setNivelDificultate(activitate_parc.getNivelDificultate());
        activitateResponseDto.setProgram(activitate_parc.getProgram());
        activitateResponseDto.setVarstaMinima(activitate_parc.getVarstaMinima());
        activitateResponseDto.setTipActivitate(activitate_parc.getTipActivitate());
        Set<Harta> zoneHarta = activitate_parc.getZonaHarta();
        for (Harta harta : zoneHarta){
            activitateResponseDto.setZonaHarta(harta.getDenumire());

        }
        activitateResponseDto.setAnimale(activitate_parc.getAnimale());
        activitateResponseDto.setRezervari(activitate_parc.getRezervari());
        activitateResponseDto.setImagine(activitate_parc.getImagine());


        return activitateResponseDto;
    }

    private Activitate_Parc mapActivitateRequestDtoToActivitate(ActivitateRequestDto activitateRequestDto){
        Activitate_Parc activitate_parc = new Activitate_Parc();
        activitate_parc.setDenumire(activitateRequestDto.getDenumire());
        activitate_parc.setDescriere(activitateRequestDto.getDescriere());
        activitate_parc.setDurata(activitateRequestDto.getDurata());
        activitate_parc.setNivelDificultate(activitateRequestDto.getNivelDificultate());
        activitate_parc.setProgram(activitateRequestDto.getProgram());
        activitate_parc.setVarstaMinima(activitateRequestDto.getVarstaMinima());
        activitate_parc.setTipActivitate(activitateRequestDto.getTipActivitate());
        Harta harta = hartaService.findByDenumire(activitateRequestDto.getZonaHarta());
        Set<Harta> zoneHarta = new HashSet<>();
        zoneHarta.add(harta);
        activitate_parc.setZonaHarta(zoneHarta);
        activitate_parc.setAnimale(activitateRequestDto.getAnimale());
        activitate_parc.setRezervari(activitateRequestDto.getRezervari());
        activitate_parc.setImagine(activitateRequestDto.getImagine());

        return activitate_parc;
    }

    private Activitate_Parc updateActivitateDtoToActivitate(Activitate_Parc activitate_parc, ActivitateRequestDto activitateRequestDto){

        activitate_parc.setDenumire(activitateRequestDto.getDenumire());
        activitate_parc.setDescriere(activitateRequestDto.getDescriere());
        activitate_parc.setDurata(activitateRequestDto.getDurata());
        activitate_parc.setNivelDificultate(activitateRequestDto.getNivelDificultate());
        activitate_parc.setProgram(activitateRequestDto.getProgram());
        activitate_parc.setVarstaMinima(activitateRequestDto.getVarstaMinima());
        activitate_parc.setTipActivitate(activitateRequestDto.getTipActivitate());
        
        //TODO
//        activitate_parc.setImagine(activitateRequestDto.getImagine());
        Harta harta = hartaService.findByDenumire(activitateRequestDto.getZonaHarta());
        Set<Harta> zoneHarta = new HashSet<>();
        zoneHarta.add(harta);
        activitate_parc.setZonaHarta(zoneHarta);
//        activitate_parc.setAnimale(activitateRequestDto.getAnimale());
//        activitate_parc.setRezervari(activitateRequestDto.getRezervari());


        return activitate_parc;
    }
}
