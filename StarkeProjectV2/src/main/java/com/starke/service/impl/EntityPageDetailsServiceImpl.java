package com.starke.service.impl;

import com.starke.service.EntityPageDetailsService;
import com.starke.domain.EntityPageDetails;
import com.starke.repository.EntityPageDetailsRepository;
import com.starke.service.dto.EntityPageDetailsDTO;
import com.starke.service.mapper.EntityPageDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
/**
 * Service Implementation for managing EntityPageDetails.
 */
@Service
@Transactional
public class EntityPageDetailsServiceImpl implements EntityPageDetailsService {

    private final Logger log = LoggerFactory.getLogger(EntityPageDetailsServiceImpl.class);

    private final EntityPageDetailsRepository entityPageDetailsRepository;

    private final EntityPageDetailsMapper entityPageDetailsMapper;

    public EntityPageDetailsServiceImpl(EntityPageDetailsRepository entityPageDetailsRepository, EntityPageDetailsMapper entityPageDetailsMapper) {
        this.entityPageDetailsRepository = entityPageDetailsRepository;
        this.entityPageDetailsMapper = entityPageDetailsMapper;
    }

    /**
     * Save a entityPageDetails.
     *
     * @param entityPageDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EntityPageDetailsDTO save(EntityPageDetailsDTO entityPageDetailsDTO) {
        log.debug("Request to save EntityPageDetails : {}", entityPageDetailsDTO);
        EntityPageDetails entityPageDetails = entityPageDetailsMapper.toEntity(entityPageDetailsDTO);
        entityPageDetails = entityPageDetailsRepository.save(entityPageDetails);
        return entityPageDetailsMapper.toDto(entityPageDetails);
    }

    /**
     * Get all the entityPageDetails.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityPageDetailsDTO> findAll() {
        log.debug("Request to get all EntityPageDetails");
        return entityPageDetailsRepository.findAll().stream()
            .map(entityPageDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the entityPageDetails where StarkeEntity is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EntityPageDetailsDTO> findAllWhereStarkeEntityIsNull() {
        log.debug("Request to get all entityPageDetails where StarkeEntity is null");
        return StreamSupport
            .stream(entityPageDetailsRepository.findAll().spliterator(), false)
            .filter(entityPageDetails -> entityPageDetails.getStarkeEntity() == null)
            .map(entityPageDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entityPageDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntityPageDetailsDTO> findOne(Long id) {
        log.debug("Request to get EntityPageDetails : {}", id);
        return entityPageDetailsRepository.findById(id)
            .map(entityPageDetailsMapper::toDto);
    }

    /**
     * Delete the entityPageDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityPageDetails : {}", id);
        entityPageDetailsRepository.deleteById(id);
    }
}
