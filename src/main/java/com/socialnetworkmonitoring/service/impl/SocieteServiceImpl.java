package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.models.Societe;
import com.socialnetworkmonitoring.repository.SocieteRepository;
import com.socialnetworkmonitoring.service.SocieteService;
import com.socialnetworkmonitoring.service.dto.SocieteDTO;
import com.socialnetworkmonitoring.service.mapper.SocieteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocieteServiceImpl implements SocieteService {

    private final SocieteRepository societeRepository;

    private final SocieteMapper societeMapper;

    @Autowired
    public SocieteServiceImpl(final SocieteRepository societeRepository, final SocieteMapper societeMapper) {
        this.societeRepository = societeRepository;
        this.societeMapper = societeMapper;
    }

    @Override
    public SocieteDTO create(SocieteDTO societeDTO) throws EntityAlreadyExistException {
        if (this.societeRepository.existsByNomIgnoreCase(societeDTO.getNom())) {
            throw new EntityAlreadyExistException("Société existante", "Une société existe dèjà avec ce nom : "+societeDTO.getNom());
        }
        Societe societe = this.societeMapper.toEntity(societeDTO);
        return this.societeMapper.toDto(this.societeRepository.save(societe));
    }

    @Override
    public void delete(Long idSociete) {
        this.societeRepository.deleteById(idSociete);
    }

    @Override
    public Optional<SocieteDTO> findOne(Long id) {
        return this.societeRepository.findById(id).map(societeMapper::toDto);
    }

    @Override
    public List<SocieteDTO> findAll() {
        return this.societeRepository.findAll().parallelStream().map(
                this.societeMapper::toDto
        ).collect(Collectors.toList());
    }

}
