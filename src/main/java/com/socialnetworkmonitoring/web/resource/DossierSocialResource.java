package com.socialnetworkmonitoring.web.resource;

import com.socialnetworkmonitoring.service.DossierSocialService;
import com.socialnetworkmonitoring.service.dto.DossierSocialDTO;
import com.socialnetworkmonitoring.web.error.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dossier")
public class DossierSocialResource {
    private static final String ENTITY_NAME = "dossierSocialDTO";

    private final Logger log = LoggerFactory.getLogger(DossierSocialResource.class);

    private DossierSocialService dossierSocialService;

    public DossierSocialResource(DossierSocialService dossierSocialService) {
        this.dossierSocialService = dossierSocialService;
    }

    @PostMapping("/create")
    public ResponseEntity<DossierSocialDTO> create(@RequestBody DossierSocialDTO dossierSocialDTO) throws ApiException{
        log.info("Création d'un dossier social :", dossierSocialDTO);
        if(dossierSocialDTO.getId() != null) {
            throw new ApiException("idexists", "Une alerte existe dèjà avec cet ID : "+dossierSocialDTO.getId(), ENTITY_NAME);
        }
        return ResponseEntity.ok(this.dossierSocialService.create(dossierSocialDTO));
    }
}
