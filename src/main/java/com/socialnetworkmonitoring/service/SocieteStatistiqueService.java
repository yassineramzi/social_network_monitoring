package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.service.dto.SocieteStatistiqueDTO;
import com.socialnetworkmonitoring.service.dto.StatisticSetDTO;

import java.util.List;

public interface SocieteStatistiqueService {
    SocieteStatistiqueDTO create(SocieteStatistiqueDTO societeStatistiqueDTO);
    List<SocieteStatistiqueDTO> findSocieteStatistiquesByIdSociete(Long idSociete);
    List<StatisticSetDTO> findAllGazoilStatisticSet();
    List<StatisticSetDTO> findAllEssenceStatisticSet();
}
