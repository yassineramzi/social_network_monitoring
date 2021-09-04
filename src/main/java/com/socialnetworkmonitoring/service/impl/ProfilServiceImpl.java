package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.models.Profil;
import com.socialnetworkmonitoring.repository.ProfilRepository;
import com.socialnetworkmonitoring.service.ProfilService;
import com.socialnetworkmonitoring.service.dto.ProfilDTO;
import com.socialnetworkmonitoring.service.mapper.ProfilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfilServiceImpl implements ProfilService {

    private ProfilRepository profilRepository;

    private ProfilMapper profilMapper;

    @Autowired
    public ProfilServiceImpl(ProfilRepository profilRepository, ProfilMapper profilMapper) {
        this.profilRepository = profilRepository;
        this.profilMapper = profilMapper;
    }

    @Override
    public ProfilDTO create(ProfilDTO profilDTO) throws EntityAlreadyExistException {
        if (this.profilRepository.existsByNom(profilDTO.getNom())) {
            throw new EntityAlreadyExistException("Profil existant", "Un profil existe dèjà avec ce nom : "+profilDTO.getNom());
        }
        Profil profil = this.profilMapper.toEntity(profilDTO);
        return this.profilMapper.toDto(this.profilRepository.save(profil));
    }

    @Override
    public ProfilDTO update(ProfilDTO profilDTO) throws EntityAlreadyExistException {
        if (this.profilRepository.existsByNomAndIdIsNot(profilDTO.getNom(), profilDTO.getId())) {
            throw new EntityAlreadyExistException("Profil existant", "Un profil existe dèjà avec ce nom : "+profilDTO.getNom());
        }
        Profil profil = this.profilMapper.toEntity(profilDTO);
        return this.profilMapper.toDto(this.profilRepository.save(profil));
    }

    @Override
    public void delete(Long idProfil) {
        this.profilRepository.deleteById(idProfil);
    }

    @Override
    public List<ProfilDTO> findProfilsByIdDossier(Long idDossier) {
        return this.profilRepository.findByDossierSocialId(idDossier).parallelStream().map(
                profil -> this.profilMapper.toDto(profil)
        ).collect(Collectors.toList());
    }

}
