package com.starke.service.impl;

import com.starke.service.StarkeEntityService;
import com.starke.domain.StarkeEntity;
import com.starke.repository.StarkeEntityRepository;
import com.starke.service.dto.StarkeEntityDTO;
import com.starke.service.mapper.StarkeEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing StarkeEntity.
 */
@Service
@Transactional
public class StarkeEntityServiceImpl implements StarkeEntityService {

    private final Logger log = LoggerFactory.getLogger(StarkeEntityServiceImpl.class);

    private final StarkeEntityRepository starkeEntityRepository;

    private final StarkeEntityMapper starkeEntityMapper;

    public StarkeEntityServiceImpl(StarkeEntityRepository starkeEntityRepository, StarkeEntityMapper starkeEntityMapper) {
        this.starkeEntityRepository = starkeEntityRepository;
        this.starkeEntityMapper = starkeEntityMapper;
    }

    /**
     * Save a starkeEntity.
     *
     * @param starkeEntityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StarkeEntityDTO save(StarkeEntityDTO starkeEntityDTO) {
        log.debug("Request to save StarkeEntity : {}", starkeEntityDTO);
        StarkeEntity starkeEntity = starkeEntityMapper.toEntity(starkeEntityDTO);
        starkeEntity = starkeEntityRepository.save(starkeEntity);
        return starkeEntityMapper.toDto(starkeEntity);
    }

    /**
     * Get all the starkeEntities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StarkeEntityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StarkeEntities");
        return starkeEntityRepository.findAll(pageable)
            .map(starkeEntityMapper::toDto);
    }


    /**
     * Get one starkeEntity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StarkeEntityDTO> findOne(Long id) {
        log.debug("Request to get StarkeEntity : {}", id);
        return starkeEntityRepository.findById(id)
            .map(starkeEntityMapper::toDto);
    }

    /**
     * Delete the starkeEntity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StarkeEntity : {}", id);
        starkeEntityRepository.deleteById(id);
    }
}
