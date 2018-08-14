package com.starke.service;

import com.starke.service.dto.EntityPageFormatsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EntityPageFormats.
 */
public interface EntityPageFormatsService {

    /**
     * Save a entityPageFormats.
     *
     * @param entityPageFormatsDTO the entity to save
     * @return the persisted entity
     */
    EntityPageFormatsDTO save(EntityPageFormatsDTO entityPageFormatsDTO);

    /**
     * Get all the entityPageFormats.
     *
     * @return the list of entities
     */
    List<EntityPageFormatsDTO> findAll();
    /**
     * Get all the EntityPageFormatsDTO where EntityPageDetails is null.
     *
     * @return the list of entities
     */
    List<EntityPageFormatsDTO> findAllWhereEntityPageDetailsIsNull();


    /**
     * Get the "id" entityPageFormats.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EntityPageFormatsDTO> findOne(Long id);

    /**
     * Delete the "id" entityPageFormats.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
