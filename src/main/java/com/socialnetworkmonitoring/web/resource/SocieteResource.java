package com.socialnetworkmonitoring.web.resource;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.service.SocieteService;
import com.socialnetworkmonitoring.service.dto.SocieteDTO;
import com.socialnetworkmonitoring.web.error.ApiException;
import com.socialnetworkmonitoring.web.util.HeaderUtil;
import com.socialnetworkmonitoring.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/societe")
public class SocieteResource {
    private static final String ENTITY_NAME = "societeDTO";

    private final Logger log = LoggerFactory.getLogger(SocieteResource.class);

    private final SocieteService societeService;

    @Autowired
    public SocieteResource(final SocieteService societeService) {
        this.societeService = societeService;
    }

    @PostMapping("/create")
    public ResponseEntity<SocieteDTO> create(@RequestBody SocieteDTO societeDTO) throws ApiException, EntityAlreadyExistException, URISyntaxException {
        log.info("Création d'une societe : {}", societeDTO);
        if(societeDTO.getId() != null) {
            throw new ApiException("idexists", "Un nouveau societe ne peut avoir un Id : "+societeDTO.getId(), ENTITY_NAME);
        }
        SocieteDTO result = this.societeService.create(societeDTO);
        return ResponseEntity.created(new URI("/api/societe/create" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SocieteDTO>> findAll() {
        log.info("Récupération de toutes les societes :");
        return ResponseEntity.ok(this.societeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocieteDTO> findById(@PathVariable("id") Long id) {
        log.info("Récupération de la societe par Id : {}",id);
        Optional<SocieteDTO> societeDTOOptional = this.societeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(societeDTOOptional);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        log.info("Suppression d'une societe par Id : {}",id);
        this.societeService.delete(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
                .build();
    }
}
