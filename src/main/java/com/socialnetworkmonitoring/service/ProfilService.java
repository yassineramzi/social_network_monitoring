package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.service.dto.ProfilDTO;

import java.util.List;

public interface ProfilService {
    ProfilDTO create(ProfilDTO profilDTO) throws EntityAlreadyExistException;

    ProfilDTO update(ProfilDTO profilDTO) throws EntityAlreadyExistException;
    
    List<ProfilDTO> findProfilsByIdDossier(Long idDossier);

    void delete(Long idProfil);
}
