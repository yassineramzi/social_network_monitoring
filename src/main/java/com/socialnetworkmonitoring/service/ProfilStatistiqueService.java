package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.service.dto.ProfilStatistiqueDTO;
import com.socialnetworkmonitoring.service.dto.StatisticSetDTO;

import java.util.List;

public interface ProfilStatistiqueService {
    List<ProfilStatistiqueDTO> findProfilStatistiquesByIdProfil(Long idProfil);
    List<StatisticSetDTO>  findAllYoutubeViewsStatisticSet();
    List<StatisticSetDTO>  findAllYoutubeSubscribersStatisticSet();
    List<StatisticSetDTO>  findAllTwitterFollowersStatisticSet();
    List<StatisticSetDTO>  findAllInstagramFollowersStatisticSet();
}
