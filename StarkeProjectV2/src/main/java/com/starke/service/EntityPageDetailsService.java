package com.starke.service;

import com.starke.service.dto.EntityPageDetailsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EntityPageDetails.
 */
public interface EntityPageDetailsService {

    /**
     * Save a entityPageDetails.
     *
     * @param entityPageDetailsDTO the entity to save
     * @return the persisted entity
     */
    EntityPageDetailsDTO save(EntityPageDetailsDTO entityPageDetailsDTO);

    /**
     * Get all the entityPageDetails.
     *
     * @return the list of entities
     */
    List<EntityPageDetailsDTO> findAll();
    /**
     * Get all the EntityPageDetailsDTO where StarkeEntity is null.
     *
     * @return the list of entities
     */
    List<EntityPageDetailsDTO> findAllWhereStarkeEntityIsNull();


    /**
     * Get the "id" entityPageDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EntityPageDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" entityPageDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
