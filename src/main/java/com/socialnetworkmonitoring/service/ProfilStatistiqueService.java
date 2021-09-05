package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.service.dto.ProfilStatistiqueDTO;

import java.util.List;

public interface ProfilStatistiqueService {
    List<ProfilStatistiqueDTO> findProfilStatistiquesByIdProfil(Long idProfil);
}
