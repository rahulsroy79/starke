package com.starke.service.mapper;

import com.starke.domain.*;
import com.starke.service.dto.EntityPageDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EntityPageDetails and its DTO EntityPageDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {EntityPageFormatsMapper.class})
public interface EntityPageDetailsMapper extends EntityMapper<EntityPageDetailsDTO, EntityPageDetails> {

    @Mapping(source = "entityPageFormats.id", target = "entityPageFormatsId")
    @Mapping(source = "entityPageFormats.pageformatid", target = "entityPageFormatsPageformatid")
    EntityPageDetailsDTO toDto(EntityPageDetails entityPageDetails);

    @Mapping(source = "entityPageFormatsId", target = "entityPageFormats")
    @Mapping(target = "starkeEntity", ignore = true)
    EntityPageDetails toEntity(EntityPageDetailsDTO entityPageDetailsDTO);

    default EntityPageDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityPageDetails entityPageDetails = new EntityPageDetails();
        entityPageDetails.setId(id);
        return entityPageDetails;
    }
}
