package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.repository.ProfilStatistiqueRepository;
import com.socialnetworkmonitoring.service.ProfilStatistiqueService;
import com.socialnetworkmonitoring.service.dto.ProfilStatistiqueDTO;
import com.socialnetworkmonitoring.service.mapper.ProfilStatistiqueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ProfilStatistiqueServiceImpl implements ProfilStatistiqueService {

    private ProfilStatistiqueRepository profilStatistiqueRepository;

    private ProfilStatistiqueMapper profilStatistiqueMapper;

    @Autowired
    public ProfilStatistiqueServiceImpl(ProfilStatistiqueRepository profilStatistiqueRepository, ProfilStatistiqueMapper profilStatistiqueMapper) {
        this.profilStatistiqueRepository = profilStatistiqueRepository;
        this.profilStatistiqueMapper = profilStatistiqueMapper;
    }

    @Override
    public List<ProfilStatistiqueDTO> findProfilStatistiquesByIdProfil(Long idProfil) {
        Map<Date,List<ProfilStatistiqueDTO>> statistiqueDTOMap = this.profilStatistiqueRepository.findByProfilId(idProfil).parallelStream().map(
                profilStatistique -> this.profilStatistiqueMapper.toDto(profilStatistique)
        ).collect(            groupingBy(
                ProfilStatistiqueDTO::getDateStatistique
        ));
        List<ProfilStatistiqueDTO> profilStatistiqueDTOList = new ArrayList<>();
        for(Map.Entry<Date,List<ProfilStatistiqueDTO>> entry : statistiqueDTOMap.entrySet()) {
            List<ProfilStatistiqueDTO> profilStatistiqueDTOListTemp  = entry.getValue();
            ProfilStatistiqueDTO profilStatistiqueDTO = new ProfilStatistiqueDTO();
            if(!CollectionUtils.isEmpty(profilStatistiqueDTOListTemp)){
                profilStatistiqueDTO.setDateStatistique(profilStatistiqueDTOListTemp.get(0).getDateStatistique());
                // Twitter
                ProfilStatistiqueDTO profilStatistiqueDTOTwitter = getNombreFollowers(profilStatistiqueDTOListTemp, profilStatistiqueDTO1 -> {return profilStatistiqueDTO1.getNombreFollowersTwitter()!=null;});
                profilStatistiqueDTO.setNombreFollowersTwitter(profilStatistiqueDTOTwitter!=null ? profilStatistiqueDTOTwitter.getNombreFollowersTwitter() : null);
                // Youtube
                ProfilStatistiqueDTO profilStatistiqueDTOYoutube = getNombreFollowers(profilStatistiqueDTOListTemp, profilStatistiqueDTO1 -> {return profilStatistiqueDTO1.getNombreFollowersYoutube()!=null;});
                profilStatistiqueDTO.setNombreFollowersYoutube(profilStatistiqueDTOYoutube!=null ? profilStatistiqueDTOYoutube.getNombreFollowersYoutube() : null);
                profilStatistiqueDTO.setNombreVuesYoutube(profilStatistiqueDTOYoutube!=null ? profilStatistiqueDTOYoutube.getNombreVuesYoutube() : null);
                // Instagram
                ProfilStatistiqueDTO profilStatistiqueDTOInstagram = getNombreFollowers(profilStatistiqueDTOListTemp, profilStatistiqueDTO1 -> {return profilStatistiqueDTO1.getNombreFollowersInstagram()!=null;});
                profilStatistiqueDTO.setNombreFollowersInstagram(profilStatistiqueDTOInstagram!=null ? profilStatistiqueDTOInstagram.getNombreFollowersInstagram() : null);
            }
            profilStatistiqueDTOList.add(profilStatistiqueDTO);
        }
        return profilStatistiqueDTOList;
    }

    private static ProfilStatistiqueDTO getNombreFollowers(List<ProfilStatistiqueDTO> profilStatistiqueDTOS, Predicate<ProfilStatistiqueDTO> predicate) {
        List<ProfilStatistiqueDTO> profilStatistiqueDTOListTemp = profilStatistiqueDTOS
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return !CollectionUtils.isEmpty(profilStatistiqueDTOListTemp) ? profilStatistiqueDTOListTemp.get(0) : null;
    }
}
