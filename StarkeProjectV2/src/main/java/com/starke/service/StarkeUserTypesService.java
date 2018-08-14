package com.starke.service;

import com.starke.service.dto.StarkeUserTypesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StarkeUserTypes.
 */
public interface StarkeUserTypesService {

    /**
     * Save a starkeUserTypes.
     *
     * @param starkeUserTypesDTO the entity to save
     * @return the persisted entity
     */
    StarkeUserTypesDTO save(StarkeUserTypesDTO starkeUserTypesDTO);

    /**
     * Get all the starkeUserTypes.
     *
     * @return the list of entities
     */
    List<StarkeUserTypesDTO> findAll();
    /**
     * Get all the StarkeUserTypesDTO where StarkeUser is null.
     *
     * @return the list of entities
     */
    List<StarkeUserTypesDTO> findAllWhereStarkeUserIsNull();


    /**
     * Get the "id" starkeUserTypes.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StarkeUserTypesDTO> findOne(Long id);

    /**
     * Delete the "id" starkeUserTypes.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
