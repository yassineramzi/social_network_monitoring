package com.socialnetworkmonitoring.service.mapper;

import com.socialnetworkmonitoring.models.SocieteStatistique;
import com.socialnetworkmonitoring.service.dto.SocieteStatistiqueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SocieteMapper.class})
public interface SocieteStatistiqueMapper extends EntityMapper<SocieteStatistiqueDTO, SocieteStatistique>{
    @Override
    @Mapping(source = "societe.id", target = "societeId")
    SocieteStatistiqueDTO toDto(SocieteStatistique societeStatistique);

    @Override
    @Mapping(source = "societeId", target = "societe.id")
    SocieteStatistique toEntity(SocieteStatistiqueDTO societeStatistiqueDTO);

    default SocieteStatistique fromId(Long id) {
        if (id == null) {
            return null;
        }
        SocieteStatistique societeStatistique = new SocieteStatistique();
        societeStatistique.setId(id);
        return societeStatistique;
    }
}
