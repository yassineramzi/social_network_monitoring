package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.repository.SocieteStatistiqueRepository;
import com.socialnetworkmonitoring.service.SocieteStatistiqueService;
import com.socialnetworkmonitoring.service.dto.SocieteStatistiqueDTO;
import com.socialnetworkmonitoring.service.mapper.SocieteStatistiqueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}
