package com.starke.service.impl;

import com.starke.service.StarkeUsersService;
import com.starke.domain.StarkeUsers;
import com.starke.repository.StarkeUsersRepository;
import com.starke.service.dto.StarkeUsersDTO;
import com.starke.service.mapper.StarkeUsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing StarkeUsers.
 */
@Service
@Transactional
public class StarkeUsersServiceImpl implements StarkeUsersService {

    private final Logger log = LoggerFactory.getLogger(StarkeUsersServiceImpl.class);

    private final StarkeUsersRepository starkeUsersRepository;

    private final StarkeUsersMapper starkeUsersMapper;

    public StarkeUsersServiceImpl(StarkeUsersRepository starkeUsersRepository, StarkeUsersMapper starkeUsersMapper) {
        this.starkeUsersRepository = starkeUsersRepository;
        this.starkeUsersMapper = starkeUsersMapper;
    }

    /**
     * Save a starkeUsers.
     *
     * @param starkeUsersDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StarkeUsersDTO save(StarkeUsersDTO starkeUsersDTO) {
        log.debug("Request to save StarkeUsers : {}", starkeUsersDTO);
        StarkeUsers starkeUsers = starkeUsersMapper.toEntity(starkeUsersDTO);
        starkeUsers = starkeUsersRepository.save(starkeUsers);
        return starkeUsersMapper.toDto(starkeUsers);
    }

    /**
     * Get all the starkeUsers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StarkeUsersDTO> findAll() {
        log.debug("Request to get all StarkeUsers");
        return starkeUsersRepository.findAll().stream()
            .map(starkeUsersMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one starkeUsers by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StarkeUsersDTO> findOne(Long id) {
        log.debug("Request to get StarkeUsers : {}", id);
        return starkeUsersRepository.findById(id)
            .map(starkeUsersMapper::toDto);
    }

    /**
     * Delete the starkeUsers by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StarkeUsers : {}", id);
        starkeUsersRepository.deleteById(id);
    }
}
