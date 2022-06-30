package com.socialnetworkmonitoring.web.resource;

import com.socialnetworkmonitoring.service.SocieteStatistiqueService;
import com.socialnetworkmonitoring.service.dto.SocieteStatistiqueDTO;
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
}
