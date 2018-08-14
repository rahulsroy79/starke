package com.starke.service.mapper;

import com.starke.domain.*;
import com.starke.service.dto.EntityReviewsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EntityReviews and its DTO EntityReviewsDTO.
 */
@Mapper(componentModel = "spring", uses = {StarkeEntityMapper.class})
public interface EntityReviewsMapper extends EntityMapper<EntityReviewsDTO, EntityReviews> {

    @Mapping(source = "starkeEntity.id", target = "starkeEntityId")
    EntityReviewsDTO toDto(EntityReviews entityReviews);

    @Mapping(source = "starkeEntityId", target = "starkeEntity")
    EntityReviews toEntity(EntityReviewsDTO entityReviewsDTO);

    default EntityReviews fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityReviews entityReviews = new EntityReviews();
        entityReviews.setId(id);
        return entityReviews;
    }
}
