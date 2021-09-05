package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.service.dto.ProfilDTO;

import java.util.List;
import java.util.Optional;

public interface ProfilService {
    ProfilDTO create(ProfilDTO profilDTO) throws EntityAlreadyExistException;

    ProfilDTO update(ProfilDTO profilDTO) throws EntityAlreadyExistException;
    
    List<ProfilDTO> findProfilsByIdDossier(Long idDossier);

    Optional<ProfilDTO> findOne(Long id);

    void delete(Long idProfil);
}
