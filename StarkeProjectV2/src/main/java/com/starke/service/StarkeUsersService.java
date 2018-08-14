package com.starke.service;

import com.starke.service.dto.StarkeUsersDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StarkeUsers.
 */
public interface StarkeUsersService {

    /**
     * Save a starkeUsers.
     *
     * @param starkeUsersDTO the entity to save
     * @return the persisted entity
     */
    StarkeUsersDTO save(StarkeUsersDTO starkeUsersDTO);

    /**
     * Get all the starkeUsers.
     *
     * @return the list of entities
     */
    List<StarkeUsersDTO> findAll();


    /**
     * Get the "id" starkeUsers.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StarkeUsersDTO> findOne(Long id);

    /**
     * Delete the "id" starkeUsers.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
