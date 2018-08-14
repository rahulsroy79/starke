package com.starke.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.starke.service.EntityReviewsService;
import com.starke.web.rest.errors.BadRequestAlertException;
import com.starke.web.rest.util.HeaderUtil;
import com.starke.service.dto.EntityReviewsDTO;
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

/**
 * REST controller for managing EntityReviews.
 */
@RestController
@RequestMapping("/api")
public class EntityReviewsResource {

    private final Logger log = LoggerFactory.getLogger(EntityReviewsResource.class);

    private static final String ENTITY_NAME = "entityReviews";

    private final EntityReviewsService entityReviewsService;

    public EntityReviewsResource(EntityReviewsService entityReviewsService) {
        this.entityReviewsService = entityReviewsService;
    }

    /**
     * POST  /entity-reviews : Create a new entityReviews.
     *
     * @param entityReviewsDTO the entityReviewsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entityReviewsDTO, or with status 400 (Bad Request) if the entityReviews has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entity-reviews")
    @Timed
    public ResponseEntity<EntityReviewsDTO> createEntityReviews(@Valid @RequestBody EntityReviewsDTO entityReviewsDTO) throws URISyntaxException {
        log.debug("REST request to save EntityReviews : {}", entityReviewsDTO);
        if (entityReviewsDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityReviews cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityReviewsDTO result = entityReviewsService.save(entityReviewsDTO);
        return ResponseEntity.created(new URI("/api/entity-reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entity-reviews : Updates an existing entityReviews.
     *
     * @param entityReviewsDTO the entityReviewsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entityReviewsDTO,
     * or with status 400 (Bad Request) if the entityReviewsDTO is not valid,
     * or with status 500 (Internal Server Error) if the entityReviewsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entity-reviews")
    @Timed
    public ResponseEntity<EntityReviewsDTO> updateEntityReviews(@Valid @RequestBody EntityReviewsDTO entityReviewsDTO) throws URISyntaxException {
        log.debug("REST request to update EntityReviews : {}", entityReviewsDTO);
        if (entityReviewsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityReviewsDTO result = entityReviewsService.save(entityReviewsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entityReviewsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entity-reviews : get all the entityReviews.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of entityReviews in body
     */
    @GetMapping("/entity-reviews")
    @Timed
    public List<EntityReviewsDTO> getAllEntityReviews() {
        log.debug("REST request to get all EntityReviews");
        return entityReviewsService.findAll();
    }

    /**
     * GET  /entity-reviews/:id : get the "id" entityReviews.
     *
     * @param id the id of the entityReviewsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entityReviewsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/entity-reviews/{id}")
    @Timed
    public ResponseEntity<EntityReviewsDTO> getEntityReviews(@PathVariable Long id) {
        log.debug("REST request to get EntityReviews : {}", id);
        Optional<EntityReviewsDTO> entityReviewsDTO = entityReviewsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityReviewsDTO);
    }

    /**
     * DELETE  /entity-reviews/:id : delete the "id" entityReviews.
     *
     * @param id the id of the entityReviewsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entity-reviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntityReviews(@PathVariable Long id) {
        log.debug("REST request to delete EntityReviews : {}", id);
        entityReviewsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
