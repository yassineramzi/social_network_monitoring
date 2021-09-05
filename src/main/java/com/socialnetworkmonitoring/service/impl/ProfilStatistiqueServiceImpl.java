package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.repository.ProfilStatistiqueRepository;
import com.socialnetworkmonitoring.service.ProfilStatistiqueService;
import com.socialnetworkmonitoring.service.dto.ProfilStatistiqueDTO;
import com.socialnetworkmonitoring.service.mapper.ProfilStatistiqueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return this.profilStatistiqueRepository.findByProfilId(idProfil).parallelStream().map(
                profilStatistique -> this.profilStatistiqueMapper.toDto(profilStatistique)
        ).collect(Collectors.toList());
    }
}
