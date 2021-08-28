package com.socialnetworkmonitoring.web.resource;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.service.DossierSocialService;
import com.socialnetworkmonitoring.service.dto.DossierSocialDTO;
import com.socialnetworkmonitoring.web.error.ApiException;
import com.socialnetworkmonitoring.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/dossier")
public class DossierSocialResource {
    private static final String ENTITY_NAME = "dossierSocialDTO";

    private final Logger log = LoggerFactory.getLogger(DossierSocialResource.class);

    private DossierSocialService dossierSocialService;

    @Autowired
    public DossierSocialResource(DossierSocialService dossierSocialService) {
        this.dossierSocialService = dossierSocialService;
    }

    @PostMapping("/create")
    public ResponseEntity<DossierSocialDTO> create(@RequestBody DossierSocialDTO dossierSocialDTO) throws ApiException, EntityAlreadyExistException, URISyntaxException {
        log.info("Création d'un dossier social :", dossierSocialDTO);
        if(dossierSocialDTO.getId() != null) {
            throw new ApiException("idexists", "Un nouveau dossier ne peut avoir un Id : "+dossierSocialDTO.getId(), ENTITY_NAME);
        }
        DossierSocialDTO result = this.dossierSocialService.create(dossierSocialDTO);
        return ResponseEntity.created(new URI("/api/dossier/create" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<DossierSocialDTO> update(@RequestBody DossierSocialDTO dossierSocialDTO) throws ApiException, EntityAlreadyExistException, URISyntaxException {
        log.info("Modification d'un dossier social :", dossierSocialDTO);
        if(dossierSocialDTO.getId() == null) {
            throw new ApiException("idnotexists", "Pour modifier un dossier, il faut avoir un Id : "+dossierSocialDTO.getId(), ENTITY_NAME);
        }
        DossierSocialDTO result = this.dossierSocialService.update(dossierSocialDTO);
        return ResponseEntity.created(new URI("/api/dossier/update" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DossierSocialDTO>> findAll() {
        log.info("Récupération de tous les dossiers sociaux :");
        return ResponseEntity.ok(this.dossierSocialService.findAll());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        log.info("Suppression du dossier : "+id);
        this.dossierSocialService.delete(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
                .build();
    }
}
