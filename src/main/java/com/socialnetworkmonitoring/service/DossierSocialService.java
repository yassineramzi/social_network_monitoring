package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.service.dto.DossierSocialDTO;

import java.util.List;

public interface DossierSocialService {
    DossierSocialDTO create(DossierSocialDTO dossierSocialDTO) throws EntityAlreadyExistException;

    DossierSocialDTO update(DossierSocialDTO dossierSocialDTO) throws EntityAlreadyExistException;

    void delete(Long idDossierSocial);

    List<DossierSocialDTO> findAll();
}
