package com.starke.service;

import com.starke.service.dto.EntityDetailsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EntityDetails.
 */
public interface EntityDetailsService {

    /**
     * Save a entityDetails.
     *
     * @param entityDetailsDTO the entity to save
     * @return the persisted entity
     */
    EntityDetailsDTO save(EntityDetailsDTO entityDetailsDTO);

    /**
     * Get all the entityDetails.
     *
     * @return the list of entities
     */
    List<EntityDetailsDTO> findAll();
    /**
     * Get all the EntityDetailsDTO where StarkeEntity is null.
     *
     * @return the list of entities
     */
    List<EntityDetailsDTO> findAllWhereStarkeEntityIsNull();


    /**
     * Get the "id" entityDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EntityDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" entityDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
