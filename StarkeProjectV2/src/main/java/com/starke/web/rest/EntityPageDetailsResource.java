package com.starke.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.starke.service.EntityPageDetailsService;
import com.starke.web.rest.errors.BadRequestAlertException;
import com.starke.web.rest.util.HeaderUtil;
import com.starke.service.dto.EntityPageDetailsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing EntityPageDetails.
 */
@RestController
@RequestMapping("/api")
public class EntityPageDetailsResource {

    private final Logger log = LoggerFactory.getLogger(EntityPageDetailsResource.class);

    private static final String ENTITY_NAME = "entityPageDetails";

    private final EntityPageDetailsService entityPageDetailsService;

    public EntityPageDetailsResource(EntityPageDetailsService entityPageDetailsService) {
        this.entityPageDetailsService = entityPageDetailsService;
    }

    /**
     * POST  /entity-page-details : Create a new entityPageDetails.
     *
     * @param entityPageDetailsDTO the entityPageDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityPageDetailsDTO, or with status 400 (Bad Request) if the entityPageDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-page-details")
    @Timed
    public ResponseEntity<EntityPageDetailsDTO> createEntityPageDetails(@Valid @RequestBody EntityPageDetailsDTO entityPageDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save EntityPageDetails : {}", entityPageDetailsDTO);
        if (entityPageDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityPageDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityPageDetailsDTO result = entityPageDetailsService.save(entityPageDetailsDTO);
        return ResponseEntity.created(new URI("/api/entity-page-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-page-details : Updates an existing entityPageDetails.
     *
     * @param entityPageDetailsDTO the entityPageDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityPageDetailsDTO,
     * or with status 400 (Bad Request) if the entityPageDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the entityPageDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-page-details")
    @Timed
    public ResponseEntity<EntityPageDetailsDTO> updateEntityPageDetails(@Valid @RequestBody EntityPageDetailsDTO entityPageDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update EntityPageDetails : {}", entityPageDetailsDTO);
        if (entityPageDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityPageDetailsDTO result = entityPageDetailsService.save(entityPageDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityPageDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-page-details : get all the entityPageDetails.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of entityPageDetails in body
     */
    @GetMapping("/entity-page-details")
    @Timed
    public List<EntityPageDetailsDTO> getAllEntityPageDetails(@RequestParam(required = false) String filter) {
        if ("starkeentity-is-null".equals(filter)) {
            log.debug("REST request to get all EntityPageDetailss where starkeEntity is null");
            return entityPageDetailsService.findAllWhereStarkeEntityIsNull();
        }
        log.debug("REST request to get all EntityPageDetails");
        return entityPageDetailsService.findAll();
    }

    /**
     * GET  /entity-page-details/:id : get the "id" entityPageDetails.
     *
     * @param id the id of the entityPageDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityPageDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/entity-page-details/{id}")
    @Timed
    public ResponseEntity<EntityPageDetailsDTO> getEntityPageDetails(@PathVariable Long id) {
        log.debug("REST request to get EntityPageDetails : {}", id);
        Optional<EntityPageDetailsDTO> entityPageDetailsDTO = entityPageDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityPageDetailsDTO);
    }

    /**
     * DELETE  /entity-page-details/:id : delete the "id" entityPageDetails.
     *
     * @param id the id of the entityPageDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-page-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityPageDetails(@PathVariable Long id) {
        log.debug("REST request to delete EntityPageDetails : {}", id);
        entityPageDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
