package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.models.DossierSocial;
import com.socialnetworkmonitoring.repository.DossierSocialRepository;
import com.socialnetworkmonitoring.service.DossierSocialService;
import com.socialnetworkmonitoring.service.dto.DossierSocialDTO;
import com.socialnetworkmonitoring.service.mapper.DossierSocialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public DossierSocialDTO create(DossierSocialDTO dossierSocialDTO) {
        DossierSocial dossierSocial = this.dossierSocialMapper.toEntity(dossierSocialDTO);
        return this.dossierSocialMapper.toDto(this.dossierSocialRepository.save(dossierSocial));
    }

    @Override
    public DossierSocialDTO update(DossierSocialDTO dossierSocialDTO) {
        return null;
    }

    @Override
    public void delete(Long idDossierSocial) {

    }
}
