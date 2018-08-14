package com.starke.service.impl;

import com.starke.service.EntityPageFormatsService;
import com.starke.domain.EntityPageFormats;
import com.starke.repository.EntityPageFormatsRepository;
import com.starke.service.dto.EntityPageFormatsDTO;
import com.starke.service.mapper.EntityPageFormatsMapper;
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
 * Service Implementation for managing EntityPageFormats.
 */
@Service
@Transactional
public class EntityPageFormatsServiceImpl implements EntityPageFormatsService {

    private final Logger log = LoggerFactory.getLogger(EntityPageFormatsServiceImpl.class);

    private final EntityPageFormatsRepository entityPageFormatsRepository;

    private final EntityPageFormatsMapper entityPageFormatsMapper;

    public EntityPageFormatsServiceImpl(EntityPageFormatsRepository entityPageFormatsRepository, EntityPageFormatsMapper entityPageFormatsMapper) {
        this.entityPageFormatsRepository = entityPageFormatsRepository;
        this.entityPageFormatsMapper = entityPageFormatsMapper;
    }

    /**
     * Save a entityPageFormats.
     *
     * @param entityPageFormatsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EntityPageFormatsDTO save(EntityPageFormatsDTO entityPageFormatsDTO) {
        log.debug("Request to save EntityPageFormats : {}", entityPageFormatsDTO);
        EntityPageFormats entityPageFormats = entityPageFormatsMapper.toEntity(entityPageFormatsDTO);
        entityPageFormats = entityPageFormatsRepository.save(entityPageFormats);
        return entityPageFormatsMapper.toDto(entityPageFormats);
    }

    /**
     * Get all the entityPageFormats.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityPageFormatsDTO> findAll() {
        log.debug("Request to get all EntityPageFormats");
        return entityPageFormatsRepository.findAll().stream()
            .map(entityPageFormatsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the entityPageFormats where EntityPageDetails is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EntityPageFormatsDTO> findAllWhereEntityPageDetailsIsNull() {
        log.debug("Request to get all entityPageFormats where EntityPageDetails is null");
        return StreamSupport
            .stream(entityPageFormatsRepository.findAll().spliterator(), false)
            .filter(entityPageFormats -> entityPageFormats.getEntityPageDetails() == null)
            .map(entityPageFormatsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entityPageFormats by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntityPageFormatsDTO> findOne(Long id) {
        log.debug("Request to get EntityPageFormats : {}", id);
        return entityPageFormatsRepository.findById(id)
            .map(entityPageFormatsMapper::toDto);
    }

    /**
     * Delete the entityPageFormats by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityPageFormats : {}", id);
        entityPageFormatsRepository.deleteById(id);
    }
}
