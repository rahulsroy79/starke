package com.starke.service.mapper;

import com.starke.domain.*;
import com.starke.service.dto.StarkeEntityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StarkeEntity and its DTO StarkeEntityDTO.
 */
@Mapper(componentModel = "spring", uses = {EntityPageDetailsMapper.class, EntityDetailsMapper.class})
public interface StarkeEntityMapper extends EntityMapper<StarkeEntityDTO, StarkeEntity> {

    @Mapping(source = "entityPageDetails.id", target = "entityPageDetailsId")
    @Mapping(source = "entityPageDetails.pagedetailid", target = "entityPageDetailsPagedetailid")
    @Mapping(source = "entityDetails.id", target = "entityDetailsId")
    @Mapping(source = "entityDetails.entityid", target = "entityDetailsEntityid")
    @Mapping(source = "parent.id", target = "parentId")
    StarkeEntityDTO toDto(StarkeEntity starkeEntity);

    @Mapping(source = "entityPageDetailsId", target = "entityPageDetails")
    @Mapping(source = "entityDetailsId", target = "entityDetails")
    @Mapping(target = "entityReviews", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    StarkeEntity toEntity(StarkeEntityDTO starkeEntityDTO);

    default StarkeEntity fromId(Long id) {
        if (id == null) {
            return null;
        }
        StarkeEntity starkeEntity = new StarkeEntity();
        starkeEntity.setId(id);
        return starkeEntity;
    }
}
