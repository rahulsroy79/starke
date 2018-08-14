package com.starke.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.starke.service.EntityDetailsService;
import com.starke.web.rest.errors.BadRequestAlertException;
import com.starke.web.rest.util.HeaderUtil;
import com.starke.service.dto.EntityDetailsDTO;
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
 * REST controller for managing EntityDetails.
 */
@RestController
@RequestMapping("/api")
public class EntityDetailsResource {

    private final Logger log = LoggerFactory.getLogger(EntityDetailsResource.class);

    private static final String ENTITY_NAME = "entityDetails";

    private final EntityDetailsService entityDetailsService;

    public EntityDetailsResource(EntityDetailsService entityDetailsService) {
        this.entityDetailsService = entityDetailsService;
    }

    /**
     * POST  /entity-details : Create a new entityDetails.
     *
     * @param entityDetailsDTO the entityDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityDetailsDTO, or with status 400 (Bad Request) if the entityDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-details")
    @Timed
    public ResponseEntity<EntityDetailsDTO> createEntityDetails(@Valid @RequestBody EntityDetailsDTO entityDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save EntityDetails : {}", entityDetailsDTO);
        if (entityDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityDetailsDTO result = entityDetailsService.save(entityDetailsDTO);
        return ResponseEntity.created(new URI("/api/entity-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-details : Updates an existing entityDetails.
     *
     * @param entityDetailsDTO the entityDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityDetailsDTO,
     * or with status 400 (Bad Request) if the entityDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the entityDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-details")
    @Timed
    public ResponseEntity<EntityDetailsDTO> updateEntityDetails(@Valid @RequestBody EntityDetailsDTO entityDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update EntityDetails : {}", entityDetailsDTO);
        if (entityDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityDetailsDTO result = entityDetailsService.save(entityDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-details : get all the entityDetails.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of entityDetails in body
     */
    @GetMapping("/entity-details")
    @Timed
    public List<EntityDetailsDTO> getAllEntityDetails(@RequestParam(required = false) String filter) {
        if ("starkeentity-is-null".equals(filter)) {
            log.debug("REST request to get all EntityDetailss where starkeEntity is null");
            return entityDetailsService.findAllWhereStarkeEntityIsNull();
        }
        log.debug("REST request to get all EntityDetails");
        return entityDetailsService.findAll();
    }

    /**
     * GET  /entity-details/:id : get the "id" entityDetails.
     *
     * @param id the id of the entityDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/entity-details/{id}")
    @Timed
    public ResponseEntity<EntityDetailsDTO> getEntityDetails(@PathVariable Long id) {
        log.debug("REST request to get EntityDetails : {}", id);
        Optional<EntityDetailsDTO> entityDetailsDTO = entityDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityDetailsDTO);
    }

    /**
     * DELETE  /entity-details/:id : delete the "id" entityDetails.
     *
     * @param id the id of the entityDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityDetails(@PathVariable Long id) {
        log.debug("REST request to delete EntityDetails : {}", id);
        entityDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
