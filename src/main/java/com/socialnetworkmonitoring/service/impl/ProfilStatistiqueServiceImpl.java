package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.models.ProfilStatistique;
import com.socialnetworkmonitoring.repository.ProfilStatistiqueRepository;
import com.socialnetworkmonitoring.service.ProfilStatistiqueService;
import com.socialnetworkmonitoring.service.dto.ProfilStatistiqueDTO;
import com.socialnetworkmonitoring.service.dto.StatisticDataDTO;
import com.socialnetworkmonitoring.service.dto.StatisticSetDTO;
import com.socialnetworkmonitoring.service.mapper.ProfilStatistiqueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.*;
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

    @Override
    public StatisticDataDTO findAllYoutubeViewsStatisticSet() {
        StatisticDataDTO statisticDataDTO = new StatisticDataDTO(new ArrayList<>(), new ArrayList<>());
        List<ProfilStatistique> profilStatistiqueList = this.profilStatistiqueRepository.findAll();
        if(!CollectionUtils.isEmpty(profilStatistiqueList)) {
            // Préparation des données
                // 1- Groupe les données par date
            Map<Date,List<ProfilStatistique>> statistiqueMap = profilStatistiqueList.stream().
                    collect(
                            groupingBy(
                                ProfilStatistique::getDateStatistique
            ));
                // 2- Groupe les données par profil
            Map<Date,Map<String, List<ProfilStatistique>>> statistiqueByProfilAndDate = new HashMap<>();
            for(Map.Entry<Date,List<ProfilStatistique>> entry : statistiqueMap.entrySet()) {
                Map<String, List<ProfilStatistique>> dataByProfile = entry.getValue().stream().collect(
                        groupingBy(
                                profilStatistique -> profilStatistique.getProfil().getNom()
                        )
                );
                statistiqueByProfilAndDate.put(entry.getKey(),dataByProfile);
            }
                // 3- Get Youtube Views
            Map<String,List<BigInteger>> dataByProfile = new HashMap<>();
            for(Map.Entry<Date,Map<String, List<ProfilStatistique>>> entry : statistiqueByProfilAndDate.entrySet()) {
                for(Map.Entry<String,List<ProfilStatistique>> entry1 : entry.getValue().entrySet()) {
                    List<BigInteger> data = !CollectionUtils.isEmpty(dataByProfile.get(entry1.getKey())) ? dataByProfile.get(entry1.getKey()): new ArrayList<>();
                    data.add((CollectionUtils.isEmpty(entry1.getValue()) ? null : entry1.getValue().get(0).getNombreVuesYoutube()));
                    dataByProfile.put(entry1.getKey(),data);
                }
            }
                // 4- Conversion of data
            for(Map.Entry<String,List<BigInteger>> entry : dataByProfile.entrySet()) {
                StatisticSetDTO statisticSetDTO = new StatisticSetDTO();
                statisticSetDTO.setName(entry.getKey());
                statisticSetDTO.setData(entry.getValue());
                statisticSetDTO.setType("line");
                statisticDataDTO.getStatisticSet().add((statisticSetDTO));
            }
                // 5- List of labels
            List<String> labels = statistiqueByProfilAndDate.keySet().stream().map(
                    Date::toString
            ).collect(Collectors.toList());
            statisticDataDTO.setLabels(labels);
        }
        return statisticDataDTO;
    }

    private static ProfilStatistiqueDTO getNombreFollowers(List<ProfilStatistiqueDTO> profilStatistiqueDTOS, Predicate<ProfilStatistiqueDTO> predicate) {
        List<ProfilStatistiqueDTO> profilStatistiqueDTOListTemp = profilStatistiqueDTOS
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return !CollectionUtils.isEmpty(profilStatistiqueDTOListTemp) ? profilStatistiqueDTOListTemp.get(0) : null;
    }
}
