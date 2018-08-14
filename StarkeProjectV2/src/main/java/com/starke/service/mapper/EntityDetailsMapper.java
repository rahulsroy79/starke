package com.starke.service.mapper;

import com.starke.domain.*;
import com.starke.service.dto.EntityDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EntityDetails and its DTO EntityDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntityDetailsMapper extends EntityMapper<EntityDetailsDTO, EntityDetails> {


    @Mapping(target = "starkeEntity", ignore = true)
    EntityDetails toEntity(EntityDetailsDTO entityDetailsDTO);

    default EntityDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityDetails entityDetails = new EntityDetails();
        entityDetails.setId(id);
        return entityDetails;
    }
}
