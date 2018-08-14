package com.starke.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.starke.service.EntityPageFormatsService;
import com.starke.web.rest.errors.BadRequestAlertException;
import com.starke.web.rest.util.HeaderUtil;
import com.starke.service.dto.EntityPageFormatsDTO;
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
 * REST controller for managing EntityPageFormats.
 */
@RestController
@RequestMapping("/api")
public class EntityPageFormatsResource {

    private final Logger log = LoggerFactory.getLogger(EntityPageFormatsResource.class);

    private static final String ENTITY_NAME = "entityPageFormats";

    private final EntityPageFormatsService entityPageFormatsService;

    public EntityPageFormatsResource(EntityPageFormatsService entityPageFormatsService) {
        this.entityPageFormatsService = entityPageFormatsService;
    }

    /**
     * POST  /entity-page-formats : Create a new entityPageFormats.
     *
     * @param entityPageFormatsDTO the entityPageFormatsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityPageFormatsDTO, or with status 400 (Bad Request) if the entityPageFormats has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-page-formats")
    @Timed
    public ResponseEntity<EntityPageFormatsDTO> createEntityPageFormats(@Valid @RequestBody EntityPageFormatsDTO entityPageFormatsDTO) throws URISyntaxException {
        log.debug("REST request to save EntityPageFormats : {}", entityPageFormatsDTO);
        if (entityPageFormatsDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityPageFormats cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityPageFormatsDTO result = entityPageFormatsService.save(entityPageFormatsDTO);
        return ResponseEntity.created(new URI("/api/entity-page-formats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-page-formats : Updates an existing entityPageFormats.
     *
     * @param entityPageFormatsDTO the entityPageFormatsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityPageFormatsDTO,
     * or with status 400 (Bad Request) if the entityPageFormatsDTO is not valid,
     * or with status 500 (Internal Server Error) if the entityPageFormatsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-page-formats")
    @Timed
    public ResponseEntity<EntityPageFormatsDTO> updateEntityPageFormats(@Valid @RequestBody EntityPageFormatsDTO entityPageFormatsDTO) throws URISyntaxException {
        log.debug("REST request to update EntityPageFormats : {}", entityPageFormatsDTO);
        if (entityPageFormatsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityPageFormatsDTO result = entityPageFormatsService.save(entityPageFormatsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityPageFormatsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-page-formats : get all the entityPageFormats.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of entityPageFormats in body
     */
    @GetMapping("/entity-page-formats")
    @Timed
    public List<EntityPageFormatsDTO> getAllEntityPageFormats(@RequestParam(required = false) String filter) {
        if ("entitypagedetails-is-null".equals(filter)) {
            log.debug("REST request to get all EntityPageFormatss where entityPageDetails is null");
            return entityPageFormatsService.findAllWhereEntityPageDetailsIsNull();
        }
        log.debug("REST request to get all EntityPageFormats");
        return entityPageFormatsService.findAll();
    }

    /**
     * GET  /entity-page-formats/:id : get the "id" entityPageFormats.
     *
     * @param id the id of the entityPageFormatsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityPageFormatsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/entity-page-formats/{id}")
    @Timed
    public ResponseEntity<EntityPageFormatsDTO> getEntityPageFormats(@PathVariable Long id) {
        log.debug("REST request to get EntityPageFormats : {}", id);
        Optional<EntityPageFormatsDTO> entityPageFormatsDTO = entityPageFormatsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityPageFormatsDTO);
    }

    /**
     * DELETE  /entity-page-formats/:id : delete the "id" entityPageFormats.
     *
     * @param id the id of the entityPageFormatsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-page-formats/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityPageFormats(@PathVariable Long id) {
        log.debug("REST request to delete EntityPageFormats : {}", id);
        entityPageFormatsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
