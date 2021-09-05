package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.service.dto.DossierSocialDTO;
import com.socialnetworkmonitoring.service.dto.ProfilDTO;

import java.util.List;
import java.util.Optional;

public interface DossierSocialService {
    DossierSocialDTO create(DossierSocialDTO dossierSocialDTO) throws EntityAlreadyExistException;

    DossierSocialDTO update(DossierSocialDTO dossierSocialDTO) throws EntityAlreadyExistException;

    void delete(Long idDossierSocial);

    Optional<DossierSocialDTO> findOne(Long id);

    List<DossierSocialDTO> findAll();
}
