package com.starke.service.impl;

import com.starke.service.EntityReviewsService;
import com.starke.domain.EntityReviews;
import com.starke.repository.EntityReviewsRepository;
import com.starke.service.dto.EntityReviewsDTO;
import com.starke.service.mapper.EntityReviewsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing EntityReviews.
 */
@Service
@Transactional
public class EntityReviewsServiceImpl implements EntityReviewsService {

    private final Logger log = LoggerFactory.getLogger(EntityReviewsServiceImpl.class);

    private final EntityReviewsRepository entityReviewsRepository;

    private final EntityReviewsMapper entityReviewsMapper;

    public EntityReviewsServiceImpl(EntityReviewsRepository entityReviewsRepository, EntityReviewsMapper entityReviewsMapper) {
        this.entityReviewsRepository = entityReviewsRepository;
        this.entityReviewsMapper = entityReviewsMapper;
    }

    /**
     * Save a entityReviews.
     *
     * @param entityReviewsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EntityReviewsDTO save(EntityReviewsDTO entityReviewsDTO) {
        log.debug("Request to save EntityReviews : {}", entityReviewsDTO);
        EntityReviews entityReviews = entityReviewsMapper.toEntity(entityReviewsDTO);
        entityReviews = entityReviewsRepository.save(entityReviews);
        return entityReviewsMapper.toDto(entityReviews);
    }

    /**
     * Get all the entityReviews.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityReviewsDTO> findAll() {
        log.debug("Request to get all EntityReviews");
        return entityReviewsRepository.findAll().stream()
            .map(entityReviewsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one entityReviews by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntityReviewsDTO> findOne(Long id) {
        log.debug("Request to get EntityReviews : {}", id);
        return entityReviewsRepository.findById(id)
            .map(entityReviewsMapper::toDto);
    }

    /**
     * Delete the entityReviews by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityReviews : {}", id);
        entityReviewsRepository.deleteById(id);
    }
}
