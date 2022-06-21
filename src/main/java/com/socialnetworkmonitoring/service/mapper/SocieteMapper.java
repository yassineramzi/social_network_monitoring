package com.socialnetworkmonitoring.service.mapper;

import com.socialnetworkmonitoring.models.Societe;
import com.socialnetworkmonitoring.service.dto.SocieteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocieteMapper extends EntityMapper<SocieteDTO, Societe> {
    @Override
    SocieteDTO toDto(Societe societe);

    @Override
    Societe toEntity(SocieteDTO societeDTO);

    default Societe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Societe societe = new Societe();
        societe.setId(id);
        return societe;
    }
}
