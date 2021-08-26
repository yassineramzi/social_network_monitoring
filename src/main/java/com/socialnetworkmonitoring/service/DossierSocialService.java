package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.service.dto.DossierSocialDTO;

public interface DossierSocialService {
    DossierSocialDTO create(DossierSocialDTO dossierSocialDTO);

    DossierSocialDTO update(DossierSocialDTO dossierSocialDTO);

    void delete(Long idDossierSocial);
}
