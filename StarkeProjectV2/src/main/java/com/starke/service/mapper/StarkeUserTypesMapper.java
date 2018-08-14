package com.starke.service.mapper;

import com.starke.domain.*;
import com.starke.service.dto.StarkeUserTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StarkeUserTypes and its DTO StarkeUserTypesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StarkeUserTypesMapper extends EntityMapper<StarkeUserTypesDTO, StarkeUserTypes> {


    @Mapping(target = "starkeUser", ignore = true)
    StarkeUserTypes toEntity(StarkeUserTypesDTO starkeUserTypesDTO);

    default StarkeUserTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        StarkeUserTypes starkeUserTypes = new StarkeUserTypes();
        starkeUserTypes.setId(id);
        return starkeUserTypes;
    }
}
