package com.socialnetworkmonitoring.service.mapper;

import com.socialnetworkmonitoring.models.Profil;
import com.socialnetworkmonitoring.service.dto.ProfilDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfilMapper extends EntityMapper<ProfilDTO, Profil> {
    @Override
    @Mapping(source = "dossierSocial.id", target = "dossierId")
    ProfilDTO toDto(Profil profil);

    @Override
    @Mapping(source = "dossierId", target = "dossierSocial.id")
    Profil toEntity(ProfilDTO profilDTO);

    default Profil fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profil profil = new Profil();
        profil.setId(id);
        return profil;
    }
}
