package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.models.SocieteStatistique;
import com.socialnetworkmonitoring.repository.SocieteStatistiqueRepository;
import com.socialnetworkmonitoring.service.SocieteStatistiqueService;
import com.socialnetworkmonitoring.service.dto.SocieteStatistiqueDTO;
import com.socialnetworkmonitoring.service.dto.StatisticDataDTO;
import com.socialnetworkmonitoring.service.dto.StatisticSetDTO;
import com.socialnetworkmonitoring.service.mapper.SocieteStatistiqueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class SocieteStatistiqueServiceImpl implements SocieteStatistiqueService {

    private final SocieteStatistiqueRepository societeStatistiqueRepository;

    private final SocieteStatistiqueMapper societeStatistiqueMapper;

    @Autowired
    public SocieteStatistiqueServiceImpl(
            final SocieteStatistiqueRepository societeStatistiqueRepository,
            final SocieteStatistiqueMapper societeStatistiqueMapper
    ){
        this.societeStatistiqueRepository = societeStatistiqueRepository;
        this.societeStatistiqueMapper = societeStatistiqueMapper;
    }

    @Override
    public SocieteStatistiqueDTO create(SocieteStatistiqueDTO societeStatistiqueDTO){
        societeStatistiqueDTO.setDateStatistique(new Date());
        return societeStatistiqueMapper.toDto(this.societeStatistiqueRepository.save(
                this.societeStatistiqueMapper.toEntity(societeStatistiqueDTO)
        ));
    }

    @Override
    public List<SocieteStatistiqueDTO> findSocieteStatistiquesByIdSociete(Long idSociete) {
        return this.societeStatistiqueRepository
                .findBySocieteId(idSociete)
                .stream()
                .map(societeStatistiqueMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<StatisticSetDTO> findAllGazoilStatisticSet() {
        List<StatisticSetDTO> statisticSetDTOS = new ArrayList<>();
        List<SocieteStatistique> societeStatistiqueList = this.societeStatistiqueRepository.findAll();
        if(!CollectionUtils.isEmpty(societeStatistiqueList)) {
            // Sort data by date
            Comparator<SocieteStatistique> reverseComparator = (c1, c2) -> c2.getDateStatistique().compareTo(c1.getDateStatistique());
            societeStatistiqueList.sort(reverseComparator);
            // Préparation des données
            // 1- Groupe les données par date
            Map<Date,List<SocieteStatistique>> statistiqueMap = societeStatistiqueList
                    .stream()
                    .collect(
                            groupingBy(
                                    SocieteStatistique::getDateStatistique
                            ));
            // 2- Groupe les données par société
            TreeMap<Date,Map<String, List<SocieteStatistique>>> statistiqueByProfilAndDate = new TreeMap<>();
            for(Map.Entry<Date,List<SocieteStatistique>> entry : statistiqueMap.entrySet()) {
                Map<String, List<SocieteStatistique>> dataBySociete = entry.getValue().stream().collect(
                        groupingBy(
                                societeStatistique -> societeStatistique.getSociete().getNom()
                        )
                );
                statistiqueByProfilAndDate.put(entry.getKey(),dataBySociete);
            }
            // 3- Get Data
            TreeMap<String,List<StatisticDataDTO>> dataBySociete = new TreeMap<>();
            for(Map.Entry<Date,Map<String, List<SocieteStatistique>>> entry : statistiqueByProfilAndDate.entrySet()) {
                for(Map.Entry<String,List<SocieteStatistique>> entry1 : entry.getValue().entrySet()) {
                    List<StatisticDataDTO> data = !CollectionUtils.isEmpty(dataBySociete.get(entry1.getKey())) ? dataBySociete.get(entry1.getKey()): new ArrayList<>();
                    SocieteStatistique societeStatistiqueTemp = getSocieteStatistiqueFromListByPredicate(
                            entry1.getValue(),
                            societeStatistique -> societeStatistique.getPrixGazoil()!=null
                    );
                    if(societeStatistiqueTemp!=null) {
                        data.add(new StatisticDataDTO(
                                societeStatistiqueTemp.getDateStatistique().toString(),
                                BigInteger.valueOf(societeStatistiqueTemp.getPrixGazoil().longValue())
                        ));
                    }
                    dataBySociete.put(entry1.getKey(),data);
                }
            }
            // 4- Conversion of data
            for(Map.Entry<String,List<StatisticDataDTO>> entry : dataBySociete.entrySet()) {
                StatisticSetDTO statisticSetDTO = new StatisticSetDTO();
                statisticSetDTO.setName(entry.getKey());
                statisticSetDTO.setData(entry.getValue());
                statisticSetDTOS.add(statisticSetDTO);
            }
        }
        return statisticSetDTOS;
    }

    public List<StatisticSetDTO> findAllEssenceStatisticSet() {
        List<StatisticSetDTO> statisticSetDTOS = new ArrayList<>();
        List<SocieteStatistique> societeStatistiqueList = this.societeStatistiqueRepository.findAll();
        if(!CollectionUtils.isEmpty(societeStatistiqueList)) {
            // Sort data by date
            Comparator<SocieteStatistique> reverseComparator = (c1, c2) -> c2.getDateStatistique().compareTo(c1.getDateStatistique());
            societeStatistiqueList.sort(reverseComparator);
            // Préparation des données
            // 1- Groupe les données par date
            Map<Date,List<SocieteStatistique>> statistiqueMap = societeStatistiqueList
                    .stream()
                    .collect(
                            groupingBy(
                                    SocieteStatistique::getDateStatistique
                            ));
            // 2- Groupe les données par société
            TreeMap<Date,Map<String, List<SocieteStatistique>>> statistiqueByProfilAndDate = new TreeMap<>();
            for(Map.Entry<Date,List<SocieteStatistique>> entry : statistiqueMap.entrySet()) {
                Map<String, List<SocieteStatistique>> dataBySociete = entry.getValue().stream().collect(
                        groupingBy(
                                societeStatistique -> societeStatistique.getSociete().getNom()
                        )
                );
                statistiqueByProfilAndDate.put(entry.getKey(),dataBySociete);
            }
            // 3- Get Data
            TreeMap<String,List<StatisticDataDTO>> dataBySociete = new TreeMap<>();
            for(Map.Entry<Date,Map<String, List<SocieteStatistique>>> entry : statistiqueByProfilAndDate.entrySet()) {
                for(Map.Entry<String,List<SocieteStatistique>> entry1 : entry.getValue().entrySet()) {
                    List<StatisticDataDTO> data = !CollectionUtils.isEmpty(dataBySociete.get(entry1.getKey())) ? dataBySociete.get(entry1.getKey()): new ArrayList<>();
                    SocieteStatistique societeStatistiqueTemp = getSocieteStatistiqueFromListByPredicate(
                            entry1.getValue(),
                            societeStatistique -> societeStatistique.getPrixEssence()!=null
                    );
                    if(societeStatistiqueTemp!=null) {
                        data.add(new StatisticDataDTO(
                                societeStatistiqueTemp.getDateStatistique().toString(),
                                BigInteger.valueOf(societeStatistiqueTemp.getPrixEssence().longValue())
                        ));
                    }
                    dataBySociete.put(entry1.getKey(),data);
                }
            }
            // 4- Conversion of data
            for(Map.Entry<String,List<StatisticDataDTO>> entry : dataBySociete.entrySet()) {
                StatisticSetDTO statisticSetDTO = new StatisticSetDTO();
                statisticSetDTO.setName(entry.getKey());
                statisticSetDTO.setData(entry.getValue());
                statisticSetDTOS.add(statisticSetDTO);
            }
        }
        return statisticSetDTOS;
    }

    private static SocieteStatistique getSocieteStatistiqueFromListByPredicate(List<SocieteStatistique> list, Predicate<SocieteStatistique> predicate) {
        List<SocieteStatistique> societeStatistiqueList = list
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return !CollectionUtils.isEmpty(societeStatistiqueList) ? societeStatistiqueList.get(0) : null;
    }
}
