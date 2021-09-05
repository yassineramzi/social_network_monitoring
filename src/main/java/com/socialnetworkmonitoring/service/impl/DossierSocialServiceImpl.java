package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
import com.socialnetworkmonitoring.models.DossierSocial;
import com.socialnetworkmonitoring.repository.DossierSocialRepository;
import com.socialnetworkmonitoring.service.DossierSocialService;
import com.socialnetworkmonitoring.service.dto.DossierSocialDTO;
import com.socialnetworkmonitoring.service.dto.ProfilDTO;
import com.socialnetworkmonitoring.service.mapper.DossierSocialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DossierSocialServiceImpl implements DossierSocialService {

    private DossierSocialRepository dossierSocialRepository;

    private DossierSocialMapper dossierSocialMapper;

    @Autowired
    public DossierSocialServiceImpl(DossierSocialRepository dossierSocialRepository, DossierSocialMapper dossierSocialMapper) {
        this.dossierSocialRepository = dossierSocialRepository;
        this.dossierSocialMapper = dossierSocialMapper;
    }

    @Override
    public DossierSocialDTO create(DossierSocialDTO dossierSocialDTO) throws EntityAlreadyExistException {
        if (this.dossierSocialRepository.existsByNom(dossierSocialDTO.getNom())) {
            throw new EntityAlreadyExistException("Dossier existant", "Un Dossier social existe dèjà avec ce nom : "+dossierSocialDTO.getNom());
        }
        DossierSocial dossierSocial = this.dossierSocialMapper.toEntity(dossierSocialDTO);
        return this.dossierSocialMapper.toDto(this.dossierSocialRepository.save(dossierSocial));
    }

    @Override
    public DossierSocialDTO update(DossierSocialDTO dossierSocialDTO) throws EntityAlreadyExistException {
        if (this.dossierSocialRepository.existsByNomAndIdIsNot(dossierSocialDTO.getNom(), dossierSocialDTO.getId())) {
            throw new EntityAlreadyExistException("Dossier existant", "Un Dossier social existe dèjà avec ce nom : "+dossierSocialDTO.getNom());
        }
        DossierSocial dossierSocial = this.dossierSocialMapper.toEntity(dossierSocialDTO);
        this.dossierSocialRepository.save(dossierSocial);
        DossierSocial dossierSocialTemp = this.dossierSocialRepository.findById(dossierSocial.getId()).get();
        return this.dossierSocialMapper.toDto(dossierSocialTemp);
    }

    @Override
    public void delete(Long idDossierSocial) {
        this.dossierSocialRepository.deleteById(idDossierSocial);
    }

    @Override
    public Optional<DossierSocialDTO> findOne(Long id) {
        return this.dossierSocialRepository.findById(id).map(dossierSocialMapper::toDto);
    }

    @Override
    public List<DossierSocialDTO> findAll() {
        return this.dossierSocialRepository.findAll().parallelStream().map(
                dossierSocial -> this.dossierSocialMapper.toDto(dossierSocial)
        ).collect(Collectors.toList());
    }

}
