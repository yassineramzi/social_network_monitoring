package com.socialnetworkmonitoring.service.mapper;

import com.socialnetworkmonitoring.models.ProfilStatistique;
import com.socialnetworkmonitoring.service.dto.ProfilStatistiqueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfilStatistiqueMapper extends EntityMapper<ProfilStatistiqueDTO, ProfilStatistique>{
    @Override
    @Mapping(source = "profil.id", target = "profilId")
    ProfilStatistiqueDTO toDto(ProfilStatistique profilStatistique);

    @Override
    @Mapping(source = "profilId", target = "profil.id")
    ProfilStatistique toEntity(ProfilStatistiqueDTO profilStatistiqueDTO);

    default ProfilStatistique fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProfilStatistique profilStatistique = new ProfilStatistique();
        profilStatistique.setId(id);
        return profilStatistique;
    }
}
