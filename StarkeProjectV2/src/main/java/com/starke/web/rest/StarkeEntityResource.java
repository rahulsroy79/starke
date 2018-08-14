package com.starke.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.starke.service.StarkeEntityService;
import com.starke.web.rest.errors.BadRequestAlertException;
import com.starke.web.rest.util.HeaderUtil;
import com.starke.web.rest.util.PaginationUtil;
import com.starke.service.dto.StarkeEntityDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing StarkeEntity.
 */
@RestController
@RequestMapping("/api")
public class StarkeEntityResource {

    private final Logger log = LoggerFactory.getLogger(StarkeEntityResource.class);

    private static final String ENTITY_NAME = "starkeEntity";

    private final StarkeEntityService starkeEntityService;

    public StarkeEntityResource(StarkeEntityService starkeEntityService) {
        this.starkeEntityService = starkeEntityService;
    }

    /**
     * POST  /starke-entities : Create a new starkeEntity.
     *
     * @param starkeEntityDTO the starkeEntityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new starkeEntityDTO, or with status 400 (Bad Request) if the starkeEntity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/starke-entities")
    @Timed
    public ResponseEntity<StarkeEntityDTO> createStarkeEntity(@Valid @RequestBody StarkeEntityDTO starkeEntityDTO) throws URISyntaxException {
        log.debug("REST request to save StarkeEntity : {}", starkeEntityDTO);
        if (starkeEntityDTO.getId() != null) {
            throw new BadRequestAlertException("A new starkeEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StarkeEntityDTO result = starkeEntityService.save(starkeEntityDTO);
        return ResponseEntity.created(new URI("/api/starke-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /starke-entities : Updates an existing starkeEntity.
     *
     * @param starkeEntityDTO the starkeEntityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated starkeEntityDTO,
     * or with status 400 (Bad Request) if the starkeEntityDTO is not valid,
     * or with status 500 (Internal Server Error) if the starkeEntityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/starke-entities")
    @Timed
    public ResponseEntity<StarkeEntityDTO> updateStarkeEntity(@Valid @RequestBody StarkeEntityDTO starkeEntityDTO) throws URISyntaxException {
        log.debug("REST request to update StarkeEntity : {}", starkeEntityDTO);
        if (starkeEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StarkeEntityDTO result = starkeEntityService.save(starkeEntityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, starkeEntityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /starke-entities : get all the starkeEntities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of starkeEntities in body
     */
    @GetMapping("/starke-entities")
    @Timed
    public ResponseEntity<List<StarkeEntityDTO>> getAllStarkeEntities(Pageable pageable) {
        log.debug("REST request to get a page of StarkeEntities");
        Page<StarkeEntityDTO> page = starkeEntityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/starke-entities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /starke-entities/:id : get the "id" starkeEntity.
     *
     * @param id the id of the starkeEntityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the starkeEntityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/starke-entities/{id}")
    @Timed
    public ResponseEntity<StarkeEntityDTO> getStarkeEntity(@PathVariable Long id) {
        log.debug("REST request to get StarkeEntity : {}", id);
        Optional<StarkeEntityDTO> starkeEntityDTO = starkeEntityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(starkeEntityDTO);
    }

    /**
     * DELETE  /starke-entities/:id : delete the "id" starkeEntity.
     *
     * @param id the id of the starkeEntityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/starke-entities/{id}")
    @Timed
    public ResponseEntity<Void> deleteStarkeEntity(@PathVariable Long id) {
        log.debug("REST request to delete StarkeEntity : {}", id);
        starkeEntityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
