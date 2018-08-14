package com.starke.service.impl;

import com.starke.service.EntityDetailsService;
import com.starke.domain.EntityDetails;
import com.starke.repository.EntityDetailsRepository;
import com.starke.service.dto.EntityDetailsDTO;
import com.starke.service.mapper.EntityDetailsMapper;
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
 * Service Implementation for managing EntityDetails.
 */
@Service
@Transactional
public class EntityDetailsServiceImpl implements EntityDetailsService {

    private final Logger log = LoggerFactory.getLogger(EntityDetailsServiceImpl.class);

    private final EntityDetailsRepository entityDetailsRepository;

    private final EntityDetailsMapper entityDetailsMapper;

    public EntityDetailsServiceImpl(EntityDetailsRepository entityDetailsRepository, EntityDetailsMapper entityDetailsMapper) {
        this.entityDetailsRepository = entityDetailsRepository;
        this.entityDetailsMapper = entityDetailsMapper;
    }

    /**
     * Save a entityDetails.
     *
     * @param entityDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EntityDetailsDTO save(EntityDetailsDTO entityDetailsDTO) {
        log.debug("Request to save EntityDetails : {}", entityDetailsDTO);
        EntityDetails entityDetails = entityDetailsMapper.toEntity(entityDetailsDTO);
        entityDetails = entityDetailsRepository.save(entityDetails);
        return entityDetailsMapper.toDto(entityDetails);
    }

    /**
     * Get all the entityDetails.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityDetailsDTO> findAll() {
        log.debug("Request to get all EntityDetails");
        return entityDetailsRepository.findAll().stream()
            .map(entityDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the entityDetails where StarkeEntity is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EntityDetailsDTO> findAllWhereStarkeEntityIsNull() {
        log.debug("Request to get all entityDetails where StarkeEntity is null");
        return StreamSupport
            .stream(entityDetailsRepository.findAll().spliterator(), false)
            .filter(entityDetails -> entityDetails.getStarkeEntity() == null)
            .map(entityDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entityDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntityDetailsDTO> findOne(Long id) {
        log.debug("Request to get EntityDetails : {}", id);
        return entityDetailsRepository.findById(id)
            .map(entityDetailsMapper::toDto);
    }

    /**
     * Delete the entityDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityDetails : {}", id);
        entityDetailsRepository.deleteById(id);
    }
}
