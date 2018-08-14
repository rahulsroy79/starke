package com.starke.service;

import com.starke.service.dto.StarkeEntityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing StarkeEntity.
 */
public interface StarkeEntityService {

    /**
     * Save a starkeEntity.
     *
     * @param starkeEntityDTO the entity to save
     * @return the persisted entity
     */
    StarkeEntityDTO save(StarkeEntityDTO starkeEntityDTO);

    /**
     * Get all the starkeEntities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StarkeEntityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" starkeEntity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StarkeEntityDTO> findOne(Long id);

    /**
     * Delete the "id" starkeEntity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
