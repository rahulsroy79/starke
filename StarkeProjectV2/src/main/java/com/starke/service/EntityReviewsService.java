package com.starke.service;

import com.starke.service.dto.EntityReviewsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EntityReviews.
 */
public interface EntityReviewsService {

    /**
     * Save a entityReviews.
     *
     * @param entityReviewsDTO the entity to save
     * @return the persisted entity
     */
    EntityReviewsDTO save(EntityReviewsDTO entityReviewsDTO);

    /**
     * Get all the entityReviews.
     *
     * @return the list of entities
     */
    List<EntityReviewsDTO> findAll();


    /**
     * Get the "id" entityReviews.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EntityReviewsDTO> findOne(Long id);

    /**
     * Delete the "id" entityReviews.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
