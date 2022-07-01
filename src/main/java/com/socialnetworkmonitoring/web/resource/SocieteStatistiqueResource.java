package com.socialnetworkmonitoring.web.resource;

import com.socialnetworkmonitoring.service.SocieteStatistiqueService;
import com.socialnetworkmonitoring.service.dto.SocieteStatistiqueDTO;
import com.socialnetworkmonitoring.service.dto.StatisticSetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/societe-statistiques")
public class SocieteStatistiqueResource {
    private final Logger log = LoggerFactory.getLogger(SocieteStatistiqueResource.class);

    private final SocieteStatistiqueService societeStatistiqueService;

    public SocieteStatistiqueResource(final SocieteStatistiqueService societeStatistiqueService){
        this.societeStatistiqueService = societeStatistiqueService;
    }

    @PostMapping("/create")
    public ResponseEntity<SocieteStatistiqueDTO> create(@RequestBody SocieteStatistiqueDTO societeStatistiqueDTO) {
        log.info("Création d'une statistique societe : {}", societeStatistiqueDTO);
        SocieteStatistiqueDTO result = this.societeStatistiqueService.create(societeStatistiqueDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{idSociete}/societe")
    public ResponseEntity<List<SocieteStatistiqueDTO>> findByProfilId(@PathVariable("idSociete") Long idSociete) {
        log.info("Récupération de toutes les statistiques, de la société : {}", idSociete);
        return ResponseEntity.ok(this.societeStatistiqueService.findSocieteStatistiquesByIdSociete(idSociete));
    }

    @GetMapping("/gazoil-statistics")
    public ResponseEntity<List<StatisticSetDTO>> findAllGazoilStatisticSet(){
        log.info("Récupération de toutes les statistiques, du Gazoil");
        return ResponseEntity.ok(this.societeStatistiqueService.findAllGazoilStatisticSet());
    }

    @GetMapping("/essence-statistics")
    public ResponseEntity<List<StatisticSetDTO>> findAllEssenceStatisticSet(){
        log.info("Récupération de toutes les statistiques, de l'Essence");
        return ResponseEntity.ok(this.societeStatistiqueService.findAllEssenceStatisticSet());
    }
}
