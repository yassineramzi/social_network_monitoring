package com.socialnetworkmonitoring.web.resource;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.service.ProfilService;
import com.socialnetworkmonitoring.service.dto.ProfilDTO;
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
@RequestMapping("/api/profil")
public class ProfilResource {

    private static final String ENTITY_NAME = "profilDTO";

    private final Logger log = LoggerFactory.getLogger(ProfilResource.class);

    private ProfilService profilService;

    @Autowired
    public ProfilResource(ProfilService profilService) {
        this.profilService = profilService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProfilDTO> create(@RequestBody ProfilDTO profilDTO) throws ApiException, EntityAlreadyExistException, URISyntaxException {
        log.info("Création d'un profil social :", profilDTO);
        if(profilDTO.getId() != null) {
            throw new ApiException("idexists", "Un nouveau profil ne peut avoir un Id : "+profilDTO.getId(), ENTITY_NAME);
        }
        ProfilDTO result = this.profilService.create(profilDTO);
        return ResponseEntity.created(new URI("/api/profil/create" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfilDTO> update(@RequestBody ProfilDTO profilDTO) throws ApiException, EntityAlreadyExistException, URISyntaxException {
        log.info("Modification d'un profil social :", profilDTO);
        if(profilDTO.getId() == null) {
            throw new ApiException("idnotexists", "Pour modifier un profil, il faut avoir un Id : "+profilDTO.getId(), ENTITY_NAME);
        }
        ProfilDTO result = this.profilService.update(profilDTO);
        return ResponseEntity.created(new URI("/api/profil/update" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @GetMapping("/{idDossier}")
    public ResponseEntity<List<ProfilDTO>> findByDossierId(@PathVariable("idDossier") Long idDossier) {
        log.info("Récupération de tous des profils, du dossier:"+idDossier);
        return ResponseEntity.ok(this.profilService.findProfilsByIdDossier(idDossier));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        log.info("Suppression du profil : "+id);
        this.profilService.delete(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
                .build();
    }
}
