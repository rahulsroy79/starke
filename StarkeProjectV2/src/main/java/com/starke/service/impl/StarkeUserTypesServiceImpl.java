package com.starke.service.impl;

import com.starke.service.StarkeUserTypesService;
import com.starke.domain.StarkeUserTypes;
import com.starke.repository.StarkeUserTypesRepository;
import com.starke.service.dto.StarkeUserTypesDTO;
import com.starke.service.mapper.StarkeUserTypesMapper;
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
 * Service Implementation for managing StarkeUserTypes.
 */
@Service
@Transactional
public class StarkeUserTypesServiceImpl implements StarkeUserTypesService {

    private final Logger log = LoggerFactory.getLogger(StarkeUserTypesServiceImpl.class);

    private final StarkeUserTypesRepository starkeUserTypesRepository;

    private final StarkeUserTypesMapper starkeUserTypesMapper;

    public StarkeUserTypesServiceImpl(StarkeUserTypesRepository starkeUserTypesRepository, StarkeUserTypesMapper starkeUserTypesMapper) {
        this.starkeUserTypesRepository = starkeUserTypesRepository;
        this.starkeUserTypesMapper = starkeUserTypesMapper;
    }

    /**
     * Save a starkeUserTypes.
     *
     * @param starkeUserTypesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StarkeUserTypesDTO save(StarkeUserTypesDTO starkeUserTypesDTO) {
        log.debug("Request to save StarkeUserTypes : {}", starkeUserTypesDTO);
        StarkeUserTypes starkeUserTypes = starkeUserTypesMapper.toEntity(starkeUserTypesDTO);
        starkeUserTypes = starkeUserTypesRepository.save(starkeUserTypes);
        return starkeUserTypesMapper.toDto(starkeUserTypes);
    }

    /**
     * Get all the starkeUserTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StarkeUserTypesDTO> findAll() {
        log.debug("Request to get all StarkeUserTypes");
        return starkeUserTypesRepository.findAll().stream()
            .map(starkeUserTypesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the starkeUserTypes where StarkeUser is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<StarkeUserTypesDTO> findAllWhereStarkeUserIsNull() {
        log.debug("Request to get all starkeUserTypes where StarkeUser is null");
        return StreamSupport
            .stream(starkeUserTypesRepository.findAll().spliterator(), false)
            .filter(starkeUserTypes -> starkeUserTypes.getStarkeUser() == null)
            .map(starkeUserTypesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one starkeUserTypes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StarkeUserTypesDTO> findOne(Long id) {
        log.debug("Request to get StarkeUserTypes : {}", id);
        return starkeUserTypesRepository.findById(id)
            .map(starkeUserTypesMapper::toDto);
    }

    /**
     * Delete the starkeUserTypes by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StarkeUserTypes : {}", id);
        starkeUserTypesRepository.deleteById(id);
    }
}
