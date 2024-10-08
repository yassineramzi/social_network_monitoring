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

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ProfilStatistiqueServiceImpl implements ProfilStatistiqueService {

    private final ProfilStatistiqueRepository profilStatistiqueRepository;

    private final ProfilStatistiqueMapper profilStatistiqueMapper;

    @Autowired
    public ProfilStatistiqueServiceImpl(ProfilStatistiqueRepository profilStatistiqueRepository, ProfilStatistiqueMapper profilStatistiqueMapper) {
        this.profilStatistiqueRepository = profilStatistiqueRepository;
        this.profilStatistiqueMapper = profilStatistiqueMapper;
    }

    @Override
    public List<ProfilStatistiqueDTO> findProfilStatistiquesByIdProfil(Long idProfil) {
        Map<Date,List<ProfilStatistiqueDTO>> statistiqueDTOMap = this.profilStatistiqueRepository.findByProfilId(idProfil).stream().map(
                this.profilStatistiqueMapper::toDto
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
                ProfilStatistiqueDTO profilStatistiqueDTOTwitter = getNombreFollowers(profilStatistiqueDTOListTemp, profilStatistiqueDTO1 -> profilStatistiqueDTO1.getNombreFollowersTwitter()!=null);
                profilStatistiqueDTO.setNombreFollowersTwitter(profilStatistiqueDTOTwitter!=null ? profilStatistiqueDTOTwitter.getNombreFollowersTwitter() : null);
                // Youtube
                ProfilStatistiqueDTO profilStatistiqueDTOYoutube = getNombreFollowers(profilStatistiqueDTOListTemp, profilStatistiqueDTO1 -> profilStatistiqueDTO1.getNombreFollowersYoutube()!=null);
                profilStatistiqueDTO.setNombreFollowersYoutube(profilStatistiqueDTOYoutube!=null ? profilStatistiqueDTOYoutube.getNombreFollowersYoutube() : null);
                profilStatistiqueDTO.setNombreVuesYoutube(profilStatistiqueDTOYoutube!=null ? profilStatistiqueDTOYoutube.getNombreVuesYoutube() : null);
                // Instagram
                ProfilStatistiqueDTO profilStatistiqueDTOInstagram = getNombreFollowers(profilStatistiqueDTOListTemp, profilStatistiqueDTO1 -> profilStatistiqueDTO1.getNombreFollowersInstagram()!=null);
                profilStatistiqueDTO.setNombreFollowersInstagram(profilStatistiqueDTOInstagram!=null ? profilStatistiqueDTOInstagram.getNombreFollowersInstagram() : null);
            }
            profilStatistiqueDTOList.add(profilStatistiqueDTO);
        }
        return profilStatistiqueDTOList;
    }

    @Override
    public List<StatisticSetDTO> findAllYoutubeViewsStatisticSet() {
        List<StatisticSetDTO> statisticSetDTOS = new ArrayList<>();
        List<ProfilStatistique> profilStatistiqueList = this.profilStatistiqueRepository.findAll();
        if(!CollectionUtils.isEmpty(profilStatistiqueList)) {
            // Sort data by date
            Comparator<ProfilStatistique> reverseComparator = (c1, c2) -> c2.getDateStatistique().compareTo(c1.getDateStatistique());
            profilStatistiqueList.sort(reverseComparator);
            // Préparation des données
            // 1- Groupe les données par date
            Map<Date,List<ProfilStatistique>> statistiqueMap = profilStatistiqueList
                    .stream()
                    .collect(
                            groupingBy(
                                    ProfilStatistique::getDateStatistique
                            ));
            // 2- Groupe les données par profil
            TreeMap<Date,Map<String, List<ProfilStatistique>>> statistiqueByProfilAndDate = new TreeMap<>();
            for(Map.Entry<Date,List<ProfilStatistique>> entry : statistiqueMap.entrySet()) {
                Map<String, List<ProfilStatistique>> dataByProfile = entry.getValue().stream().collect(
                        groupingBy(
                                profilStatistique -> profilStatistique.getProfil().getNom()
                        )
                );
                statistiqueByProfilAndDate.put(entry.getKey(),dataByProfile);
            }
            // 3- Get Data
            TreeMap<String,List<StatisticDataDTO>> dataByProfile = new TreeMap<>();
            for(Map.Entry<Date,Map<String, List<ProfilStatistique>>> entry : statistiqueByProfilAndDate.entrySet()) {
                for(Map.Entry<String,List<ProfilStatistique>> entry1 : entry.getValue().entrySet()) {
                    List<StatisticDataDTO> data = !CollectionUtils.isEmpty(dataByProfile.get(entry1.getKey())) ? dataByProfile.get(entry1.getKey()): new ArrayList<>();
                    ProfilStatistique profilStatistiqueTemp = getProfilStatistiqueFromListByPredicate(
                            entry1.getValue(),
                            profilStatistique -> profilStatistique.getNombreVuesYoutube()!=null
                    );
                    if(profilStatistiqueTemp!=null) {
                        data.add(new StatisticDataDTO(
                                profilStatistiqueTemp.getDateStatistique().toString(),
                                profilStatistiqueTemp.getNombreVuesYoutube()
                        ));
                    }
                    dataByProfile.put(entry1.getKey(),data);
                }
            }
            // 4- Conversion of data
            for(Map.Entry<String,List<StatisticDataDTO>> entry : dataByProfile.entrySet()) {
                StatisticSetDTO statisticSetDTO = new StatisticSetDTO();
                statisticSetDTO.setName(entry.getKey());
                statisticSetDTO.setData(entry.getValue());
                statisticSetDTOS.add(statisticSetDTO);
            }
        }
        return statisticSetDTOS;
    }

    @Override
    public List<StatisticSetDTO> findAllYoutubeSubscribersStatisticSet() {
        List<StatisticSetDTO> statisticSetDTOS = new ArrayList<>();
        List<ProfilStatistique> profilStatistiqueList = this.profilStatistiqueRepository.findAll();
        if(!CollectionUtils.isEmpty(profilStatistiqueList)) {
            // Sort data by date
            Comparator<ProfilStatistique> reverseComparator = (c1, c2) -> c2.getDateStatistique().compareTo(c1.getDateStatistique());
            profilStatistiqueList.sort(reverseComparator);
            // Préparation des données
            // 1- Groupe les données par date
            Map<Date,List<ProfilStatistique>> statistiqueMap = profilStatistiqueList
                    .stream()
                    .collect(
                            groupingBy(
                                    ProfilStatistique::getDateStatistique
                            ));
            // 2- Groupe les données par profil
            TreeMap<Date,Map<String, List<ProfilStatistique>>> statistiqueByProfilAndDate = new TreeMap<>();
            for(Map.Entry<Date,List<ProfilStatistique>> entry : statistiqueMap.entrySet()) {
                Map<String, List<ProfilStatistique>> dataByProfile = entry.getValue().stream().collect(
                        groupingBy(
                                profilStatistique -> profilStatistique.getProfil().getNom()
                        )
                );
                statistiqueByProfilAndDate.put(entry.getKey(),dataByProfile);
            }
            // 3- Get Data
            TreeMap<String,List<StatisticDataDTO>> dataByProfile = new TreeMap<>();
            for(Map.Entry<Date,Map<String, List<ProfilStatistique>>> entry : statistiqueByProfilAndDate.entrySet()) {
                for(Map.Entry<String,List<ProfilStatistique>> entry1 : entry.getValue().entrySet()) {
                    List<StatisticDataDTO> data = !CollectionUtils.isEmpty(dataByProfile.get(entry1.getKey())) ? dataByProfile.get(entry1.getKey()): new ArrayList<>();
                    ProfilStatistique profilStatistiqueTemp = getProfilStatistiqueFromListByPredicate(
                            entry1.getValue(),
                            profilStatistique -> profilStatistique.getNombreFollowersYoutube()!=null
                    );
                    if(profilStatistiqueTemp!=null) {
                        data.add(new StatisticDataDTO(
                                profilStatistiqueTemp.getDateStatistique().toString(),
                                profilStatistiqueTemp.getNombreFollowersYoutube()
                        ));
                    }
                    dataByProfile.put(entry1.getKey(),data);
                }
            }
            // 4- Conversion of data
            for(Map.Entry<String,List<StatisticDataDTO>> entry : dataByProfile.entrySet()) {
                StatisticSetDTO statisticSetDTO = new StatisticSetDTO();
                statisticSetDTO.setName(entry.getKey());
                statisticSetDTO.setData(entry.getValue());
                statisticSetDTOS.add(statisticSetDTO);
            }
        }
        return statisticSetDTOS;
    }

    @Override
    public List<StatisticSetDTO> findAllTwitterFollowersStatisticSet() {
        List<StatisticSetDTO> statisticSetDTOS = new ArrayList<>();
        List<ProfilStatistique> profilStatistiqueList = this.profilStatistiqueRepository.findAll();
        if(!CollectionUtils.isEmpty(profilStatistiqueList)) {
            // Sort data by date
            Comparator<ProfilStatistique> reverseComparator = (c1, c2) -> c2.getDateStatistique().compareTo(c1.getDateStatistique());
            profilStatistiqueList.sort(reverseComparator);
            // Préparation des données
            // 1- Groupe les données par date
            Map<Date,List<ProfilStatistique>> statistiqueMap = profilStatistiqueList
                    .stream()
                    .collect(
                            groupingBy(
                                    ProfilStatistique::getDateStatistique
                            ));
            // 2- Groupe les données par profil
            TreeMap<Date,Map<String, List<ProfilStatistique>>> statistiqueByProfilAndDate = new TreeMap<>();
            for(Map.Entry<Date,List<ProfilStatistique>> entry : statistiqueMap.entrySet()) {
                Map<String, List<ProfilStatistique>> dataByProfile = entry.getValue().stream().collect(
                        groupingBy(
                                profilStatistique -> profilStatistique.getProfil().getNom()
                        )
                );
                statistiqueByProfilAndDate.put(entry.getKey(),dataByProfile);
            }
            // 3- Get Data
            TreeMap<String,List<StatisticDataDTO>> dataByProfile = new TreeMap<>();
            for(Map.Entry<Date,Map<String, List<ProfilStatistique>>> entry : statistiqueByProfilAndDate.entrySet()) {
                for(Map.Entry<String,List<ProfilStatistique>> entry1 : entry.getValue().entrySet()) {
                    List<StatisticDataDTO> data = !CollectionUtils.isEmpty(dataByProfile.get(entry1.getKey())) ? dataByProfile.get(entry1.getKey()): new ArrayList<>();
                    ProfilStatistique profilStatistiqueTemp = getProfilStatistiqueFromListByPredicate(
                            entry1.getValue(),
                            profilStatistique -> profilStatistique.getNombreFollowersTwitter()!=null
                    );
                    if(profilStatistiqueTemp!=null) {
                        data.add(new StatisticDataDTO(
                                profilStatistiqueTemp.getDateStatistique().toString(),
                                profilStatistiqueTemp.getNombreFollowersTwitter()
                        ));
                    }
                    dataByProfile.put(entry1.getKey(),data);
                }
            }
            // 4- Conversion of data
            for(Map.Entry<String,List<StatisticDataDTO>> entry : dataByProfile.entrySet()) {
                StatisticSetDTO statisticSetDTO = new StatisticSetDTO();
                statisticSetDTO.setName(entry.getKey());
                statisticSetDTO.setData(entry.getValue());
                statisticSetDTOS.add(statisticSetDTO);
            }
        }
        return statisticSetDTOS;
    }

    @Override
    public List<StatisticSetDTO> findAllInstagramFollowersStatisticSet() {
        List<StatisticSetDTO> statisticSetDTOS = new ArrayList<>();
        List<ProfilStatistique> profilStatistiqueList = this.profilStatistiqueRepository.findAll();
        if(!CollectionUtils.isEmpty(profilStatistiqueList)) {
            // Sort data by date
            Comparator<ProfilStatistique> reverseComparator = (c1, c2) -> c2.getDateStatistique().compareTo(c1.getDateStatistique());
            profilStatistiqueList.sort(reverseComparator);
            // Préparation des données
            // 1- Groupe les données par date
            Map<Date,List<ProfilStatistique>> statistiqueMap = profilStatistiqueList
                    .stream()
                    .collect(
                            groupingBy(
                                    ProfilStatistique::getDateStatistique
                            ));
            // 2- Groupe les données par profil
            TreeMap<Date,Map<String, List<ProfilStatistique>>> statistiqueByProfilAndDate = new TreeMap<>();
            for(Map.Entry<Date,List<ProfilStatistique>> entry : statistiqueMap.entrySet()) {
                Map<String, List<ProfilStatistique>> dataByProfile = entry.getValue().stream().collect(
                        groupingBy(
                                profilStatistique -> profilStatistique.getProfil().getNom()
                        )
                );
                statistiqueByProfilAndDate.put(entry.getKey(),dataByProfile);
            }
            // 3- Get Data
            TreeMap<String,List<StatisticDataDTO>> dataByProfile = new TreeMap<>();
            for(Map.Entry<Date,Map<String, List<ProfilStatistique>>> entry : statistiqueByProfilAndDate.entrySet()) {
                for(Map.Entry<String,List<ProfilStatistique>> entry1 : entry.getValue().entrySet()) {
                    List<StatisticDataDTO> data = !CollectionUtils.isEmpty(dataByProfile.get(entry1.getKey())) ? dataByProfile.get(entry1.getKey()): new ArrayList<>();
                    ProfilStatistique profilStatistiqueTemp = getProfilStatistiqueFromListByPredicate(
                            entry1.getValue(),
                            profilStatistique -> profilStatistique.getNombreFollowersInstagram()!=null
                            );
                    if(profilStatistiqueTemp!=null) {
                        data.add(new StatisticDataDTO(
                                profilStatistiqueTemp.getDateStatistique().toString(),
                                profilStatistiqueTemp.getNombreFollowersInstagram()
                        ));
                    }
                    dataByProfile.put(entry1.getKey(),data);
                }
            }
            // 4- Conversion of data
            for(Map.Entry<String,List<StatisticDataDTO>> entry : dataByProfile.entrySet()) {
                StatisticSetDTO statisticSetDTO = new StatisticSetDTO();
                statisticSetDTO.setName(entry.getKey());
                statisticSetDTO.setData(entry.getValue());
                statisticSetDTOS.add(statisticSetDTO);
            }
        }
        return statisticSetDTOS;
    }

    private static ProfilStatistiqueDTO getNombreFollowers(List<ProfilStatistiqueDTO> profilStatistiqueDTOS, Predicate<ProfilStatistiqueDTO> predicate) {
        List<ProfilStatistiqueDTO> profilStatistiqueDTOListTemp = profilStatistiqueDTOS
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return !CollectionUtils.isEmpty(profilStatistiqueDTOListTemp) ? profilStatistiqueDTOListTemp.get(0) : null;
    }

    private static ProfilStatistique getProfilStatistiqueFromListByPredicate(List<ProfilStatistique> list, Predicate<ProfilStatistique> predicate) {
        List<ProfilStatistique> profilStatistiqueList = list
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return !CollectionUtils.isEmpty(profilStatistiqueList) ? profilStatistiqueList.get(0) : null;
    }
}
