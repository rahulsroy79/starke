package com.starke.service.mapper;

import com.starke.domain.*;
import com.starke.service.dto.EntityPageFormatsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EntityPageFormats and its DTO EntityPageFormatsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntityPageFormatsMapper extends EntityMapper<EntityPageFormatsDTO, EntityPageFormats> {


    @Mapping(target = "entityPageDetails", ignore = true)
    EntityPageFormats toEntity(EntityPageFormatsDTO entityPageFormatsDTO);

    default EntityPageFormats fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityPageFormats entityPageFormats = new EntityPageFormats();
        entityPageFormats.setId(id);
        return entityPageFormats;
    }
}
