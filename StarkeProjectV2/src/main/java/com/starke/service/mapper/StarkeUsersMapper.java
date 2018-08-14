package com.starke.service.mapper;

import com.starke.domain.*;
import com.starke.service.dto.StarkeUsersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StarkeUsers and its DTO StarkeUsersDTO.
 */
@Mapper(componentModel = "spring", uses = {StarkeUserTypesMapper.class})
public interface StarkeUsersMapper extends EntityMapper<StarkeUsersDTO, StarkeUsers> {

    @Mapping(source = "starkeUserTypes.id", target = "starkeUserTypesId")
    StarkeUsersDTO toDto(StarkeUsers starkeUsers);

    @Mapping(source = "starkeUserTypesId", target = "starkeUserTypes")
    StarkeUsers toEntity(StarkeUsersDTO starkeUsersDTO);

    default StarkeUsers fromId(Long id) {
        if (id == null) {
            return null;
        }
        StarkeUsers starkeUsers = new StarkeUsers();
        starkeUsers.setId(id);
        return starkeUsers;
    }
}
