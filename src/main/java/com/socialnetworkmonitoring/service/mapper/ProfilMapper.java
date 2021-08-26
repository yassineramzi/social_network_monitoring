package com.socialnetworkmonitoring.service.mapper;

import com.socialnetworkmonitoring.models.Profil;
import com.socialnetworkmonitoring.service.dto.ProfilDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfilMapper extends EntityMapper<ProfilDTO, Profil> {
    @Override
    ProfilDTO toDto(Profil profil);

    @Override
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
