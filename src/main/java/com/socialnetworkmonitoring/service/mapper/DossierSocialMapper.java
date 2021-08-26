package com.socialnetworkmonitoring.service.mapper;

import com.socialnetworkmonitoring.models.DossierSocial;
import com.socialnetworkmonitoring.service.dto.DossierSocialDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProfilMapper.class})
public interface DossierSocialMapper extends EntityMapper<DossierSocialDTO, DossierSocial> {
    @Override
    DossierSocialDTO toDto(DossierSocial dossierSocial);

    @Override
    DossierSocial toEntity(DossierSocialDTO dossierSocialDTO);

    default DossierSocial fromId(Long id) {
        if (id == null) {
            return null;
        }
        DossierSocial dossierSocial = new DossierSocial();
        dossierSocial.setId(id);
        return dossierSocial;
    }
}
