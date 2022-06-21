package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.service.dto.SocieteDTO;

import java.util.List;
import java.util.Optional;

public interface SocieteService {
    SocieteDTO create(SocieteDTO societeDTO) throws EntityAlreadyExistException;

    void delete(Long idSociete);

    Optional<SocieteDTO> findOne(Long id);

    List<SocieteDTO> findAll();
}
