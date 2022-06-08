package com.socialnetworkmonitoring.web.resource;

import com.socialnetworkmonitoring.service.ProfilStatistiqueService;
import com.socialnetworkmonitoring.service.dto.ProfilStatistiqueDTO;
import com.socialnetworkmonitoring.service.dto.StatisticDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profil-statistique")
public class ProfilStatistiqueResource {

    private static final String ENTITY_NAME = "profilStatistiqueDTO";

    private final Logger log = LoggerFactory.getLogger(ProfilStatistiqueResource.class);

    private ProfilStatistiqueService profilStatistiqueService;

    @Autowired
    public ProfilStatistiqueResource(ProfilStatistiqueService profilStatistiqueService) {
        this.profilStatistiqueService = profilStatistiqueService;
    }

    @GetMapping("/{idProfil}")
    public ResponseEntity<List<ProfilStatistiqueDTO>> findByProfilId(@PathVariable("idProfil") Long idProfil) {
        log.info("Récupération de toutes les statistiques, du profil : "+idProfil);
        return ResponseEntity.ok(this.profilStatistiqueService.findProfilStatistiquesByIdProfil(idProfil));
    }

    @GetMapping("/youtube/views")
    public ResponseEntity<StatisticDataDTO> findAllYoutubeViewsStatisticSet(){
        log.info("Récupération de toutes les statistiques, des vues Youtube");
        return ResponseEntity.ok(this.profilStatistiqueService.findAllYoutubeViewsStatisticSet());
    }

    @GetMapping("/youtube/subscribers")
    public ResponseEntity<StatisticDataDTO> findAllYoutubeSubscribersStatisticSet(){
        log.info("Récupération de toutes les statistiques, des abonnés Youtube");
        return ResponseEntity.ok(this.profilStatistiqueService.findAllYoutubeSubscribersStatisticSet());
    }

    @GetMapping("/twitter/followers")
    public ResponseEntity<StatisticDataDTO> findAllTwitterFollowersStatisticSet(){
        log.info("Récupération de toutes les statistiques, des followers Twitter");
        return ResponseEntity.ok(this.profilStatistiqueService.findAllTwitterFollowersStatisticSet());
    }

    @GetMapping("/instagram/followers")
    public ResponseEntity<StatisticDataDTO> findAllInstagramFollowersStatisticSet(){
        log.info("Récupération de toutes les statistiques, des followers Instagram");
        return ResponseEntity.ok(this.profilStatistiqueService.findAllInstagramFollowersStatisticSet());
    }
}
